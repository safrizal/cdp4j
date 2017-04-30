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

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.browser.Bounds;
import io.webfolder.cdp.type.browser.GetWindowForTargetResult;

/**
 * The Browser domain defines methods and events for browser managing
 */
@Experimental
@Domain("Browser")
public interface Browser {
    /**
     * Get the browser window that contains the devtools target.
     * 
     * @param targetId Devtools agent host id.
     * 
     * @return GetWindowForTargetResult
     */
    GetWindowForTargetResult getWindowForTarget(String targetId);

    /**
     * Set position and/or size of the browser window.
     * 
     * @param windowId Browser window id.
     * @param bounds New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
     */
    void setWindowBounds(Integer windowId, Bounds bounds);

    /**
     * Get position and size of the browser window.
     * 
     * @param windowId Browser window id.
     * 
     * @return Bounds information of the window. When window state is 'minimized', the restored window position and size are returned.
     */
    @Returns("bounds")
    Bounds getWindowBounds(Integer windowId);
}
