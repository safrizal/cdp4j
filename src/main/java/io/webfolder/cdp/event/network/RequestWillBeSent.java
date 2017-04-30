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
package io.webfolder.cdp.event.network;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.network.Initiator;
import io.webfolder.cdp.type.network.Request;
import io.webfolder.cdp.type.network.Response;
import io.webfolder.cdp.type.page.ResourceType;

/**
 * Fired when page is about to send HTTP request
 */
@Domain("Network")
@EventName("requestWillBeSent")
public class RequestWillBeSent {
    private String requestId;

    private String frameId;

    private String loaderId;

    private String documentURL;

    private Request request;

    private Double timestamp;

    private Double wallTime;

    private Initiator initiator;

    private Response redirectResponse;

    private ResourceType type;

    /**
     * Request identifier.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Frame identifier.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Frame identifier.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Loader identifier.
     */
    public String getLoaderId() {
        return loaderId;
    }

    /**
     * Loader identifier.
     */
    public void setLoaderId(String loaderId) {
        this.loaderId = loaderId;
    }

    /**
     * URL of the document this request is loaded for.
     */
    public String getDocumentURL() {
        return documentURL;
    }

    /**
     * URL of the document this request is loaded for.
     */
    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    /**
     * Request data.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Request data.
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * UTC Timestamp.
     */
    public Double getWallTime() {
        return wallTime;
    }

    /**
     * UTC Timestamp.
     */
    public void setWallTime(Double wallTime) {
        this.wallTime = wallTime;
    }

    /**
     * Request initiator.
     */
    public Initiator getInitiator() {
        return initiator;
    }

    /**
     * Request initiator.
     */
    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }

    /**
     * Redirect response data.
     */
    public Response getRedirectResponse() {
        return redirectResponse;
    }

    /**
     * Redirect response data.
     */
    public void setRedirectResponse(Response redirectResponse) {
        this.redirectResponse = redirectResponse;
    }

    /**
     * Type of this resource.
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Type of this resource.
     */
    public void setType(ResourceType type) {
        this.type = type;
    }
}
