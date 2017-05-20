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
package io.webfolder.cdp.command;

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.domdebugger.DOMBreakpointType;
import io.webfolder.cdp.type.domdebugger.EventListener;

/**
 * DOM debugging allows setting breakpoints on particular DOM operations and events
 * JavaScript execution will stop on these operations as if there was a regular breakpoint set
 */
@Domain("DOMDebugger")
public interface DOMDebugger {
    /**
     * Sets breakpoint on particular operation with DOM.
     * 
     * @param nodeId Identifier of the node to set breakpoint on.
     * @param type Type of the operation to stop upon.
     */
    void setDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

    /**
     * Removes DOM breakpoint that was set using <tt>setDOMBreakpoint</tt>.
     * 
     * @param nodeId Identifier of the node to remove breakpoint from.
     * @param type Type of the breakpoint to remove.
     */
    void removeDOMBreakpoint(Integer nodeId, DOMBreakpointType type);

    /**
     * Sets breakpoint on particular DOM event.
     * 
     * @param eventName DOM Event name to stop on (any DOM event will do).
     * @param targetName EventTarget interface name to stop on. If equal to <code>"*"</code> or not provided, will stop on any EventTarget.
     */
    void setEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

    /**
     * Removes breakpoint on particular DOM event.
     * 
     * @param eventName Event name.
     * @param targetName EventTarget interface name.
     */
    void removeEventListenerBreakpoint(String eventName, @Experimental @Optional String targetName);

    /**
     * Sets breakpoint on particular native event.
     * 
     * @param eventName Instrumentation name to stop on.
     */
    @Experimental
    void setInstrumentationBreakpoint(String eventName);

    /**
     * Removes breakpoint on particular native event.
     * 
     * @param eventName Instrumentation name to stop on.
     */
    @Experimental
    void removeInstrumentationBreakpoint(String eventName);

    /**
     * Sets breakpoint on XMLHttpRequest.
     * 
     * @param url Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
     */
    void setXHRBreakpoint(String url);

    /**
     * Removes breakpoint from XMLHttpRequest.
     * 
     * @param url Resource URL substring.
     */
    void removeXHRBreakpoint(String url);

    /**
     * Returns event listeners of the given object.
     * 
     * @param objectId Identifier of the object to return listeners for.
     * @param depth The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
     * 
     * @return Array of relevant listeners.
     */
    @Experimental
    @Returns("listeners")
    List<EventListener> getEventListeners(String objectId, @Experimental @Optional Integer depth,
            @Experimental @Optional Boolean pierce);

    /**
     * Sets breakpoint on particular DOM event.
     * 
     * @param eventName DOM Event name to stop on (any DOM event will do).
     */
    void setEventListenerBreakpoint(String eventName);

    /**
     * Removes breakpoint on particular DOM event.
     * 
     * @param eventName Event name.
     */
    void removeEventListenerBreakpoint(String eventName);

    /**
     * Returns event listeners of the given object.
     * 
     * @param objectId Identifier of the object to return listeners for.
     * 
     * @return Array of relevant listeners.
     */
    @Experimental
    @Returns("listeners")
    List<EventListener> getEventListeners(String objectId);
}
