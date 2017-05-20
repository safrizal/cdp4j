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
package io.webfolder.cdp.type.dom;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Box model
 */
@Experimental
public class BoxModel {
    private List<Double> content = new ArrayList<>();

    private List<Double> padding = new ArrayList<>();

    private List<Double> border = new ArrayList<>();

    private List<Double> margin = new ArrayList<>();

    private Integer width;

    private Integer height;

    private ShapeOutsideInfo shapeOutside;

    /**
     * Content box
     */
    public List<Double> getContent() {
        return content;
    }

    /**
     * Content box
     */
    public void setContent(List<Double> content) {
        this.content = content;
    }

    /**
     * Padding box
     */
    public List<Double> getPadding() {
        return padding;
    }

    /**
     * Padding box
     */
    public void setPadding(List<Double> padding) {
        this.padding = padding;
    }

    /**
     * Border box
     */
    public List<Double> getBorder() {
        return border;
    }

    /**
     * Border box
     */
    public void setBorder(List<Double> border) {
        this.border = border;
    }

    /**
     * Margin box
     */
    public List<Double> getMargin() {
        return margin;
    }

    /**
     * Margin box
     */
    public void setMargin(List<Double> margin) {
        this.margin = margin;
    }

    /**
     * Node width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Node width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Node height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Node height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Shape outside coordinates
     */
    public ShapeOutsideInfo getShapeOutside() {
        return shapeOutside;
    }

    /**
     * Shape outside coordinates
     */
    public void setShapeOutside(ShapeOutsideInfo shapeOutside) {
        this.shapeOutside = shapeOutside;
    }
}
