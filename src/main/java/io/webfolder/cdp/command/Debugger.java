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
import io.webfolder.cdp.type.constant.PauseOnExceptionState;
import io.webfolder.cdp.type.debugger.BreakLocation;
import io.webfolder.cdp.type.debugger.EvaluateOnCallFrameResult;
import io.webfolder.cdp.type.debugger.Location;
import io.webfolder.cdp.type.debugger.RestartFrameResult;
import io.webfolder.cdp.type.debugger.ScriptPosition;
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.debugger.SetBreakpointByUrlResult;
import io.webfolder.cdp.type.debugger.SetBreakpointResult;
import io.webfolder.cdp.type.debugger.SetScriptSourceResult;
import io.webfolder.cdp.type.runtime.CallArgument;

/**
 * Debugger domain exposes JavaScript debugging capabilities
 * It allows setting and removing breakpoints, stepping through execution, exploring stack traces, etc
 */
@Domain("Debugger")
public interface Debugger {
    /**
     * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
     */
    void enable();

    /**
     * Disables debugger for given page.
     */
    void disable();

    /**
     * Activates / deactivates all breakpoints on the page.
     * 
     * @param active New value for breakpoints active state.
     */
    void setBreakpointsActive(Boolean active);

    /**
     * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
     * 
     * @param skip New value for skip pauses state.
     */
    void setSkipAllPauses(Boolean skip);

    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in <tt>locations</tt> property. Further matching script parsing will result in subsequent <tt>breakpointResolved</tt> events issued. This logical breakpoint will survive page reloads.
     * 
     * @param lineNumber Line number to set breakpoint at.
     * @param url URL of the resources to set breakpoint on.
     * @param urlRegex Regex pattern for the URLs of the resources to set breakpoints on. Either <code>url</code> or <code>urlRegex</code> must be specified.
     * @param columnNumber Offset in the line to set breakpoint at.
     * @param condition Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
     * 
     * @return SetBreakpointByUrlResult
     */
    SetBreakpointByUrlResult setBreakpointByUrl(Integer lineNumber, @Optional String url,
            @Optional String urlRegex, @Optional Integer columnNumber, @Optional String condition);

    /**
     * Sets JavaScript breakpoint at a given location.
     * 
     * @param location Location to set breakpoint in.
     * @param condition Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
     * 
     * @return SetBreakpointResult
     */
    SetBreakpointResult setBreakpoint(Location location, @Optional String condition);

    /**
     * Removes JavaScript breakpoint.
     * 
     */
    void removeBreakpoint(String breakpointId);

    /**
     * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
     * 
     * @param start Start of range to search possible breakpoint locations in.
     * @param end End of range to search possible breakpoint locations in (excluding). When not specified, end of scripts is used as end of range.
     * @param restrictToFunction Only consider locations which are in the same (non-nested) function as start.
     * 
     * @return List of the possible breakpoint locations.
     */
    @Experimental
    @Returns("locations")
    List<BreakLocation> getPossibleBreakpoints(Location start, @Optional Location end,
            @Optional Boolean restrictToFunction);

    /**
     * Continues execution until specific location is reached.
     * 
     * @param location Location to continue to.
     */
    void continueToLocation(Location location);

    /**
     * Steps over the statement.
     */
    void stepOver();

    /**
     * Steps into the function call.
     */
    void stepInto();

    /**
     * Steps out of the function call.
     */
    void stepOut();

    /**
     * Stops on the next JavaScript statement.
     */
    void pause();

    /**
     * Steps into next scheduled async task if any is scheduled before next pause. Returns success when async task is actually scheduled, returns error if no task were scheduled or another scheduleStepIntoAsync was called.
     */
    @Experimental
    void scheduleStepIntoAsync();

    /**
     * Resumes JavaScript execution.
     */
    void resume();

    /**
     * Searches for given string in script content.
     * 
     * @param scriptId Id of the script to search in.
     * @param query String to search for.
     * @param caseSensitive If true, search is case sensitive.
     * @param isRegex If true, treats string parameter as regex.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInContent(String scriptId, String query,
            @Optional Boolean caseSensitive, @Optional Boolean isRegex);

    /**
     * Edits JavaScript source live.
     * 
     * @param scriptId Id of the script to edit.
     * @param scriptSource New content of the script.
     * @param dryRun  If true the change will not actually be applied. Dry run may be used to get result description without actually modifying the code.
     * 
     * @return SetScriptSourceResult
     */
    SetScriptSourceResult setScriptSource(String scriptId, String scriptSource,
            @Optional Boolean dryRun);

    /**
     * Restarts particular call frame from the beginning.
     * 
     * @param callFrameId Call frame identifier to evaluate on.
     * 
     * @return RestartFrameResult
     */
    RestartFrameResult restartFrame(String callFrameId);

    /**
     * Returns source for the script with given id.
     * 
     * @param scriptId Id of the script to get source for.
     * 
     * @return Script source.
     */
    @Returns("scriptSource")
    String getScriptSource(String scriptId);

    /**
     * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions or no exceptions. Initial pause on exceptions state is <tt>none</tt>.
     * 
     * @param state Pause on exceptions mode.
     */
    void setPauseOnExceptions(PauseOnExceptionState state);

    /**
     * Evaluates expression on a given call frame.
     * 
     * @param callFrameId Call frame identifier to evaluate on.
     * @param expression Expression to evaluate.
     * @param objectGroup String object group name to put result into (allows rapid releasing resulting object handles using <code>releaseObjectGroup</code>).
     * @param includeCommandLineAPI Specifies whether command line API should be available to the evaluated expression, defaults to false.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides <code>setPauseOnException</code> state.
     * @param returnByValue Whether the result is expected to be a JSON object that should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param throwOnSideEffect Whether to throw an exception if side effect cannot be ruled out during evaluation.
     * 
     * @return EvaluateOnCallFrameResult
     */
    EvaluateOnCallFrameResult evaluateOnCallFrame(String callFrameId, String expression,
            @Optional String objectGroup, @Optional Boolean includeCommandLineAPI,
            @Optional Boolean silent, @Optional Boolean returnByValue,
            @Experimental @Optional Boolean generatePreview,
            @Experimental @Optional Boolean throwOnSideEffect);

    /**
     * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
     * 
     * @param scopeNumber 0-based number of scope as was listed in scope chain. Only 'local', 'closure' and 'catch' scope types are allowed. Other scopes could be manipulated manually.
     * @param variableName Variable name.
     * @param newValue New variable value.
     * @param callFrameId Id of callframe that holds variable.
     */
    void setVariableValue(Integer scopeNumber, String variableName, CallArgument newValue,
            String callFrameId);

    /**
     * Enables or disables async call stacks tracking.
     * 
     * @param maxDepth Maximum depth of async call stacks. Setting to <code>0</code> will effectively disable collecting async call stacks (default).
     */
    void setAsyncCallStackDepth(Integer maxDepth);

    /**
     * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
     * 
     * @param patterns Array of regexps that will be used to check script url for blackbox state.
     */
    @Experimental
    void setBlackboxPatterns(List<String> patterns);

    /**
     * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing 'step in' several times, finally resorting to 'step out' if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn't blackboxed. Array should be sorted.
     * 
     * @param scriptId Id of the script.
     */
    @Experimental
    void setBlackboxedRanges(String scriptId, List<ScriptPosition> positions);

    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in <tt>locations</tt> property. Further matching script parsing will result in subsequent <tt>breakpointResolved</tt> events issued. This logical breakpoint will survive page reloads.
     * 
     * @param lineNumber Line number to set breakpoint at.
     * 
     * @return SetBreakpointByUrlResult
     */
    SetBreakpointByUrlResult setBreakpointByUrl(Integer lineNumber);

    /**
     * Sets JavaScript breakpoint at a given location.
     * 
     * @param location Location to set breakpoint in.
     * 
     * @return SetBreakpointResult
     */
    SetBreakpointResult setBreakpoint(Location location);

    /**
     * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
     * 
     * @param start Start of range to search possible breakpoint locations in.
     * 
     * @return List of the possible breakpoint locations.
     */
    @Experimental
    @Returns("locations")
    List<BreakLocation> getPossibleBreakpoints(Location start);

    /**
     * Searches for given string in script content.
     * 
     * @param scriptId Id of the script to search in.
     * @param query String to search for.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInContent(String scriptId, String query);

    /**
     * Edits JavaScript source live.
     * 
     * @param scriptId Id of the script to edit.
     * @param scriptSource New content of the script.
     * 
     * @return SetScriptSourceResult
     */
    SetScriptSourceResult setScriptSource(String scriptId, String scriptSource);

    /**
     * Evaluates expression on a given call frame.
     * 
     * @param callFrameId Call frame identifier to evaluate on.
     * @param expression Expression to evaluate.
     * 
     * @return EvaluateOnCallFrameResult
     */
    EvaluateOnCallFrameResult evaluateOnCallFrame(String callFrameId, String expression);
}
