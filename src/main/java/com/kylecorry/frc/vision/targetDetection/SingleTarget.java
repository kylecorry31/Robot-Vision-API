package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.geometry.Point;
import org.opencv.core.Size;

public class SingleTarget extends Target {

    /**
     * Create a target.
     *
     * @param confidence   The confidence that this is the real target from 0 to 1 inclusive.
     * @param width        The width of the target in pixels.
     * @param height       The height of the target in pixels.
     * @param position     The top left position in pixels.
     * @param centerOfMass The center of mass in pixels.
     * @param imageSize    The size of the image that the target was located in.
     */
    SingleTarget(double confidence, double width, double height, Point position, Point centerOfMass, Size imageSize) {
        super(confidence, width, height, position, centerOfMass, imageSize);
    }


}
