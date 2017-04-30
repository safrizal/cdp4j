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
package io.webfolder.cdp.type.overlay;

import io.webfolder.cdp.type.dom.RGBA;

/**
 * Configuration data for the highlighting of page elements
 */
public class HighlightConfig {
    private Boolean showInfo;

    private Boolean showRulers;

    private Boolean showExtensionLines;

    private Boolean displayAsMaterial;

    private RGBA contentColor;

    private RGBA paddingColor;

    private RGBA borderColor;

    private RGBA marginColor;

    private RGBA eventTargetColor;

    private RGBA shapeColor;

    private RGBA shapeMarginColor;

    private String selectorList;

    /**
     * Whether the node info tooltip should be shown (default: false).
     */
    public Boolean isShowInfo() {
        return showInfo;
    }

    /**
     * Whether the node info tooltip should be shown (default: false).
     */
    public void setShowInfo(Boolean showInfo) {
        this.showInfo = showInfo;
    }

    /**
     * Whether the rulers should be shown (default: false).
     */
    public Boolean isShowRulers() {
        return showRulers;
    }

    /**
     * Whether the rulers should be shown (default: false).
     */
    public void setShowRulers(Boolean showRulers) {
        this.showRulers = showRulers;
    }

    /**
     * Whether the extension lines from node to the rulers should be shown (default: false).
     */
    public Boolean isShowExtensionLines() {
        return showExtensionLines;
    }

    /**
     * Whether the extension lines from node to the rulers should be shown (default: false).
     */
    public void setShowExtensionLines(Boolean showExtensionLines) {
        this.showExtensionLines = showExtensionLines;
    }

    public Boolean isDisplayAsMaterial() {
        return displayAsMaterial;
    }

    public void setDisplayAsMaterial(Boolean displayAsMaterial) {
        this.displayAsMaterial = displayAsMaterial;
    }

    /**
     * The content box highlight fill color (default: transparent).
     */
    public RGBA getContentColor() {
        return contentColor;
    }

    /**
     * The content box highlight fill color (default: transparent).
     */
    public void setContentColor(RGBA contentColor) {
        this.contentColor = contentColor;
    }

    /**
     * The padding highlight fill color (default: transparent).
     */
    public RGBA getPaddingColor() {
        return paddingColor;
    }

    /**
     * The padding highlight fill color (default: transparent).
     */
    public void setPaddingColor(RGBA paddingColor) {
        this.paddingColor = paddingColor;
    }

    /**
     * The border highlight fill color (default: transparent).
     */
    public RGBA getBorderColor() {
        return borderColor;
    }

    /**
     * The border highlight fill color (default: transparent).
     */
    public void setBorderColor(RGBA borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * The margin highlight fill color (default: transparent).
     */
    public RGBA getMarginColor() {
        return marginColor;
    }

    /**
     * The margin highlight fill color (default: transparent).
     */
    public void setMarginColor(RGBA marginColor) {
        this.marginColor = marginColor;
    }

    /**
     * The event target element highlight fill color (default: transparent).
     */
    public RGBA getEventTargetColor() {
        return eventTargetColor;
    }

    /**
     * The event target element highlight fill color (default: transparent).
     */
    public void setEventTargetColor(RGBA eventTargetColor) {
        this.eventTargetColor = eventTargetColor;
    }

    /**
     * The shape outside fill color (default: transparent).
     */
    public RGBA getShapeColor() {
        return shapeColor;
    }

    /**
     * The shape outside fill color (default: transparent).
     */
    public void setShapeColor(RGBA shapeColor) {
        this.shapeColor = shapeColor;
    }

    /**
     * The shape margin fill color (default: transparent).
     */
    public RGBA getShapeMarginColor() {
        return shapeMarginColor;
    }

    /**
     * The shape margin fill color (default: transparent).
     */
    public void setShapeMarginColor(RGBA shapeMarginColor) {
        this.shapeMarginColor = shapeMarginColor;
    }

    /**
     * Selectors to highlight relevant nodes.
     */
    public String getSelectorList() {
        return selectorList;
    }

    /**
     * Selectors to highlight relevant nodes.
     */
    public void setSelectorList(String selectorList) {
        this.selectorList = selectorList;
    }
}
