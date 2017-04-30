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
package io.webfolder.cdp.event.runtime;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.constant.ConsoleApiCallType;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Issued when console API was called
 */
@Domain("Runtime")
@EventName("consoleAPICalled")
public class ConsoleAPICalled {
    private ConsoleApiCallType type;

    private List<RemoteObject> args = new ArrayList<>();

    private Integer executionContextId;

    private Double timestamp;

    private StackTrace stackTrace;

    /**
     * Type of the call.
     */
    public ConsoleApiCallType getType() {
        return type;
    }

    /**
     * Type of the call.
     */
    public void setType(ConsoleApiCallType type) {
        this.type = type;
    }

    /**
     * Call arguments.
     */
    public List<RemoteObject> getArgs() {
        return args;
    }

    /**
     * Call arguments.
     */
    public void setArgs(List<RemoteObject> args) {
        this.args = args;
    }

    /**
     * Identifier of the context where the call was made.
     */
    public Integer getExecutionContextId() {
        return executionContextId;
    }

    /**
     * Identifier of the context where the call was made.
     */
    public void setExecutionContextId(Integer executionContextId) {
        this.executionContextId = executionContextId;
    }

    /**
     * Call timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Call timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Stack trace captured when the call was made.
     */
    public StackTrace getStackTrace() {
        return stackTrace;
    }

    /**
     * Stack trace captured when the call was made.
     */
    public void setStackTrace(StackTrace stackTrace) {
        this.stackTrace = stackTrace;
    }
}
