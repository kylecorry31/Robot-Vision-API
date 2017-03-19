package com.kylecorry.frc.vision;

import com.kylecorry.geometry.Point;

public class TargetGroup {
    private Target first, second;
    double confidence;

    /**
     * Create a TargetGroup consisting of the first and second target.
     *
     * @param first  The first target in the group.
     * @param second The second target in the group.
     */
    public TargetGroup(Target first, Target second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get the probability that this is the specified target group from 0 to 1
     * inclusive.
     *
     * @return The probability that this is the target group.
     */
    public double getIsTargetGroupProbability() {
        return confidence;
    }

    /**
     * Get the first target in the group.
     *
     * @return The first target.
     */
    public Target getFirstTarget() {
        return first;
    }

    /**
     * Get the second target in the group.
     *
     * @return The second target.
     */
    public Target getSecondTarget() {
        return second;
    }

    /**
     * Get the width of the target group.
     *
     * @return The group width.
     */
    public double getWidth() {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double maxRight = Math.max(first.getPosition().x + first.getWidth(),
                second.getPosition().x + second.getWidth());
        return maxRight - minLeft;
    }

    /**
     * Get the height of the target group.
     *
     * @return The group height.
     */
    public double getHeight() {
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
    public Point getPosition() {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double minTop = Math.min(first.getPosition().y, second.getPosition().y);
        return new Point(minLeft, minTop, 0);
    }

    /**
     * Get the position of the center of the target group in pixels.
     *
     * @return The center pixel position.
     */
    public Point getCenterPosition() {
        return new Point(getPosition().x + getWidth() / 2.0, getPosition().y + getHeight() / 2.0, 0);
    }

    /**
     * Compute the distance to the target group.
     *
     * @param imageWidth             The width of the image.
     * @param heightRelativeToCamera The height of the target relative to the camera (distance from camera to target along Y axis).
     * @param focalLengthPixels      The focal length of the camera in pixels.
     * @return The distance to the target in the same units as the targetActualWidth.
     */
    public double computeDistance(int imageWidth, double heightRelativeToCamera, double focalLengthPixels) {
        return focalLengthPixels * heightRelativeToCamera / (getCenterPosition().y - imageWidth / 2.0 + 0.5);
    }

    /**
     * Compute the angle to the target group from the center of the camera. This returns angle to the target from the coordinate frame placed on the camera.
     * So 0 is directly to the right of the camera, 180 is directly to the left, and 90 is directly ahead.
     * To convert it to allow for the left of center to be negative, and right of center to be positive subtract this angle from 90.
     *
     * @param imageWidth      The width of the image in pixels.
     * @param cameraViewAngle The view angle of the camera in degrees.
     * @return The angle to the target from the coordinate frame centered on the camera.
     */
    public double computeAngle(int imageWidth, double cameraViewAngle) {
        return 90 - (getCenterPosition().x - imageWidth / 2.0 + 0.5) / (imageWidth / 2 * Math.tan(Math.toRadians(cameraViewAngle / 2.0)));
    }
}
