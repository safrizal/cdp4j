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

import static io.webfolder.cdp.type.constant.KeyEventType.KeyDown;
import static io.webfolder.cdp.type.constant.KeyEventType.KeyUp;
import static java.lang.String.valueOf;

import io.webfolder.cdp.command.Input;
import io.webfolder.cdp.type.constant.KeyEventType;

/**
 * Interface representing basic keyboard operations.
 */
public interface Keyboard {

    /**
     * Use this method to simulate typing into an element, which may set its value.
     * 
     * @param text character sequence to send to the element
     * 
     * @return this
     */
    default Session sendKeys(String text) {
        getThis().logEntry("sendKeys", text);
        if (text == null) {
            return getThis();
        }
        Input input = getThis().getCommand().getInput();
        for (int i = 0; i < text.length(); i++) {
            String c = text.substring(i, i + 1);
            input.dispatchKeyEvent(
                    KeyEventType.KeyDown, null, null, c,
                    null, null, null, null,
                    null, null, null, null,
                    null);
            input.dispatchKeyEvent(
                    KeyEventType.KeyUp, null, null, c,
                    null, null, null, null,
                    null, null, null, null,
                    null);
        }
        return getThis();
    }

    /**
     * Use this method to simulate typing <strong>TAB</strong> key.
     * 
     * @return this
     */
    default Session sendTab() {
        getThis().logEntry("sendTab");
        Input input = getThis().getCommand().getInput();
        input.dispatchKeyEvent(KeyDown, null, null, null,
                                null, null, null, "Tab",
                                9, 9, null, null, null);
        input.dispatchKeyEvent(KeyUp, null, null, null,
                                null, null, null, "Tab",
                                9, 9, null, null, null);
        return getThis();
    }

    /**
     * Use this method to simulate typing <strong>ENTER</strong> key.
     * 
     * @return this
     */
    default Session sendEnter() {
        getThis().logEntry("sendEnter");
        Input input = getThis().getCommand().getInput();
        input.dispatchKeyEvent(KeyDown, null, null, "\r",
                                null, null, null, "Enter",
                                13, 13, null, null, null);
        input.dispatchKeyEvent(KeyUp, null, null, null,
                                null, null, null, "Enter",
                                13, 13, null, null, null);
        return getThis();
    }

    /**
     * Use this method to simulate typing <strong>BACKSPACE</strong> key.
     * 
     * @return this
     */
    default Session sendBackspace() {
        getThis().logEntry("sendBackspace");
        return sendKeyCode(46);
    }

    /**
     * Use this method to simulate typing <strong>LEFT_ARROW</strong> key.
     * 
     * @return this
     */
    default Session sendLeftArrow() {
        getThis().logEntry("sendLeftArrow");
        return sendKeyCode(37);
    }

    /**
     * Use this method to simulate typing <strong>UP_ARROW</strong> key.
     * 
     * @return this
     */
    default Session sendUpArrow() {
        getThis().logEntry("sendUpArrow");
        return sendKeyCode(38);
    }

    /**
     * Use this method to simulate typing <strong>RIGHT_ARROW</strong> key.
     * 
     * @return this
     */
    default Session sendRightArrow() {
        getThis().logEntry("sendRightArrow");
        return sendKeyCode(39);
    }

    /**
     * Use this method to simulate typing <strong>DOWN_ARROW</strong> key.
     * 
     * @return this
     */
    default Session sendDownArrow() {
        getThis().logEntry("sendDownArrow");
        return sendKeyCode(40);
    }

    /**
     * Use this method to simulate typing <strong>ESC</strong> key.
     * 
     * @return this
     */
    default Session sendEsc() {
        getThis().logEntry("sendEsc");
        return sendKeyCode(27);
    }

    /**
     * Use this method to simulate typing unicode value of keyboard key.
     * 
     * @return this
     */
    default Session sendKeyCode(int keyCode) {
        if ( ! Constant.SPECIAL_KEYS.contains(keyCode) ) {
            getThis().logEntry("sendKeyCode", valueOf(keyCode));
        }
        Input input = getThis().getCommand().getInput();
        input.dispatchKeyEvent(KeyDown, null, null, null,
                                null, null, null, null,
                                keyCode, keyCode, null, null, null);
        input.dispatchKeyEvent(KeyUp, null, null, null,
                                null, null, null, null,
                                keyCode, keyCode, null, null, null);
        return getThis();
    }

    Session getThis();
}
