package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.geometry.Point;
import org.opencv.core.Size;

public class DoubleTarget extends Target {
    private SingleTarget first, second;

    public static DoubleTarget makeDoubleTarget(SingleTarget first, SingleTarget second, double confidence){
        double width = getFullWidth(first, second);
        double height = getFullHeight(first, second);
        Point position = getLeftMostPosition(first, second);
        Point centerOfMass = getFullCenterOfMass(first, second);
        Size imageSize = first.imageSize;
        return new DoubleTarget(first, second, confidence, width, height, position, centerOfMass, imageSize);
    }


    private DoubleTarget(SingleTarget first, SingleTarget second, double confidence, double width, double height, Point position, Point centerOfMass, Size imageSize) {
        super(confidence, width, height, position, centerOfMass, imageSize);
        this.first = first;
        this.second = second;
    }

    /**
     * Get the first target in the group.
     *
     * @return The first target.
     */
    public SingleTarget getFirstTarget() {
        return first;
    }

    /**
     * Get the second target in the group.
     *
     * @return The second target.
     */
    public SingleTarget getSecondTarget() {
        return second;
    }


    private static double getFullWidth(SingleTarget first, SingleTarget second) {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double maxRight = Math.max(first.getPosition().x + first.getWidth(),
                second.getPosition().x + second.getWidth());
        return maxRight - minLeft;
    }

    private static double getFullHeight(SingleTarget first, SingleTarget second) {
        double minTop = Math.min(first.getPosition().y, second.getPosition().y);
        double maxBottom = Math.max(first.getPosition().y + first.getHeight(),
                second.getPosition().y + second.getHeight());
        return maxBottom - minTop;
    }

    /**
     * Get the position of the top left pixel in the target group.
     *
     * @return The top left pixel position.
     */
    private static Point getLeftMostPosition(SingleTarget first, SingleTarget second) {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double minTop = Math.min(first.getPosition().y, second.getPosition().y);
        return new Point(minLeft, minTop, 0);
    }


    /**
     * Get the center of mass of the target group in pixels.
     *
     * @return The center of mass in pixels.
     */
    private static Point getFullCenterOfMass(SingleTarget first, SingleTarget second) {
        return new Point((first.getCenterOfMass().x + second.getCenterOfMass().x) / 2.0,
                (first.getCenterOfMass().y + second.getCenterOfMass().y) / 2.0, 0);
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
