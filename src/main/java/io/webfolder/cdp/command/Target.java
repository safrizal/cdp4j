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
import io.webfolder.cdp.type.target.RemoteLocation;
import io.webfolder.cdp.type.target.TargetInfo;

/**
 * Supports additional targets discovery and allows to attach to them
 */
@Experimental
@Domain("Target")
public interface Target {
    /**
     * Controls whether to discover available targets and notify via <tt>targetCreated/targetDestroyed</tt> events.
     * 
     * @param discover Whether to discover available targets.
     */
    void setDiscoverTargets(Boolean discover);

    /**
     * Controls whether to automatically attach to new targets which are considered to be related to this one. When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets.
     * 
     * @param autoAttach Whether to auto-attach to related targets.
     * @param waitForDebuggerOnStart Whether to pause new targets when attaching to them. Use <code>Runtime.runIfWaitingForDebugger</code> to run paused targets.
     */
    void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart);

    void setAttachToFrames(Boolean value);

    /**
     * Enables target discovery for the specified locations, when <tt>setDiscoverTargets</tt> was set to <tt>true</tt>.
     * 
     * @param locations List of remote locations.
     */
    void setRemoteLocations(List<RemoteLocation> locations);

    /**
     * Sends protocol message to the target with given id.
     * 
     */
    void sendMessageToTarget(String targetId, String message);

    /**
     * Returns information about a target.
     * 
     */
    @Returns("targetInfo")
    TargetInfo getTargetInfo(String targetId);

    /**
     * Activates (focuses) the target.
     * 
     */
    void activateTarget(String targetId);

    /**
     * Closes the target. If the target is a page that gets closed too.
     * 
     */
    @Returns("success")
    Boolean closeTarget(String targetId);

    /**
     * Attaches to the target with given id.
     * 
     * 
     * @return Whether attach succeeded.
     */
    @Returns("success")
    Boolean attachToTarget(String targetId);

    /**
     * Detaches from the target with given id.
     * 
     */
    void detachFromTarget(String targetId);

    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
     * 
     * @return The id of the context created.
     */
    @Returns("browserContextId")
    String createBrowserContext();

    /**
     * Deletes a BrowserContext, will fail of any open page uses it.
     * 
     */
    @Returns("success")
    Boolean disposeBrowserContext(String browserContextId);

    /**
     * Creates a new page.
     * 
     * @param url The initial URL the page will be navigated to.
     * @param width Frame width in DIP (headless chrome only).
     * @param height Frame height in DIP (headless chrome only).
     * @param browserContextId The browser context to create the page in (headless chrome only).
     * 
     * @return The id of the page opened.
     */
    @Returns("targetId")
    String createTarget(String url, @Optional Integer width, @Optional Integer height,
            @Optional String browserContextId);

    /**
     * Retrieves a list of available targets.
     * 
     * @return The list of targets.
     */
    @Returns("targetInfos")
    List<TargetInfo> getTargets();

    /**
     * Creates a new page.
     * 
     * @param url The initial URL the page will be navigated to.
     * 
     * @return The id of the page opened.
     */
    @Returns("targetId")
    String createTarget(String url);
}
