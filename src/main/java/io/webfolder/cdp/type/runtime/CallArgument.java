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
package io.webfolder.cdp.type.runtime;

/**
 * Represents function call argument
 * Either remote object id <code>objectId</code>, primitive <code>value</code>, unserializable primitive value or neither of (for undefined) them should be specified
 */
public class CallArgument {
    private Object value;

    private UnserializableValue unserializableValue;

    private String objectId;

    /**
     * Primitive value.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Primitive value.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public UnserializableValue getUnserializableValue() {
        return unserializableValue;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public void setUnserializableValue(UnserializableValue unserializableValue) {
        this.unserializableValue = unserializableValue;
    }

    /**
     * Remote object handle.
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Remote object handle.
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
