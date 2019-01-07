package com.kylecorry.frc.vision.targeting;

import org.opencv.core.RotatedRect;

import java.util.Objects;

/**
 * A representation of a vision target.
 */
public class Target {

    private double horizontalAngle, verticalAngle;

    private double percentArea;

    private double skew;

    private RotatedRect boundary;

    /**
     * Creates a target.
     * @param horizontalAngle The horizontal angle from the center of the camera to the center of the target in degrees.
     * @param verticalAngle The vertical angle from the center of the camera to the center of the target in degrees.
     * @param percentArea The percent area of the target to the image's area [0, 100] in percentage.
     * @param skew The skew of the target (rotation angle) in degrees [-90, 90].
     * @param boundary The bounding rotated rectangle of the target.
     */
    public Target(double horizontalAngle, double verticalAngle, double percentArea, double skew, RotatedRect boundary) {
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.percentArea = percentArea;
        this.skew = skew;
        this.boundary = boundary;
    }

    /**
     * Get the bounding box of the target.
     * @return The bounding box.
     */
    public RotatedRect getBoundary() {
        return boundary;
    }

    /**
     * Get the horizontal angle from the center of the camera to the center of the target in degrees.
     * @return The horizontal angle.
     */
    public double getHorizontalAngle() {
        return horizontalAngle;
    }

    /**
     * Get the vertical angle from the center of the camera to the center of the target in degrees.
     * @return The vertical angle.
     */
    public double getVerticalAngle() {
        return verticalAngle;
    }

    /**
     * Get the percent area of the target compared to the image in percentage.
     * @return The percent area.
     */
    public double getPercentArea() {
        return percentArea;
    }

    /**
     * Get the skew of the target in degrees, from -90 to 90 where negative values are angled to the left.
     * @return The skew in degrees.
     */
    public double getSkew() {
        return skew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Double.compare(target.horizontalAngle, horizontalAngle) == 0 &&
                Double.compare(target.verticalAngle, verticalAngle) == 0 &&
                Double.compare(target.percentArea, percentArea) == 0 &&
                Double.compare(target.skew, skew) == 0 &&
                Objects.equals(boundary, target.boundary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalAngle, verticalAngle, percentArea, skew, boundary);
    }

    @Override
    public String toString() {
        return "Target{" +
                "horizontalAngle=" + horizontalAngle +
                ", verticalAngle=" + verticalAngle +
                ", percentArea=" + percentArea +
                ", skew=" + skew +
                ", boundary=" + boundary +
                '}';
    }
}
