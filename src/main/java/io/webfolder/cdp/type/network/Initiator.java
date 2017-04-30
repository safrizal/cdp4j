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
package io.webfolder.cdp.type.network;

import io.webfolder.cdp.type.constant.InitiatorType;
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Information about the request initiator
 */
public class Initiator {
    private InitiatorType type;

    private StackTrace stack;

    private String url;

    private Double lineNumber;

    /**
     * Type of this initiator.
     */
    public InitiatorType getType() {
        return type;
    }

    /**
     * Type of this initiator.
     */
    public void setType(InitiatorType type) {
        this.type = type;
    }

    /**
     * Initiator JavaScript stack trace, set for Script only.
     */
    public StackTrace getStack() {
        return stack;
    }

    /**
     * Initiator JavaScript stack trace, set for Script only.
     */
    public void setStack(StackTrace stack) {
        this.stack = stack;
    }

    /**
     * Initiator URL, set for Parser type only.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Initiator URL, set for Parser type only.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Initiator line number, set for Parser type only (0-based).
     */
    public Double getLineNumber() {
        return lineNumber;
    }

    /**
     * Initiator line number, set for Parser type only (0-based).
     */
    public void setLineNumber(Double lineNumber) {
        this.lineNumber = lineNumber;
    }
}
