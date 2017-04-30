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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Search match for resource
 */
@Experimental
public class SearchMatch {
    private Double lineNumber;

    private String lineContent;

    /**
     * Line number in resource content.
     */
    public Double getLineNumber() {
        return lineNumber;
    }

    /**
     * Line number in resource content.
     */
    public void setLineNumber(Double lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Line with match content.
     */
    public String getLineContent() {
        return lineContent;
    }

    /**
     * Line with match content.
     */
    public void setLineContent(String lineContent) {
        this.lineContent = lineContent;
    }
}
