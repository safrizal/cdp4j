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
package io.webfolder.cdp.type.accessibility;

import com.google.gson.annotations.SerializedName;

/**
 * Attributes which apply to widgets
 */
public enum AXWidgetAttributes {
    @SerializedName("autocomplete")
    Autocomplete("autocomplete"),

    @SerializedName("haspopup")
    Haspopup("haspopup"),

    @SerializedName("level")
    Level("level"),

    @SerializedName("multiselectable")
    Multiselectable("multiselectable"),

    @SerializedName("orientation")
    Orientation("orientation"),

    @SerializedName("multiline")
    Multiline("multiline"),

    @SerializedName("readonly")
    Readonly("readonly"),

    @SerializedName("required")
    Required("required"),

    @SerializedName("valuemin")
    Valuemin("valuemin"),

    @SerializedName("valuemax")
    Valuemax("valuemax"),

    @SerializedName("valuetext")
    Valuetext("valuetext");

    public final String value;

    AXWidgetAttributes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
