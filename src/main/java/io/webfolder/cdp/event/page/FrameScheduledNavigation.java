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
package io.webfolder.cdp.event.page;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;

/**
 * Fired when frame schedules a potential navigation
 */
@Experimental
@Domain("Page")
@EventName("frameScheduledNavigation")
public class FrameScheduledNavigation {
    private String frameId;

    private Double delay;

    /**
     * Id of the frame that has scheduled a navigation.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame that has scheduled a navigation.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
     */
    public Double getDelay() {
        return delay;
    }

    /**
     * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
     */
    public void setDelay(Double delay) {
        this.delay = delay;
    }
}
