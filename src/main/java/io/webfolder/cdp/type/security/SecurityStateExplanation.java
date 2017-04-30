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
 * An explanation of an factor contributing to the security state
 */
public class SecurityStateExplanation {
    private SecurityState securityState;

    private String summary;

    private String description;

    private Boolean hasCertificate;

    /**
     * Security state representing the severity of the factor being explained.
     */
    public SecurityState getSecurityState() {
        return securityState;
    }

    /**
     * Security state representing the severity of the factor being explained.
     */
    public void setSecurityState(SecurityState securityState) {
        this.securityState = securityState;
    }

    /**
     * Short phrase describing the type of factor.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Short phrase describing the type of factor.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Full text explanation of the factor.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Full text explanation of the factor.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * True if the page has a certificate.
     */
    public Boolean isHasCertificate() {
        return hasCertificate;
    }

    /**
     * True if the page has a certificate.
     */
    public void setHasCertificate(Boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }
}
