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
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.applicationcache.FrameWithManifest;

@Experimental
@Domain("ApplicationCache")
public interface ApplicationCache {
    /**
     * Returns array of frame identifiers with manifest urls for each frame containing a document associated with some application cache.
     * 
     * @return Array of frame identifiers with manifest urls for each frame containing a document associated with some application cache.
     */
    @Returns("frameIds")
    List<FrameWithManifest> getFramesWithManifests();

    /**
     * Enables application cache domain notifications.
     */
    void enable();

    /**
     * Returns manifest URL for document in the given frame.
     * 
     * @param frameId Identifier of the frame containing document whose manifest is retrieved.
     * 
     * @return Manifest URL for document in the given frame.
     */
    @Returns("manifestURL")
    String getManifestForFrame(String frameId);

    /**
     * Returns relevant application cache data for the document in given frame.
     * 
     * @param frameId Identifier of the frame containing document whose application cache is retrieved.
     * 
     * @return Relevant application cache data for the document in given frame.
     */
    @Returns("applicationCache")
    io.webfolder.cdp.type.applicationcache.ApplicationCache getApplicationCacheForFrame(String frameId);
}
