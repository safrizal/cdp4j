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
import io.webfolder.cdp.type.runtime.AwaitPromiseResult;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.CompileScriptResult;
import io.webfolder.cdp.type.runtime.EvaluateResult;
import io.webfolder.cdp.type.runtime.GetPropertiesResult;
import io.webfolder.cdp.type.runtime.RunScriptResult;

/**
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects
 * Evaluation results are returned as mirror object that expose object type, string representation and unique identifier that can be used for further object reference
 * Original objects are maintained in memory unless they are either explicitly released or are released along with the other objects in their object group
 */
@Domain("Runtime")
public interface Runtime {
    /**
     * Evaluates expression on global object.
     * 
     * @param expression Expression to evaluate.
     * @param objectGroup Symbolic group name that can be used to release multiple objects.
     * @param includeCommandLineAPI Determines whether Command Line API should be available during the evaluation.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides <code>setPauseOnException</code> state.
     * @param contextId Specifies in which execution context to perform evaluation. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
     * @param returnByValue Whether the result is expected to be a JSON object that should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param userGesture Whether execution should be treated as initiated by user in the UI.
     * @param awaitPromise Whether execution should wait for promise to be resolved. If the result of evaluation is not a Promise, it's considered to be an error.
     * 
     * @return EvaluateResult
     */
    EvaluateResult evaluate(String expression, @Optional String objectGroup,
            @Optional Boolean includeCommandLineAPI, @Optional Boolean silent,
            @Optional Integer contextId, @Optional Boolean returnByValue,
            @Experimental @Optional Boolean generatePreview,
            @Experimental @Optional Boolean userGesture, @Optional Boolean awaitPromise);

    /**
     * Add handler to promise with given promise object id.
     * 
     * @param promiseObjectId Identifier of the promise.
     * @param returnByValue Whether the result is expected to be a JSON object that should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * 
     * @return AwaitPromiseResult
     */
    AwaitPromiseResult awaitPromise(String promiseObjectId, @Optional Boolean returnByValue,
            @Optional Boolean generatePreview);

    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     * 
     * @param objectId Identifier of the object to call function on.
     * @param functionDeclaration Declaration of the function to call.
     * @param arguments Call arguments. All call arguments must belong to the same JavaScript world as the target object.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides <code>setPauseOnException</code> state.
     * @param returnByValue Whether the result is expected to be a JSON object which should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param userGesture Whether execution should be treated as initiated by user in the UI.
     * @param awaitPromise Whether execution should wait for promise to be resolved. If the result of evaluation is not a Promise, it's considered to be an error.
     * 
     * @return CallFunctionOnResult
     */
    CallFunctionOnResult callFunctionOn(String objectId, String functionDeclaration,
            @Optional List<CallArgument> arguments, @Optional Boolean silent,
            @Optional Boolean returnByValue, @Experimental @Optional Boolean generatePreview,
            @Experimental @Optional Boolean userGesture, @Optional Boolean awaitPromise);

    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     * 
     * @param objectId Identifier of the object to return properties for.
     * @param ownProperties If true, returns properties belonging only to the element itself, not to its prototype chain.
     * @param accessorPropertiesOnly If true, returns accessor properties (with getter/setter) only; internal properties are not returned either.
     * @param generatePreview Whether preview should be generated for the results.
     * 
     * @return GetPropertiesResult
     */
    GetPropertiesResult getProperties(String objectId, @Optional Boolean ownProperties,
            @Experimental @Optional Boolean accessorPropertiesOnly,
            @Experimental @Optional Boolean generatePreview);

    /**
     * Releases remote object with given id.
     * 
     * @param objectId Identifier of the object to release.
     */
    void releaseObject(String objectId);

    /**
     * Releases all remote objects that belong to a given group.
     * 
     * @param objectGroup Symbolic object group name.
     */
    void releaseObjectGroup(String objectGroup);

    /**
     * Tells inspected instance to run if it was waiting for debugger to attach.
     */
    void runIfWaitingForDebugger();

    /**
     * Enables reporting of execution contexts creation by means of <tt>executionContextCreated</tt> event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
     */
    void enable();

    /**
     * Disables reporting of execution contexts creation.
     */
    void disable();

    /**
     * Discards collected exceptions and console API calls.
     */
    void discardConsoleEntries();

    @Experimental
    void setCustomObjectFormatterEnabled(Boolean enabled);

    /**
     * Compiles expression.
     * 
     * @param expression Expression to compile.
     * @param sourceURL Source url to be set for the script.
     * @param persistScript Specifies whether the compiled script should be persisted.
     * @param executionContextId Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
     * 
     * @return CompileScriptResult
     */
    CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript,
            @Optional Integer executionContextId);

    /**
     * Runs script with given id in a given context.
     * 
     * @param scriptId Id of the script to run.
     * @param executionContextId Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
     * @param objectGroup Symbolic group name that can be used to release multiple objects.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides <code>setPauseOnException</code> state.
     * @param includeCommandLineAPI Determines whether Command Line API should be available during the evaluation.
     * @param returnByValue Whether the result is expected to be a JSON object which should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param awaitPromise Whether execution should wait for promise to be resolved. If the result of evaluation is not a Promise, it's considered to be an error.
     * 
     * @return RunScriptResult
     */
    RunScriptResult runScript(String scriptId, @Optional Integer executionContextId,
            @Optional String objectGroup, @Optional Boolean silent,
            @Optional Boolean includeCommandLineAPI, @Optional Boolean returnByValue,
            @Optional Boolean generatePreview, @Optional Boolean awaitPromise);

    /**
     * Evaluates expression on global object.
     * 
     * @param expression Expression to evaluate.
     * 
     * @return EvaluateResult
     */
    EvaluateResult evaluate(String expression);

    /**
     * Add handler to promise with given promise object id.
     * 
     * @param promiseObjectId Identifier of the promise.
     * 
     * @return AwaitPromiseResult
     */
    AwaitPromiseResult awaitPromise(String promiseObjectId);

    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     * 
     * @param objectId Identifier of the object to call function on.
     * @param functionDeclaration Declaration of the function to call.
     * 
     * @return CallFunctionOnResult
     */
    CallFunctionOnResult callFunctionOn(String objectId, String functionDeclaration);

    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     * 
     * @param objectId Identifier of the object to return properties for.
     * 
     * @return GetPropertiesResult
     */
    GetPropertiesResult getProperties(String objectId);

    /**
     * Compiles expression.
     * 
     * @param expression Expression to compile.
     * @param sourceURL Source url to be set for the script.
     * @param persistScript Specifies whether the compiled script should be persisted.
     * 
     * @return CompileScriptResult
     */
    CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript);

    /**
     * Runs script with given id in a given context.
     * 
     * @param scriptId Id of the script to run.
     * 
     * @return RunScriptResult
     */
    RunScriptResult runScript(String scriptId);
}
