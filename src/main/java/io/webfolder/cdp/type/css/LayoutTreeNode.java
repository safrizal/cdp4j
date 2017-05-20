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
package io.webfolder.cdp.type.css;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.dom.Rect;

/**
 * Details of an element in the DOM tree with a LayoutObject
 */
@Experimental
public class LayoutTreeNode {
    private Integer nodeId;

    private Rect boundingBox;

    private String layoutText;

    private List<InlineTextBox> inlineTextNodes = new ArrayList<>();

    private Integer styleIndex;

    /**
     * The id of the related DOM node matching one from DOM.GetDocument.
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * The id of the related DOM node matching one from DOM.GetDocument.
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * The absolute position bounding box.
     */
    public Rect getBoundingBox() {
        return boundingBox;
    }

    /**
     * The absolute position bounding box.
     */
    public void setBoundingBox(Rect boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * Contents of the LayoutText if any
     */
    public String getLayoutText() {
        return layoutText;
    }

    /**
     * Contents of the LayoutText if any
     */
    public void setLayoutText(String layoutText) {
        this.layoutText = layoutText;
    }

    /**
     * The post layout inline text nodes, if any.
     */
    public List<InlineTextBox> getInlineTextNodes() {
        return inlineTextNodes;
    }

    /**
     * The post layout inline text nodes, if any.
     */
    public void setInlineTextNodes(List<InlineTextBox> inlineTextNodes) {
        this.inlineTextNodes = inlineTextNodes;
    }

    /**
     * Index into the computedStyles array returned by getLayoutTreeAndStyles.
     */
    public Integer getStyleIndex() {
        return styleIndex;
    }

    /**
     * Index into the computedStyles array returned by getLayoutTreeAndStyles.
     */
    public void setStyleIndex(Integer styleIndex) {
        this.styleIndex = styleIndex;
    }
}
