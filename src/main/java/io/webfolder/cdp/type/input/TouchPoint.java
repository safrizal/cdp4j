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
package io.webfolder.cdp.type.input;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.TouchPointState;

@Experimental
public class TouchPoint {
    private TouchPointState state;

    private Integer x;

    private Integer y;

    private Integer radiusX;

    private Integer radiusY;

    private Double rotationAngle;

    private Double force;

    private Double id;

    /**
     * State of the touch point.
     */
    public TouchPointState getState() {
        return state;
    }

    /**
     * State of the touch point.
     */
    public void setState(TouchPointState state) {
        this.state = state;
    }

    /**
     * X coordinate of the event relative to the main frame's viewport.
     */
    public Integer getX() {
        return x;
    }

    /**
     * X coordinate of the event relative to the main frame's viewport.
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Y coordinate of the event relative to the main frame's viewport. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public Integer getY() {
        return y;
    }

    /**
     * Y coordinate of the event relative to the main frame's viewport. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * X radius of the touch area (default: 1).
     */
    public Integer getRadiusX() {
        return radiusX;
    }

    /**
     * X radius of the touch area (default: 1).
     */
    public void setRadiusX(Integer radiusX) {
        this.radiusX = radiusX;
    }

    /**
     * Y radius of the touch area (default: 1).
     */
    public Integer getRadiusY() {
        return radiusY;
    }

    /**
     * Y radius of the touch area (default: 1).
     */
    public void setRadiusY(Integer radiusY) {
        this.radiusY = radiusY;
    }

    /**
     * Rotation angle (default: 0.0).
     */
    public Double getRotationAngle() {
        return rotationAngle;
    }

    /**
     * Rotation angle (default: 0.0).
     */
    public void setRotationAngle(Double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    /**
     * Force (default: 1.0).
     */
    public Double getForce() {
        return force;
    }

    /**
     * Force (default: 1.0).
     */
    public void setForce(Double force) {
        this.force = force;
    }

    /**
     * Identifier used to track touch sources between events, must be unique within an event.
     */
    public Double getId() {
        return id;
    }

    /**
     * Identifier used to track touch sources between events, must be unique within an event.
     */
    public void setId(Double id) {
        this.id = id;
    }
}
