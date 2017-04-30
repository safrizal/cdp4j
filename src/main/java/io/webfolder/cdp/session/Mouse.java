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

import static io.webfolder.cdp.type.constant.MouseButtonType.Left;
import static io.webfolder.cdp.type.constant.MouseEventType.MousePressed;
import static io.webfolder.cdp.type.constant.MouseEventType.MouseReleased;
import static java.lang.Math.floor;
import static java.lang.String.format;

import java.util.List;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.Input;
import io.webfolder.cdp.exception.ElementNotFoundException;
import io.webfolder.cdp.type.dom.BoxModel;

/**
 * Interface representing basic mouse operations.
 */
public interface Mouse {

    /**
     * Click on the specified element.
     * 
     * There are some preconditions for an element to be clicked.
     * The element must be visible and it must have a height and width greater then 0.
     * 
     * @param selector css or xpath selector
     * 
     * @return this
     */
    default Session click(final String selector) {
        getThis();
        return click(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Click on the specified element.
     * 
     * There are some preconditions for an element to be clicked.
     * The element must be visible and it must have a height and width greater then 0.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return this
     */
    default Session click(final String selector, final Object ...args) {
        getThis().logEntry("click", format(selector, args));
        DOM dom = getThis().getCommand().getDOM();
        Integer nodeId = getThis().getNodeId(format(selector, args));
        if (nodeId == null || nodeId == Constant.EMPTY_NODE_ID) {
            throw new ElementNotFoundException(format(selector, args));
        }
        BoxModel boxModel = dom.getBoxModel(nodeId);
        if (boxModel == null) {
            return getThis();
        }
        List<Double> content = boxModel.getContent();
        if (content == null           ||
                    content.isEmpty() ||
                    content.size() < 2) {
            return getThis();
        }
        int left = (int) floor(content.get(0));
        int  top = (int) floor(content.get(1));
        int clickCount = 1;
        Input input = getThis().getCommand().getInput();
        input.dispatchMouseEvent(MousePressed, left, top, null, null, Left, clickCount);
        input.dispatchMouseEvent(MouseReleased, left, top, null, null, Left, clickCount);
        return getThis();
    }

    Session getThis();
}
