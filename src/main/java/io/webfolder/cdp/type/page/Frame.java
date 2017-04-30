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
package io.webfolder.cdp.type.page;

/**
 * Information about the Frame on the page
 */
public class Frame {
    private String id;

    private String parentId;

    private String loaderId;

    private String name;

    private String url;

    private String securityOrigin;

    private String mimeType;

    /**
     * Frame unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Frame unique identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Parent frame identifier.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Parent frame identifier.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Identifier of the loader associated with this frame.
     */
    public String getLoaderId() {
        return loaderId;
    }

    /**
     * Identifier of the loader associated with this frame.
     */
    public void setLoaderId(String loaderId) {
        this.loaderId = loaderId;
    }

    /**
     * Frame's name as specified in the tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Frame's name as specified in the tag.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Frame document's URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Frame document's URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Frame document's security origin.
     */
    public String getSecurityOrigin() {
        return securityOrigin;
    }

    /**
     * Frame document's security origin.
     */
    public void setSecurityOrigin(String securityOrigin) {
        this.securityOrigin = securityOrigin;
    }

    /**
     * Frame document's mimeType as determined by the browser.
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Frame document's mimeType as determined by the browser.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
