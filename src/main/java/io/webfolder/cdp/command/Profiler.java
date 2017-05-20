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
import io.webfolder.cdp.type.profiler.Profile;
import io.webfolder.cdp.type.profiler.ScriptCoverage;

@Domain("Profiler")
public interface Profiler {
    void enable();

    void disable();

    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     * 
     * @param interval New sampling interval in microseconds.
     */
    void setSamplingInterval(Integer interval);

    void start();

    @Returns("profile")
    Profile stop();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     * 
     * @param callCount Collect accurate call counts beyond simple 'covered' or 'not covered'.
     */
    @Experimental
    void startPreciseCoverage(@Optional Boolean callCount);

    /**
     * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
     */
    @Experimental
    void stopPreciseCoverage();

    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
     * 
     * @return Coverage data for the current isolate.
     */
    @Experimental
    @Returns("result")
    List<ScriptCoverage> takePreciseCoverage();

    /**
     * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
     * 
     * @return Coverage data for the current isolate.
     */
    @Experimental
    @Returns("result")
    List<ScriptCoverage> getBestEffortCoverage();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     */
    @Experimental
    void startPreciseCoverage();
}
