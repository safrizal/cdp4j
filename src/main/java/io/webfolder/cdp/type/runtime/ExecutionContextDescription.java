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
 * Description of an isolated world
 */
public class ExecutionContextDescription {
    private Integer id;

    private String origin;

    private String name;

    private Object auxData;

    /**
     * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Execution context origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Execution context origin.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Human readable name describing given context.
     */
    public String getName() {
        return name;
    }

    /**
     * Human readable name describing given context.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public Object getAuxData() {
        return auxData;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public void setAuxData(Object auxData) {
        this.auxData = auxData;
    }
}
