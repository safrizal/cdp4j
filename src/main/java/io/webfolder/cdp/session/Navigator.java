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

import static java.lang.Boolean.TRUE;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.type.runtime.RemoteObject;

public interface Navigator {

    /**
     * This method stops window loading.
     * 
     * @return this
     */
    public default Session stop() {
        getThis().logEntry("stop");
        getThis().getCommand().getPage().stopLoading();
        return getThis();
    }

    /**
     * Returns the window to the previous item in the history.
     * 
     * @return this
     */
    public default Session back() {
        getThis().logEntry("back");
        getThis().evaluate("window.history.back()");
        return getThis();
    }

    /**
     * Moves the window one document forward in the history
     * 
     * @return this
     */
    public default Session forward() {
        getThis().logEntry("forward");
        getThis().evaluate("window.history.forward()");
        return getThis();
    }

    /**
     * This method reloads the resource from the current URL.
     * 
     * @return this
     */
    public default Session reload() {
        getThis().logEntry("reload");
        getThis().getCommand().getPage().reload();
        return getThis();
    }


    /**
     * Allows overriding user agent with the given string.
     * 
     * @param userAgent User agent to use
     * 
     * @return this
     */
    public default Session setUserAgent(final String userAgent) {
        getThis().logEntry("userAgent", userAgent);
        Network network = getThis().getCommand().getNetwork();
        network.enable();
        network.setUserAgentOverride(userAgent);
        return getThis();
    }

    /**
     * Document URL that <code>Document</code> points to.
     * 
     * @return document location
     */
    default String getLocation() {
        return getThis()
                    .getCommand()
                    .getDOM()
                    .getDocument()
                    .getDocumentURL();
    }

    /**
     * Retrieves <code>Location.pathname</code> property.
     * 
     * @return an initial <strong>/</strong> followed by the path of the URL
     */
    public default String getPathname() {
        DOM dom = getThis().getCommand().getDOM();
        Integer nodeId = dom.getDocument().getNodeId();
        RemoteObject remoteObject = dom.resolveNode(nodeId);
        String pathname = (String) getThis().getPropertyByObjectId(remoteObject.getObjectId(), "location.pathname");
        getThis().releaseObject(remoteObject.getObjectId());
        System.out.println(pathname);
        return pathname;
    }

    /**
     * Gets query string
     * 
     * @return key value pair
     */
    @SuppressWarnings("unchecked")
    public default Map<String, Object> getQueryString() {
        getThis().disableFlowLog();
        String json = (String) getThis()
                                .evaluate("JSON.stringify(Array.from(new URLSearchParams(document.location.search)))");
        getThis().enableFlowLog();
        if (json == null || json.trim().isEmpty()) {
            return emptyMap();
        }
        List<List<Object>> params = getThis().getGson().fromJson(json, List.class);
        Map<String, Object> map = new LinkedHashMap<>(params.size());
        for (List<Object> param : params) {
            if (param.size() == 0) {
                continue;
            }
            String key = valueOf(param.get(0));
            Object value = null;
            if (param.size() > 1) {
                value = param.size() == 2 ? param.get(1) : param.subList(1, param.size());
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * Gets the title of the document.
     * 
     * @return string containing the document's title
     */
    default String getTitle() {
        DOM dom = getThis().getCommand().getDOM();
        Integer nodeId = dom.getDocument().getNodeId();
        RemoteObject remoteObject = dom.resolveNode(nodeId);
        String title = (String) getThis().getPropertyByObjectId(remoteObject.getObjectId(), "title");
        getThis().logExit("getTitle", title);
        getThis().releaseObject(remoteObject.getObjectId());
        return title;
    }

    /**
     * Gets the state of Document
     * 
     * @return <code>true</code> if Document.readyState property is <strong>complete</strong>
     */
    default boolean isDomReady() {
        try {
            getThis().disableFlowLog();
            DOM dom = getThis().getCommand().getDOM();
            Integer nodeId = dom.getDocument().getNodeId();
            RemoteObject remoteObject = dom.resolveNode(nodeId);
            String readyState = (String) getThis().getPropertyByObjectId(remoteObject.getObjectId(), "readyState");
            getThis().releaseObject(remoteObject.getObjectId());
            boolean domReady = "complete".equals(readyState);
            return domReady;            
        } catch (CdpException e) {
            return TRUE.equals(getThis().evaluate("window.document.readyState === 'complete'"));
        } finally {
            getThis().enableFlowLog();
        }
    }

    /**
     * Clears browser cache.
     * 
     * @return <code>true</code> if browser cache cleared.
     */
    public default boolean clearCache() {
        getThis().logEntry("clearCache");
        Network network = getThis().getCommand().getNetwork();
        network.enable();
        Boolean canClear = network.canClearBrowserCache();
        if (TRUE.equals(canClear)) {
            getThis()
                .getCommand()
                .getNetwork()
                .clearBrowserCache();
            return true;
        }
        return false;
    }

    /**
     * Clears all browser cookies.
     * 
     * @return <code>true</code> if browser cookies cleared.
     */
    public default boolean clearCookies() {
        getThis().logEntry("clearCookies");
        Network network = getThis().getCommand().getNetwork();
        network.enable();
        Boolean canClear = network.canClearBrowserCookies();
        if (TRUE.equals(canClear)) {
            getThis()
                .getCommand()
                .getNetwork()
                .clearBrowserCookies();
            return true;
        }
        return false;
    }

    Session getThis();
}
