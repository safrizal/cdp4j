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

import java.util.HashMap;
import java.util.Map;

import io.webfolder.cdp.type.constant.MixedContentType;
import io.webfolder.cdp.type.constant.ReferrerPolicy;

/**
 * HTTP request data
 */
public class Request {
    private String url;

    private String method;

    private Map<String, Object> headers = new HashMap<>();

    private String postData;

    private MixedContentType mixedContentType;

    private ResourcePriority initialPriority;

    private ReferrerPolicy referrerPolicy;

    private Boolean isLinkPreload;

    /**
     * Request URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Request URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * HTTP request method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * HTTP request method.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * HTTP request headers.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * HTTP request headers.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * HTTP POST request data.
     */
    public String getPostData() {
        return postData;
    }

    /**
     * HTTP POST request data.
     */
    public void setPostData(String postData) {
        this.postData = postData;
    }

    /**
     * The mixed content status of the request, as defined in http://www.w3.org/TR/mixed-content/
     */
    public MixedContentType getMixedContentType() {
        return mixedContentType;
    }

    /**
     * The mixed content status of the request, as defined in http://www.w3.org/TR/mixed-content/
     */
    public void setMixedContentType(MixedContentType mixedContentType) {
        this.mixedContentType = mixedContentType;
    }

    /**
     * Priority of the resource request at the time request is sent.
     */
    public ResourcePriority getInitialPriority() {
        return initialPriority;
    }

    /**
     * Priority of the resource request at the time request is sent.
     */
    public void setInitialPriority(ResourcePriority initialPriority) {
        this.initialPriority = initialPriority;
    }

    /**
     * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
     */
    public ReferrerPolicy getReferrerPolicy() {
        return referrerPolicy;
    }

    /**
     * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
     */
    public void setReferrerPolicy(ReferrerPolicy referrerPolicy) {
        this.referrerPolicy = referrerPolicy;
    }

    /**
     * Whether is loaded via link preload.
     */
    public Boolean isIsLinkPreload() {
        return isLinkPreload;
    }

    /**
     * Whether is loaded via link preload.
     */
    public void setIsLinkPreload(Boolean isLinkPreload) {
        this.isLinkPreload = isLinkPreload;
    }
}
