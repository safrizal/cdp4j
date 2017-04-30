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
package io.webfolder.cdp.type.page;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Visual viewport position, dimensions, and scale
 */
@Experimental
public class VisualViewport {
    private Double offsetX;

    private Double offsetY;

    private Double pageX;

    private Double pageY;

    private Double clientWidth;

    private Double clientHeight;

    private Double scale;

    /**
     * Horizontal offset relative to the layout viewport (CSS pixels).
     */
    public Double getOffsetX() {
        return offsetX;
    }

    /**
     * Horizontal offset relative to the layout viewport (CSS pixels).
     */
    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * Vertical offset relative to the layout viewport (CSS pixels).
     */
    public Double getOffsetY() {
        return offsetY;
    }

    /**
     * Vertical offset relative to the layout viewport (CSS pixels).
     */
    public void setOffsetY(Double offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public Double getPageX() {
        return pageX;
    }

    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public void setPageX(Double pageX) {
        this.pageX = pageX;
    }

    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public Double getPageY() {
        return pageY;
    }

    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public void setPageY(Double pageY) {
        this.pageY = pageY;
    }

    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public Double getClientWidth() {
        return clientWidth;
    }

    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public void setClientWidth(Double clientWidth) {
        this.clientWidth = clientWidth;
    }

    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public Double getClientHeight() {
        return clientHeight;
    }

    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public void setClientHeight(Double clientHeight) {
        this.clientHeight = clientHeight;
    }

    /**
     * Scale relative to the ideal viewport (size at width=device-width).
     */
    public Double getScale() {
        return scale;
    }

    /**
     * Scale relative to the ideal viewport (size at width=device-width).
     */
    public void setScale(Double scale) {
        this.scale = scale;
    }
}
