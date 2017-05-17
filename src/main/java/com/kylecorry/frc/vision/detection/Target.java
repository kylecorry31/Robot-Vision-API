package com.kylecorry.frc.vision.detection;

import com.kylecorry.geometry.Point;
import org.opencv.core.Size;

public class Target extends AbstractTarget {
    private double width, height;
    private Point position;
    private Point centerOfMass;

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
    Target(double confidence, double width, double height, Point position, Point centerOfMass, Size imageSize) {
        this.confidence = confidence;
        this.width = width;
        this.height = height;
        this.position = position;
        this.imageSize = imageSize;
        this.centerOfMass = centerOfMass;
    }

    public Point getCenterOfMass() {
        return centerOfMass;
    }

    public double getProbability() {
        return confidence;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }
}
