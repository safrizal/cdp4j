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
package io.webfolder.cdp.type.runtime;

import io.webfolder.cdp.annotation.Experimental;

@Experimental
public class CustomPreview {
    private String header;

    private Boolean hasBody;

    private String formatterObjectId;

    private String bindRemoteObjectFunctionId;

    private String configObjectId;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean isHasBody() {
        return hasBody;
    }

    public void setHasBody(Boolean hasBody) {
        this.hasBody = hasBody;
    }

    public String getFormatterObjectId() {
        return formatterObjectId;
    }

    public void setFormatterObjectId(String formatterObjectId) {
        this.formatterObjectId = formatterObjectId;
    }

    public String getBindRemoteObjectFunctionId() {
        return bindRemoteObjectFunctionId;
    }

    public void setBindRemoteObjectFunctionId(String bindRemoteObjectFunctionId) {
        this.bindRemoteObjectFunctionId = bindRemoteObjectFunctionId;
    }

    public String getConfigObjectId() {
        return configObjectId;
    }

    public void setConfigObjectId(String configObjectId) {
        this.configObjectId = configObjectId;
    }
}
