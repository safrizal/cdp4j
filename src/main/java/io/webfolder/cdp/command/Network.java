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
package io.webfolder.cdp.command;

import java.util.List;
import java.util.Map;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.network.ConnectionType;
import io.webfolder.cdp.type.network.Cookie;
import io.webfolder.cdp.type.network.CookieSameSite;
import io.webfolder.cdp.type.network.GetResponseBodyResult;

/**
 * Network domain allows tracking network activities of the page
 * It exposes information about http, file, data and other requests and responses, their headers, bodies, timing, etc
 */
@Domain("Network")
public interface Network {
    /**
     * Enables network tracking, network events will now be delivered to the client.
     * 
     * @param maxTotalBufferSize Buffer size in bytes to use when preserving network payloads (XHRs, etc).
     * @param maxResourceBufferSize Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
     */
    void enable(@Experimental @Optional Integer maxTotalBufferSize,
            @Experimental @Optional Integer maxResourceBufferSize);

    /**
     * Disables network tracking, prevents network events from being sent to the client.
     */
    void disable();

    /**
     * Allows overriding user agent with the given string.
     * 
     * @param userAgent User agent to use.
     */
    void setUserAgentOverride(String userAgent);

    /**
     * Specifies whether to always send extra HTTP headers with the requests from this page.
     * 
     * @param headers Map with extra HTTP headers.
     */
    void setExtraHTTPHeaders(Map<String, Object> headers);

    /**
     * Returns content served for the given request.
     * 
     * @param requestId Identifier of the network request to get content for.
     * 
     * @return GetResponseBodyResult
     */
    GetResponseBodyResult getResponseBody(String requestId);

    /**
     * Blocks URLs from loading.
     * 
     * @param urls URL patterns to block. Wildcards ('*') are allowed.
     */
    @Experimental
    void setBlockedURLs(List<String> urls);

    /**
     * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
     * 
     * @param requestId Identifier of XHR to replay.
     */
    @Experimental
    void replayXHR(String requestId);

    /**
     * Tells whether clearing browser cache is supported.
     * 
     * @return True if browser cache can be cleared.
     */
    @Returns("result")
    Boolean canClearBrowserCache();

    /**
     * Clears browser cache.
     */
    void clearBrowserCache();

    /**
     * Tells whether clearing browser cookies is supported.
     * 
     * @return True if browser cookies can be cleared.
     */
    @Returns("result")
    Boolean canClearBrowserCookies();

    /**
     * Clears browser cookies.
     */
    void clearBrowserCookies();

    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the <tt>cookies</tt> field.
     * 
     * @param urls The list of URLs for which applicable cookies will be fetched
     * 
     * @return Array of cookie objects.
     */
    @Experimental
    @Returns("cookies")
    List<Cookie> getCookies(@Optional List<String> urls);

    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the <tt>cookies</tt> field.
     * 
     * @return Array of cookie objects.
     */
    @Experimental
    @Returns("cookies")
    List<Cookie> getAllCookies();

    /**
     * Deletes browser cookie with given name, domain and path.
     * 
     * @param cookieName Name of the cookie to remove.
     * @param url URL to match cooke domain and path.
     */
    @Experimental
    void deleteCookie(String cookieName, String url);

    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     * 
     * @param url The request-URI to associate with the setting of the cookie. This value can affect the default domain and path values of the created cookie.
     * @param name The name of the cookie.
     * @param value The value of the cookie.
     * @param domain If omitted, the cookie becomes a host-only cookie.
     * @param path Defaults to the path portion of the url parameter.
     * @param secure Defaults ot false.
     * @param httpOnly Defaults to false.
     * @param sameSite Defaults to browser default behavior.
     * @param expirationDate If omitted, the cookie becomes a session cookie.
     * 
     * @return True if successfully set cookie.
     */
    @Experimental
    @Returns("success")
    Boolean setCookie(String url, String name, String value, @Optional String domain,
            @Optional String path, @Optional Boolean secure, @Optional Boolean httpOnly,
            @Optional CookieSameSite sameSite, @Optional Double expirationDate);

    /**
     * Tells whether emulation of network conditions is supported.
     * 
     * @return True if emulation of network conditions is supported.
     */
    @Experimental
    @Returns("result")
    Boolean canEmulateNetworkConditions();

    /**
     * Activates emulation of network conditions.
     * 
     * @param offline True to emulate internet disconnection.
     * @param latency Additional latency (ms).
     * @param downloadThroughput Maximal aggregated download throughput.
     * @param uploadThroughput Maximal aggregated upload throughput.
     * @param connectionType Connection type if known.
     */
    void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput, @Optional ConnectionType connectionType);

    /**
     * Toggles ignoring cache for each request. If <tt>true</tt>, cache will not be used.
     * 
     * @param cacheDisabled Cache disabled state.
     */
    void setCacheDisabled(Boolean cacheDisabled);

    /**
     * Toggles ignoring of service worker for each request.
     * 
     * @param bypass Bypass service worker and load from network.
     */
    @Experimental
    void setBypassServiceWorker(Boolean bypass);

    /**
     * For testing.
     * 
     * @param maxTotalSize Maximum total buffer size.
     * @param maxResourceSize Maximum per-resource size.
     */
    @Experimental
    void setDataSizeLimitsForTest(Integer maxTotalSize, Integer maxResourceSize);

    /**
     * Returns the DER-encoded certificate.
     * 
     * @param origin Origin to get certificate for.
     */
    @Experimental
    @Returns("tableNames")
    List<String> getCertificate(String origin);

    /**
     * Enables network tracking, network events will now be delivered to the client.
     */
    void enable();

    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the <tt>cookies</tt> field.
     * 
     * @return Array of cookie objects.
     */
    @Experimental
    @Returns("cookies")
    List<Cookie> getCookies();

    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     * 
     * @param url The request-URI to associate with the setting of the cookie. This value can affect the default domain and path values of the created cookie.
     * @param name The name of the cookie.
     * @param value The value of the cookie.
     * 
     * @return True if successfully set cookie.
     */
    @Experimental
    @Returns("success")
    Boolean setCookie(String url, String name, String value);

    /**
     * Activates emulation of network conditions.
     * 
     * @param offline True to emulate internet disconnection.
     * @param latency Additional latency (ms).
     * @param downloadThroughput Maximal aggregated download throughput.
     * @param uploadThroughput Maximal aggregated upload throughput.
     */
    void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput);
}
