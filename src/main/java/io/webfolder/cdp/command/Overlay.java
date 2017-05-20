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
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.overlay.HighlightConfig;
import io.webfolder.cdp.type.overlay.InspectMode;

/**
 * This domain provides various functionality related to drawing atop the inspected page
 */
@Experimental
@Domain("Overlay")
public interface Overlay {
    /**
     * Enables domain notifications.
     */
    void enable();

    /**
     * Disables domain notifications.
     */
    void disable();

    /**
     * Requests that backend shows paint rectangles
     * 
     * @param result True for showing paint rectangles
     */
    void setShowPaintRects(Boolean result);

    /**
     * Requests that backend shows debug borders on layers
     * 
     * @param show True for showing debug borders
     */
    void setShowDebugBorders(Boolean show);

    /**
     * Requests that backend shows the FPS counter
     * 
     * @param show True for showing the FPS counter
     */
    void setShowFPSCounter(Boolean show);

    /**
     * Requests that backend shows scroll bottleneck rects
     * 
     * @param show True for showing scroll bottleneck rects
     */
    void setShowScrollBottleneckRects(Boolean show);

    /**
     * Paints viewport size upon main frame resize.
     * 
     * @param show Whether to paint size or not.
     */
    void setShowViewportSizeOnResize(Boolean show);

    void setPausedInDebuggerMessage(@Optional String message);

    void setSuspended(Boolean suspended);

    /**
     * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted. Backend then generates 'inspectNodeRequested' event upon element selection.
     * 
     * @param mode Set an inspection mode.
     * @param highlightConfig A descriptor for the highlight appearance of hovered-over nodes. May be omitted if <code>enabled == false</code>.
     */
    void setInspectMode(InspectMode mode, @Optional HighlightConfig highlightConfig);

    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Rectangle width
     * @param height Rectangle height
     * @param color The highlight fill color (default: transparent).
     * @param outlineColor The highlight outline color (default: transparent).
     */
    void highlightRect(Integer x, Integer y, Integer width, Integer height, @Optional RGBA color,
            @Optional RGBA outlineColor);

    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param quad Quad to highlight
     * @param color The highlight fill color (default: transparent).
     * @param outlineColor The highlight outline color (default: transparent).
     */
    void highlightQuad(List<Double> quad, @Optional RGBA color, @Optional RGBA outlineColor);

    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     * 
     * @param highlightConfig A descriptor for the highlight appearance.
     * @param nodeId Identifier of the node to highlight.
     * @param backendNodeId Identifier of the backend node to highlight.
     * @param objectId JavaScript object id of the node to be highlighted.
     */
    void highlightNode(HighlightConfig highlightConfig, @Optional Integer nodeId,
            @Optional Integer backendNodeId, @Optional String objectId);

    /**
     * Highlights owner element of the frame with given id.
     * 
     * @param frameId Identifier of the frame to highlight.
     * @param contentColor The content box highlight fill color (default: transparent).
     * @param contentOutlineColor The content box highlight outline color (default: transparent).
     */
    void highlightFrame(String frameId, @Optional RGBA contentColor,
            @Optional RGBA contentOutlineColor);

    /**
     * Hides any highlight.
     */
    void hideHighlight();

    void setPausedInDebuggerMessage();

    /**
     * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted. Backend then generates 'inspectNodeRequested' event upon element selection.
     * 
     * @param mode Set an inspection mode.
     */
    void setInspectMode(InspectMode mode);

    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Rectangle width
     * @param height Rectangle height
     */
    void highlightRect(Integer x, Integer y, Integer width, Integer height);

    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param quad Quad to highlight
     */
    void highlightQuad(List<Double> quad);

    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     * 
     * @param highlightConfig A descriptor for the highlight appearance.
     */
    void highlightNode(HighlightConfig highlightConfig);

    /**
     * Highlights owner element of the frame with given id.
     * 
     * @param frameId Identifier of the frame to highlight.
     */
    void highlightFrame(String frameId);
}
