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

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.constant.ImageFormat;
import io.webfolder.cdp.type.constant.Platform;
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.emulation.ScreenOrientation;
import io.webfolder.cdp.type.network.Cookie;
import io.webfolder.cdp.type.page.FrameResourceTree;
import io.webfolder.cdp.type.page.GetAppManifestResult;
import io.webfolder.cdp.type.page.GetLayoutMetricsResult;
import io.webfolder.cdp.type.page.GetNavigationHistoryResult;
import io.webfolder.cdp.type.page.GetResourceContentResult;
import io.webfolder.cdp.type.page.NavigationResponse;

/**
 * Actions and events related to the inspected page belong to the page domain
 */
@Domain("Page")
public interface Page {
    /**
     * Enables page domain notifications.
     */
    void enable();

    /**
     * Disables page domain notifications.
     */
    void disable();

    @Experimental
    @Returns("identifier")
    String addScriptToEvaluateOnLoad(String scriptSource);

    @Experimental
    void removeScriptToEvaluateOnLoad(String identifier);

    /**
     * Controls whether browser will open a new inspector window for connected pages.
     * 
     * @param autoAttach If true, browser will open a new inspector window for every page created from this one.
     */
    @Experimental
    void setAutoAttachToCreatedPages(Boolean autoAttach);

    /**
     * Reloads given page optionally ignoring the cache.
     * 
     * @param ignoreCache If true, browser cache is ignored (as if the user pressed Shift+refresh).
     * @param scriptToEvaluateOnLoad If set, the script will be injected into all frames of the inspected page after reload.
     */
    void reload(@Optional Boolean ignoreCache, @Optional String scriptToEvaluateOnLoad);

    /**
     * Navigates current page to the given URL.
     * 
     * @param url URL to navigate the page to.
     * @param referrer Referrer URL.
     * 
     * @return Frame id that will be navigated.
     */
    @Returns("frameId")
    String navigate(String url, @Experimental @Optional String referrer);

    /**
     * Force the page stop all navigations and pending resource fetches.
     */
    @Experimental
    void stopLoading();

    /**
     * Returns navigation history for the current page.
     * 
     * @return GetNavigationHistoryResult
     */
    @Experimental
    GetNavigationHistoryResult getNavigationHistory();

    /**
     * Navigates current page to the given history entry.
     * 
     * @param entryId Unique id of the entry to navigate to.
     */
    @Experimental
    void navigateToHistoryEntry(Integer entryId);

    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the <tt>cookies</tt> field.
     * 
     * @return Array of cookie objects.
     */
    @Experimental
    @Returns("cookies")
    List<Cookie> getCookies();

    /**
     * Deletes browser cookie with given name, domain and path.
     * 
     * @param cookieName Name of the cookie to remove.
     * @param url URL to match cooke domain and path.
     */
    @Experimental
    void deleteCookie(String cookieName, String url);

    /**
     * Returns present frame / resource tree structure.
     * 
     * @return Present frame / resource tree structure.
     */
    @Experimental
    @Returns("frameTree")
    FrameResourceTree getResourceTree();

    /**
     * Returns content of the given resource.
     * 
     * @param frameId Frame id to get resource for.
     * @param url URL of the resource to get content for.
     * 
     * @return GetResourceContentResult
     */
    @Experimental
    GetResourceContentResult getResourceContent(String frameId, String url);

    /**
     * Searches for given string in resource content.
     * 
     * @param frameId Frame id for resource to search in.
     * @param url URL of the resource to search in.
     * @param query String to search for.
     * @param caseSensitive If true, search is case sensitive.
     * @param isRegex If true, treats string parameter as regex.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResource(String frameId, String url, String query,
            @Optional Boolean caseSensitive, @Optional Boolean isRegex);

    /**
     * Sets given markup as the document's HTML.
     * 
     * @param frameId Frame id to set HTML for.
     * @param html HTML content to set.
     */
    @Experimental
    void setDocumentContent(String frameId, String html);

    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
     * 
     * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
     * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
     * @param fitWindow Whether a view that exceeds the available browser window area should be scaled down to fit.
     * @param scale Scale to apply to resulting view image. Ignored in |fitWindow| mode.
     * @param offsetX X offset to shift resulting view image by. Ignored in |fitWindow| mode.
     * @param offsetY Y offset to shift resulting view image by. Ignored in |fitWindow| mode.
     * @param screenWidth Overriding screen width value in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param screenHeight Overriding screen height value in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param positionX Overriding view X position on screen in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param positionY Overriding view Y position on screen in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param screenOrientation Screen orientation override.
     */
    @Experimental
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile, Boolean fitWindow, @Optional Double scale, @Optional Double offsetX,
            @Optional Double offsetY, @Optional Integer screenWidth, @Optional Integer screenHeight,
            @Optional Integer positionX, @Optional Integer positionY,
            @Optional ScreenOrientation screenOrientation);

    /**
     * Clears the overriden device metrics.
     */
    @Experimental
    void clearDeviceMetricsOverride();

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     * 
     * @param latitude Mock latitude
     * @param longitude Mock longitude
     * @param accuracy Mock accuracy
     */
    void setGeolocationOverride(@Optional Double latitude, @Optional Double longitude,
            @Optional Double accuracy);

    /**
     * Clears the overriden Geolocation Position and Error.
     */
    void clearGeolocationOverride();

    /**
     * Overrides the Device Orientation.
     * 
     * @param alpha Mock alpha
     * @param beta Mock beta
     * @param gamma Mock gamma
     */
    @Experimental
    void setDeviceOrientationOverride(Double alpha, Double beta, Double gamma);

    /**
     * Clears the overridden Device Orientation.
     */
    @Experimental
    void clearDeviceOrientationOverride();

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     * @param configuration Touch/gesture events configuration. Default: current platform.
     */
    @Experimental
    void setTouchEmulationEnabled(Boolean enabled, @Optional Platform configuration);

    /**
     * Capture page screenshot.
     * 
     * @param format Image compression format (defaults to png).
     * @param quality Compression quality from range [0..100] (jpeg only).
     * @param fromSurface Capture the screenshot from the surface, rather than the view. Defaults to false.
     * 
     * @return Base64-encoded image data.
     */
    @Experimental
    @Returns("data")
    byte[] captureScreenshot(@Optional ImageFormat format, @Optional Integer quality,
            @Experimental @Optional Boolean fromSurface);

    /**
     * Print page as PDF.
     * 
     * @param landscape Paper orientation. Defaults to false.
     * @param displayHeaderFooter Display header and footer. Defaults to false.
     * @param printBackground Print background graphics. Defaults to false.
     * @param scale Scale of the webpage rendering. Defaults to 1.
     * @param paperWidth Paper width in inches. Defaults to 8.5 inches.
     * @param paperHeight Paper height in inches. Defaults to 11 inches.
     * @param marginTop Top margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginBottom Bottom margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginLeft Left margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginRight Right margin in inches. Defaults to 1cm (~0.4 inches).
     * @param pageRanges Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string, which means print all pages.
     * 
     * @return Base64-encoded pdf data.
     */
    @Experimental
    @Returns("data")
    byte[] printToPDF(@Optional Boolean landscape, @Optional Boolean displayHeaderFooter,
            @Optional Boolean printBackground, @Optional Double scale, @Optional Double paperWidth,
            @Optional Double paperHeight, @Optional Double marginTop, @Optional Double marginBottom,
            @Optional Double marginLeft, @Optional Double marginRight, @Optional String pageRanges);

    /**
     * Starts sending each frame using the <tt>screencastFrame</tt> event.
     * 
     * @param format Image compression format.
     * @param quality Compression quality from range [0..100].
     * @param maxWidth Maximum screenshot width.
     * @param maxHeight Maximum screenshot height.
     * @param everyNthFrame Send every n-th frame.
     */
    @Experimental
    void startScreencast(@Optional ImageFormat format, @Optional Integer quality,
            @Optional Integer maxWidth, @Optional Integer maxHeight,
            @Optional Integer everyNthFrame);

    /**
     * Stops sending each frame in the <tt>screencastFrame</tt>.
     */
    @Experimental
    void stopScreencast();

    /**
     * Acknowledges that a screencast frame has been received by the frontend.
     * 
     * @param sessionId Frame number.
     */
    @Experimental
    void screencastFrameAck(Integer sessionId);

    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     * 
     * @param accept Whether to accept or dismiss the dialog.
     * @param promptText The text to enter into the dialog prompt before accepting. Used only if this is a prompt dialog.
     */
    void handleJavaScriptDialog(Boolean accept, @Optional String promptText);

    /**
     * 
     * @return GetAppManifestResult
     */
    @Experimental
    GetAppManifestResult getAppManifest();

    @Experimental
    void requestAppBanner();

    /**
     * Toggles navigation throttling which allows programatic control over navigation and redirect response.
     * 
     */
    @Experimental
    void setControlNavigations(Boolean enabled);

    /**
     * Should be sent in response to a navigationRequested or a redirectRequested event, telling the browser how to handle the navigation.
     * 
     */
    @Experimental
    void processNavigation(NavigationResponse response, Integer navigationId);

    /**
     * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
     * 
     * @return GetLayoutMetricsResult
     */
    @Experimental
    GetLayoutMetricsResult getLayoutMetrics();

    /**
     * Reloads given page optionally ignoring the cache.
     */
    void reload();

    /**
     * Navigates current page to the given URL.
     * 
     * @param url URL to navigate the page to.
     * 
     * @return Frame id that will be navigated.
     */
    @Returns("frameId")
    String navigate(String url);

    /**
     * Searches for given string in resource content.
     * 
     * @param frameId Frame id for resource to search in.
     * @param url URL of the resource to search in.
     * @param query String to search for.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResource(String frameId, String url, String query);

    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
     * 
     * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
     * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
     * @param fitWindow Whether a view that exceeds the available browser window area should be scaled down to fit.
     */
    @Experimental
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile, Boolean fitWindow);

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     */
    void setGeolocationOverride();

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     */
    @Experimental
    void setTouchEmulationEnabled(Boolean enabled);

    /**
     * Capture page screenshot.
     * 
     * @return Base64-encoded image data.
     */
    @Experimental
    @Returns("data")
    byte[] captureScreenshot();

    /**
     * Print page as PDF.
     * 
     * @return Base64-encoded pdf data.
     */
    @Experimental
    @Returns("data")
    byte[] printToPDF();

    /**
     * Starts sending each frame using the <tt>screencastFrame</tt> event.
     */
    @Experimental
    void startScreencast();

    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     * 
     * @param accept Whether to accept or dismiss the dialog.
     */
    void handleJavaScriptDialog(Boolean accept);
}
