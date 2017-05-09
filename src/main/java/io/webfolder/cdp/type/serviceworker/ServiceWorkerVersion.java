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
package io.webfolder.cdp.type.serviceworker;

/**
 * ServiceWorker version
 */
public class ServiceWorkerVersion {
    private String versionId;

    private String registrationId;

    private String scriptURL;

    private ServiceWorkerVersionRunningStatus runningStatus;

    private ServiceWorkerVersionStatus status;

    private Double scriptLastModified;

    private Double scriptResponseTime;

    private String targetId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getScriptURL() {
        return scriptURL;
    }

    public void setScriptURL(String scriptURL) {
        this.scriptURL = scriptURL;
    }

    public ServiceWorkerVersionRunningStatus getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(ServiceWorkerVersionRunningStatus runningStatus) {
        this.runningStatus = runningStatus;
    }

    public ServiceWorkerVersionStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceWorkerVersionStatus status) {
        this.status = status;
    }

    /**
     * The Last-Modified header value of the main script.
     */
    public Double getScriptLastModified() {
        return scriptLastModified;
    }

    /**
     * The Last-Modified header value of the main script.
     */
    public void setScriptLastModified(Double scriptLastModified) {
        this.scriptLastModified = scriptLastModified;
    }

    /**
     * The time at which the response headers of the main script were received from the server.  For cached script it is the last time the cache entry was validated.
     */
    public Double getScriptResponseTime() {
        return scriptResponseTime;
    }

    /**
     * The time at which the response headers of the main script were received from the server.  For cached script it is the last time the cache entry was validated.
     */
    public void setScriptResponseTime(Double scriptResponseTime) {
        this.scriptResponseTime = scriptResponseTime;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
