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
import io.webfolder.cdp.type.dom.Rect;
import io.webfolder.cdp.type.layertree.PictureTile;

@Experimental
@Domain("LayerTree")
public interface LayerTree {
    /**
     * Enables compositing tree inspection.
     */
    void enable();

    /**
     * Disables compositing tree inspection.
     */
    void disable();

    /**
     * Provides the reasons why the given layer was composited.
     * 
     * @param layerId The id of the layer for which we want to get the reasons it was composited.
     * 
     * @return A list of strings specifying reasons for the given layer to become composited.
     */
    @Returns("compositingReasons")
    List<String> compositingReasons(String layerId);

    /**
     * Returns the layer snapshot identifier.
     * 
     * @param layerId The id of the layer.
     * 
     * @return The id of the layer snapshot.
     */
    @Returns("snapshotId")
    String makeSnapshot(String layerId);

    /**
     * Returns the snapshot identifier.
     * 
     * @param tiles An array of tiles composing the snapshot.
     * 
     * @return The id of the snapshot.
     */
    @Returns("snapshotId")
    String loadSnapshot(List<PictureTile> tiles);

    /**
     * Releases layer snapshot captured by the back-end.
     * 
     * @param snapshotId The id of the layer snapshot.
     */
    void releaseSnapshot(String snapshotId);

    @Returns("timings")
    List<Double> profileSnapshot(String snapshotId, @Optional Integer minRepeatCount,
            @Optional Double minDuration, @Optional Rect clipRect);

    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     * 
     * @param snapshotId The id of the layer snapshot.
     * @param fromStep The first step to replay from (replay from the very start if not specified).
     * @param toStep The last step to replay to (replay till the end if not specified).
     * @param scale The scale to apply while replaying (defaults to 1).
     * 
     * @return A data: URL for resulting image.
     */
    @Returns("dataURL")
    String replaySnapshot(String snapshotId, @Optional Integer fromStep, @Optional Integer toStep,
            @Optional Double scale);

    @Returns("timings")
    List<Double> profileSnapshot(String snapshotId);

    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     * 
     * @param snapshotId The id of the layer snapshot.
     * 
     * @return A data: URL for resulting image.
     */
    @Returns("dataURL")
    String replaySnapshot(String snapshotId);
}
