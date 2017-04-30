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
package io.webfolder.cdp.type.security;

/**
 * Information about insecure content on the page
 */
public class InsecureContentStatus {
    private Boolean ranMixedContent;

    private Boolean displayedMixedContent;

    private Boolean containedMixedForm;

    private Boolean ranContentWithCertErrors;

    private Boolean displayedContentWithCertErrors;

    private SecurityState ranInsecureContentStyle;

    private SecurityState displayedInsecureContentStyle;

    /**
     * True if the page was loaded over HTTPS and ran mixed (HTTP) content such as scripts.
     */
    public Boolean isRanMixedContent() {
        return ranMixedContent;
    }

    /**
     * True if the page was loaded over HTTPS and ran mixed (HTTP) content such as scripts.
     */
    public void setRanMixedContent(Boolean ranMixedContent) {
        this.ranMixedContent = ranMixedContent;
    }

    /**
     * True if the page was loaded over HTTPS and displayed mixed (HTTP) content such as images.
     */
    public Boolean isDisplayedMixedContent() {
        return displayedMixedContent;
    }

    /**
     * True if the page was loaded over HTTPS and displayed mixed (HTTP) content such as images.
     */
    public void setDisplayedMixedContent(Boolean displayedMixedContent) {
        this.displayedMixedContent = displayedMixedContent;
    }

    /**
     * True if the page was loaded over HTTPS and contained a form targeting an insecure url.
     */
    public Boolean isContainedMixedForm() {
        return containedMixedForm;
    }

    /**
     * True if the page was loaded over HTTPS and contained a form targeting an insecure url.
     */
    public void setContainedMixedForm(Boolean containedMixedForm) {
        this.containedMixedForm = containedMixedForm;
    }

    /**
     * True if the page was loaded over HTTPS without certificate errors, and ran content such as scripts that were loaded with certificate errors.
     */
    public Boolean isRanContentWithCertErrors() {
        return ranContentWithCertErrors;
    }

    /**
     * True if the page was loaded over HTTPS without certificate errors, and ran content such as scripts that were loaded with certificate errors.
     */
    public void setRanContentWithCertErrors(Boolean ranContentWithCertErrors) {
        this.ranContentWithCertErrors = ranContentWithCertErrors;
    }

    /**
     * True if the page was loaded over HTTPS without certificate errors, and displayed content such as images that were loaded with certificate errors.
     */
    public Boolean isDisplayedContentWithCertErrors() {
        return displayedContentWithCertErrors;
    }

    /**
     * True if the page was loaded over HTTPS without certificate errors, and displayed content such as images that were loaded with certificate errors.
     */
    public void setDisplayedContentWithCertErrors(Boolean displayedContentWithCertErrors) {
        this.displayedContentWithCertErrors = displayedContentWithCertErrors;
    }

    /**
     * Security state representing a page that ran insecure content.
     */
    public SecurityState getRanInsecureContentStyle() {
        return ranInsecureContentStyle;
    }

    /**
     * Security state representing a page that ran insecure content.
     */
    public void setRanInsecureContentStyle(SecurityState ranInsecureContentStyle) {
        this.ranInsecureContentStyle = ranInsecureContentStyle;
    }

    /**
     * Security state representing a page that displayed insecure content.
     */
    public SecurityState getDisplayedInsecureContentStyle() {
        return displayedInsecureContentStyle;
    }

    /**
     * Security state representing a page that displayed insecure content.
     */
    public void setDisplayedInsecureContentStyle(SecurityState displayedInsecureContentStyle) {
        this.displayedInsecureContentStyle = displayedInsecureContentStyle;
    }
}
