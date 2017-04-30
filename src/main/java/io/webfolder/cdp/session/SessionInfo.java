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

public class SessionInfo {

    private String description;

    private String devtoolsFrontendUrl;

    private String id;

    private String title;

    private String type;

    private String url;

    private String webSocketDebuggerUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevtoolsFrontendUrl() {
        return devtoolsFrontendUrl;
    }

    public void setDevtoolsFrontendUrl(String devtoolsFrontendUrl) {
        this.devtoolsFrontendUrl = devtoolsFrontendUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebSocketDebuggerUrl() {
        return webSocketDebuggerUrl;
    }

    public void setWebSocketDebuggerUrl(String webSocketDebuggerUrl) {
        this.webSocketDebuggerUrl = webSocketDebuggerUrl;
    }

    @Override
    public String toString() {
        return "SessionInfo [description=" + description + ", devtoolsFrontendUrl=" + devtoolsFrontendUrl + ", id=" + id
                + ", title=" + title + ", type=" + type + ", url=" + url + ", webSocketDebuggerUrl="
                + webSocketDebuggerUrl + "]";
    }
}
