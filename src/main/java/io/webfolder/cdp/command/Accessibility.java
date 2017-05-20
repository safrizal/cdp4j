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
import io.webfolder.cdp.type.accessibility.AXNode;

@Experimental
@Domain("Accessibility")
public interface Accessibility {
    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * 
     * @param nodeId ID of node to get the partial accessibility tree for.
     * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
     * 
     * @return The <code>Accessibility.AXNode</code> for this DOM node, if it exists, plus its ancestors, siblings and children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree(Integer nodeId, @Optional Boolean fetchRelatives);

    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * 
     * @param nodeId ID of node to get the partial accessibility tree for.
     * 
     * @return The <code>Accessibility.AXNode</code> for this DOM node, if it exists, plus its ancestors, siblings and children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree(Integer nodeId);
}
