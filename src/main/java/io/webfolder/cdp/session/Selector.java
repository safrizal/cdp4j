/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.session;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.Runtime;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.ElementNotFoundException;
import io.webfolder.cdp.type.dom.Node;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.EvaluateResult;
import io.webfolder.cdp.type.runtime.ExceptionDetails;
import io.webfolder.cdp.type.runtime.GetPropertiesResult;
import io.webfolder.cdp.type.runtime.PropertyDescriptor;
import io.webfolder.cdp.type.runtime.RemoteObject;

public interface Selector {

    /**
     * This method returns <code>true</code> if the element would be selected by the specified selector string;
     * otherwise, returns <code>false</code>.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return <code>true</code> if the element selected by the specified selector
     */
    default boolean matches(final String selector) {
        return matches(selector, Constant.EMPTY_ARGS);
    }

    /**
     * This method returns <code>true</code> if the element would be selected by the specified selector string;
     * otherwise, returns <code>false</code>.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return <code>true</code> if the element selected by the specified selector
     */
    default boolean matches(
                    final String selector,
                    final Object ...args) {
        Integer nodeId = getThis().getNodeId(selector, args);
        if (nodeId == null || nodeId == Constant.EMPTY_NODE_ID) {
            return false;
        }
        boolean retValue = nodeId.intValue() > 0;
        getThis().logExit("matches", format(selector, args), retValue);
        return retValue;
    }

    /**
     * Gets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * 
     * @return property value
     */
    default Object getProperty(
                        final String selector,
                        final String propertyName) {
        return getProperty(selector, propertyName, Constant.EMPTY_ARGS);
    }

    /**
     * Gets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param args format string
     * 
     * @return property value
     */
    default Object getProperty(
                        final String selector,
                        final String propertyName,
                        final Object ...args) {
        String objectId = getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        Object value = getPropertyByObjectId(objectId, propertyName);
        releaseObject(objectId);
        if ( ! Constant.DOM_PROPERTIES.contains(propertyName) ) {
            getThis().logExit("getProperty", format(selector, args) + "\", \"" + propertyName,
                                    valueOf(value).replace("\n", "").replace("\r", ""));
        }
        return value;
    }

    /**
     * Sets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param value property value
     */
    default void setProperty(
            final String selector,
            final String propetyName,
            final Object value) {
        setProperty(selector, propetyName, value, Constant.EMPTY_ARGS);
    }

    /**
     * Sets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param value property value
     * @param args format string
     */
    default void setProperty(
                    final String selector,
                    final String propertyName,
                    final Object value,
                    final Object ...args) {
        if ( ! Constant.DOM_PROPERTIES.contains(propertyName) ) {
            getThis().logEntry("setProperty", format(selector) + "\", \"" + propertyName + "\", \"" + value);
        }
        String objectId = getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        List<CallArgument> arguments = new ArrayList<>(2);
        CallArgument prp = new CallArgument();
        prp.setValue(propertyName);
        CallArgument val = new CallArgument();
        val.setValue(value);
        arguments.add(prp);
        arguments.add(val);
        CallFunctionOnResult callFunctionOn = getThis()
                                                .getCommand()
                                                .getRuntime()
                                                .callFunctionOn(objectId,
                                                        "function(property, value) { function index(obj, property, value) { " +
                                                        "if (typeof property == 'string') return index(obj, property.split('.'), value); " +
                                                        "else if (property.length == 1 && value !== undefined) return obj[property[0]] = value; " +
                                                        "else if (property.length == 0) return obj; " +
                                                        "else return index(obj[property[0]], property.slice(1), value); }" +
                                                        "return index(this, property, value); }",
                                                        arguments,
                                                        FALSE, TRUE, FALSE, FALSE, FALSE);
        String error = null;
        if (callFunctionOn != null) {
            RemoteObject result = callFunctionOn.getResult();
            if (result != null) {
                if ( result != null ) {
                    getThis().releaseObject(result.getObjectId());
                }
            }
            if (callFunctionOn.getExceptionDetails() != null) {
                RemoteObject exception = callFunctionOn.getExceptionDetails().getException();
                if (exception != null) {
                    error = exception.getDescription();
                }
            }
        }
        getThis().releaseObject(objectId);
        if ( error != null ) {
            throw new CdpException(error);
        }
    }

    default PropertyDescriptor getPropertyDescriptor(
                final String objectId,
                final String name) {
        Runtime runtime = getThis().getCommand().getRuntime();
        GetPropertiesResult properties = runtime.getProperties(objectId);
        if (properties == null) {
            return null;
        }
        if (properties.getResult() == null ||
                        properties.getResult().isEmpty()) {
            return null;
        }
        for (PropertyDescriptor next : properties.getResult()) {
            if (name.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    /**
     * Gets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * 
     * @return property value
     */
    default Object getPropertyByObjectId(
                            final String objectId,
                            final String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        if (objectId == null || name == null) {
            return null;
        }
        List<CallArgument> arguments = new ArrayList<>(1);
        CallArgument argProperty = new CallArgument();
        argProperty.setValue(name);
        arguments.add(argProperty);
        CallFunctionOnResult callFunctionOn = getThis()
                                                .getCommand()
                                                .getRuntime()
                                                .callFunctionOn(objectId,
                                                        "function(property) { return property.split('.').reduce((o, i) => o[i], this); }",
                                                        arguments,
                                                        FALSE, TRUE,
                                                        FALSE, FALSE,
                                                        FALSE);
        Object value = null;
        String error = null;
        if (callFunctionOn != null) {
            RemoteObject result = callFunctionOn.getResult();
            if (result != null) {
                value = callFunctionOn.getResult().getValue();
                if ( result != null ) {
                    getThis().releaseObject(result.getObjectId());
                }
            }
            if (callFunctionOn.getExceptionDetails() != null) {
                RemoteObject exception = callFunctionOn.getExceptionDetails().getException();
                if (exception != null) {
                    error = exception.getDescription();
                }
            }
        }
        if ( error != null ) {
            throw new CdpException(error);
        }
        return value;
    }

    default String getObjectId(
                final String selector,
                final Object ...args) {
        final boolean sizzle = getThis().useSizzle();
        DOM dom = getThis().getCommand().getDOM();
        final boolean xpath = selector.charAt(0) == '/';
        if (sizzle || xpath) {
            final Runtime       runtime = getThis().getCommand().getRuntime();
            final String           func = xpath ? "$x(\"%s\")[0]" : "window.cdp4j.query(\"%s\")";
            final String     expression = format(func, format(selector.replace("\"", "\\\""), args));
            final Boolean includeCmdApi = xpath ? TRUE : FALSE;
            EvaluateResult       result = runtime.evaluate(expression, null, includeCmdApi,
                                                                null, null, null,
                                                                null, null, null);
            if (result == null) {
                return null;
            }
            ExceptionDetails ex = result.getExceptionDetails();
            if ( sizzle && ex != null &&
                        ex.getException() != null &&
                        "TypeError".equals(ex.getException().getClassName()) )  {
                releaseObject(ex.getException().getObjectId());
                getThis().installSizzle();
                result = runtime.evaluate(expression, null, null,
                                                        null, null, null,
                                                        null, null, null);
                if ( result != null &&
                            result.getExceptionDetails() != null ) {
                    ex = result.getExceptionDetails();
                } else {
                    ex = null;
                }
            }
            if ( ex != null &&
                        ex.getException() != null &&
                        ex.getException().getObjectId() != null ) {
                releaseObject(ex.getException().getObjectId());
            }
            if ( xpath && ex != null && ex.getException() != null ) {
                throw new CdpException(ex.getException().getDescription());
            }
            RemoteObject remoteObject = result.getResult();
            if (remoteObject == null) {
                return null;
            }
            if (result.getResult() == null) {
                return null;
            }
            String objectId = result.getResult().getObjectId();
            return ex == null ? objectId : null;
        } else {
            Integer rootNodeId = dom.getDocument().getNodeId();
            if (rootNodeId == null) {
                return null;
            }
            Integer nodeId = dom.querySelector(rootNodeId, format(selector, args));
            if (nodeId == null || nodeId.intValue() == 0) {
                return null;
            }
            RemoteObject remoteObject = dom.resolveNode(nodeId);
            if (remoteObject == null) {
                return null;
            }
            String objectId = remoteObject.getObjectId();
            if (objectId == null) {
                return null;
            }
            return objectId;
        }
    }

    default Integer getNodeId(
                final String selector,
                final Object ...args) {
        if (selector == null || selector.trim().isEmpty()) {
            return Constant.EMPTY_NODE_ID;
        }
        boolean sizzle = getThis().useSizzle();
        Integer nodeId = Constant.EMPTY_NODE_ID;
        DOM dom = getThis().getCommand().getDOM();
        if (sizzle) {
            String objectId = getThis().getObjectId(format(selector, args));
            if (objectId != null) {
                nodeId = dom.requestNode(objectId);
                getThis().releaseObject(objectId);
            }
        } else {
            Node document = dom.getDocument();
            if (document != null) {
                Integer documentNodeId = document.getNodeId();
                if (documentNodeId != null) {
                    try {
                        nodeId = dom.querySelector(documentNodeId, format(selector, args));
                    } catch (CdpException e) {
                        throw new CdpException(
                                    format("Method invoke error: querySelector(%s). %s",
                                            format(selector, args),
                                            e.getMessage()));
                    }
                }
            }
        }
        return nodeId;
    }

    default Session releaseObject(final String objectId) {
        if (objectId != null) {
            try {
                getThis().getCommand().getRuntime().releaseObject(objectId);
            } catch (CdpException e) {
                throw e;
            }
        }
        return getThis();
    }

    Session getThis();
}
