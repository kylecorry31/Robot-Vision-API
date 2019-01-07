package com.kylecorry.frc.vision.camera;

import java.util.Objects;

/**
 * A class representation of a camera's field of view.
 */
public class FOV {

    private double horizontalDegrees, verticalDegrees;

    /**
     * Create an instance of the field of view. The field of view is considered the total change in angle from one side of the image to the other.
     * @param horizontalDegrees The horizontal field of view in degrees.
     * @param verticalDegrees The vertical field of view in degrees.
     */
    public FOV(double horizontalDegrees, double verticalDegrees) {
        this.horizontalDegrees = horizontalDegrees;
        this.verticalDegrees = verticalDegrees;
    }

    /**
     * Get the horizontal field of view in degrees.
     * @return The horizontal field of view.
     */
    public double getHorizontalDegrees() {
        return horizontalDegrees;
    }

    /**
     * Set the horizontal field of view in degrees.
     * @param horizontalDegrees The horizontal field of view.
     */
    public void setHorizontalDegrees(double horizontalDegrees) {
        this.horizontalDegrees = horizontalDegrees;
    }

    /**
     * Get the vertical field of view in degrees.
     * @return The vertical field of view.
     */
    public double getVerticalDegrees() {
        return verticalDegrees;
    }

    /**
     * Set the vertical field of view in degrees.
     * @param verticalDegrees The vertical field of view.
     */
    public void setVerticalDegrees(double verticalDegrees) {
        this.verticalDegrees = verticalDegrees;
    }

    @Override
    public String toString() {
        return "FOV{" +
                "horizontalDegrees=" + horizontalDegrees +
                ", verticalDegrees=" + verticalDegrees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FOV fov = (FOV) o;
        return Double.compare(fov.horizontalDegrees, horizontalDegrees) == 0 &&
                Double.compare(fov.verticalDegrees, verticalDegrees) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalDegrees, verticalDegrees);
    }
}
