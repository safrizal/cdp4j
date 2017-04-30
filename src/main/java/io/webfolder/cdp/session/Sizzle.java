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

import java.util.Scanner;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.type.dom.Node;
import io.webfolder.cdp.type.runtime.RemoteObject;

public interface Sizzle {

    /**
     * @return <code>true</code> if sizzle is installed
     */
    public default boolean useSizzle() {
        return getThis().getSizzle();
    }

    /**
     * Remove sizzle CSS selector engine
     */
    public default void removeSizzle() {
        if (getThis().useSizzle()) {
            getThis().logEntry("removeSizzle");
            getThis().setSizzle(false);
            getThis().evaluate("window.cdp4j = undefined");
        }
    }

    /**
     * Install sizzle CSS selector engine instead of browser's native selector engine.
     * 
     * @return this
     */
    default Session installSizzle() {
        getThis().setSizzle(true);
        getThis().disableFlowLog();
        final boolean install = TRUE.equals(getThis().evaluate("typeof window.cdp4j === 'undefined'"));
        getThis().enableFlowLog();
        if (install) {
            String sizzle = null;
            try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/cdp4j-sizzle-2.3.3.min.js"))) {
                scanner.useDelimiter("\\A");
                sizzle = scanner.hasNext() ? scanner.next() : "";
            }
            String func = "window.cdp4j = {}; window.cdp4j.query = function(selector) { " +
                                        sizzle +
                                        " var result = Sizzle(selector); if (result.length > 0) { return result[0]; } else { return null; } };";
            getThis().disableFlowLog();
            getThis().evaluate(func);
            getThis().enableFlowLog();
            DOM dom = getThis().getCommand().getDOM();
            Node document = dom.getDocument();
            if (document != null) {
                RemoteObject remoteObject = dom.resolveNode(document.getNodeId());
                if (remoteObject != null) {
                    if (remoteObject.getObjectId() != null) {
                        getThis().getCommand().getRuntime().releaseObject(remoteObject.getObjectId());
                    }
                }
            }
        }
        return getThis();
    }

    Session getThis();
}
