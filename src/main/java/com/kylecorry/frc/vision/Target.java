package com.kylecorry.frc.vision;

import com.kylecorry.geometry.Point;
import org.opencv.core.Size;

public class Target {
    private double confidence;
    private double width, height;
    private Point position;
    private Point centerOfMass;
    Size imageSize;

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

    /**
     * Get the center of mass of the target in pixels.
     *
     * @return The center of mass in pixels.
     */
    public Point getCenterOfMass() {
        return centerOfMass;
    }

    /**
     * Get how confident that this object is the specified target.
     *
     * @return The confidence from 0 to 1 inclusive.
     */
    public double getIsTargetProbability() {
        return confidence;
    }

    /**
     * Get the width in pixels of the target's bounding box.
     *
     * @return The width of the target in pixels.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the height in pixels of the target's bounding box.
     *
     * @return The height of the target in pixels.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the position of the top left point of the target's bounding box.
     *
     * @return The target's top left point in pixels.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Get the position of the center of the target's bounding box.
     *
     * @return The target's center point in pixels.
     */
    public Point getCenterPosition() {
        return new Point(getPosition().x + getWidth() / 2.0, getPosition().y + getHeight() / 2.0, 0);
    }

    /**
     * Compute the distance to the target.
     *
     * @param heightRelativeToCamera The height of the target relative to the camera (distance from camera to target along Y axis).
     * @param horizontalViewAngle    The horizontal view angle in degrees.
     * @return The distance to the target in the same units as the heightRelativeToCamera.
     */
    public double computeDistance(double heightRelativeToCamera, double horizontalViewAngle) {
        return -CameraSpecs.calculateFocalLengthPixels((int) imageSize.width, horizontalViewAngle) * heightRelativeToCamera / (getCenterPosition().y - imageSize.height / 2.0 + 0.5);
    }

    /**
     * Compute the yaw angle to the target from the center of the camera. This returns angle to the target from the coordinate frame placed on the camera.
     * So 0 is directly to the right of the camera, 180 is directly to the left, and 90 is directly ahead.
     * To convert it to allow for the left of center to be negative, and right of center to be positive subtract this angle from 90.
     *
     * @param horizontalViewAngle The horizontal view angle in degrees.
     * @return The angle to the target from the coordinate frame centered on the camera.
     */
    public double computeAngle(double horizontalViewAngle) {
        return 90 - Math.toDegrees(Math.atan((getCenterPosition().x - imageSize.width / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels((int) imageSize.width, horizontalViewAngle)));
    }

    /**
     * Compute the coordinates of the target in 3D space.
     *
     * @param heightRelativeToCamera The height of the target relative to the camera (distance from camera to target along Y axis).
     * @param horizontalViewAngle    The horizontal view angle in degrees.
     * @return The coordinates of the target in the same units as the heightRelativeToCamera.
     */
    public Point computeCoordinates(double heightRelativeToCamera, double horizontalViewAngle) {
        double distance = computeDistance(heightRelativeToCamera, horizontalViewAngle);
        double x = distance * (getCenterPosition().x - imageSize.width / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels((int) imageSize.width, horizontalViewAngle);
        double y = -distance * (getCenterPosition().y - imageSize.height / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels((int) imageSize.width, horizontalViewAngle);
        return new Point(x, y, distance);
    }

}
