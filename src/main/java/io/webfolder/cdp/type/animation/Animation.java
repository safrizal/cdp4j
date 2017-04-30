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
package io.webfolder.cdp.type.animation;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.AnimationType;

/**
 * Animation instance
 */
@Experimental
public class Animation {
    private String id;

    private String name;

    private Boolean pausedState;

    private String playState;

    private Double playbackRate;

    private Double startTime;

    private Double currentTime;

    private AnimationEffect source;

    private AnimationType type;

    private String cssId;

    /**
     * <code>Animation</code>'s id.
     */
    public String getId() {
        return id;
    }

    /**
     * <code>Animation</code>'s id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * <code>Animation</code>'s name.
     */
    public String getName() {
        return name;
    }

    /**
     * <code>Animation</code>'s name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <code>Animation</code>'s internal paused state.
     */
    public Boolean isPausedState() {
        return pausedState;
    }

    /**
     * <code>Animation</code>'s internal paused state.
     */
    public void setPausedState(Boolean pausedState) {
        this.pausedState = pausedState;
    }

    /**
     * <code>Animation</code>'s play state.
     */
    public String getPlayState() {
        return playState;
    }

    /**
     * <code>Animation</code>'s play state.
     */
    public void setPlayState(String playState) {
        this.playState = playState;
    }

    /**
     * <code>Animation</code>'s playback rate.
     */
    public Double getPlaybackRate() {
        return playbackRate;
    }

    /**
     * <code>Animation</code>'s playback rate.
     */
    public void setPlaybackRate(Double playbackRate) {
        this.playbackRate = playbackRate;
    }

    /**
     * <code>Animation</code>'s start time.
     */
    public Double getStartTime() {
        return startTime;
    }

    /**
     * <code>Animation</code>'s start time.
     */
    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    /**
     * <code>Animation</code>'s current time.
     */
    public Double getCurrentTime() {
        return currentTime;
    }

    /**
     * <code>Animation</code>'s current time.
     */
    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * <code>Animation</code>'s source animation node.
     */
    public AnimationEffect getSource() {
        return source;
    }

    /**
     * <code>Animation</code>'s source animation node.
     */
    public void setSource(AnimationEffect source) {
        this.source = source;
    }

    /**
     * Animation type of <code>Animation</code>.
     */
    public AnimationType getType() {
        return type;
    }

    /**
     * Animation type of <code>Animation</code>.
     */
    public void setType(AnimationType type) {
        this.type = type;
    }

    /**
     * A unique ID for <code>Animation</code> representing the sources that triggered this CSS animation/transition.
     */
    public String getCssId() {
        return cssId;
    }

    /**
     * A unique ID for <code>Animation</code> representing the sources that triggered this CSS animation/transition.
     */
    public void setCssId(String cssId) {
        this.cssId = cssId;
    }
}
