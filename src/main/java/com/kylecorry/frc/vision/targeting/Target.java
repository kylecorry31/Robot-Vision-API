package com.kylecorry.frc.vision.targeting;

import org.opencv.core.RotatedRect;

import java.util.Objects;

public class Target {

    private double horizontalAngle, verticalAngle;

    private double percentArea;

    private double skew;

    private RotatedRect boundary;

    public Target(double horizontalAngle, double verticalAngle, double percentArea, double skew, RotatedRect boundary) {
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.percentArea = percentArea;
        this.skew = skew;
        this.boundary = boundary;
    }

    public RotatedRect getBoundary() {
        return boundary;
    }

    public double getHorizontalAngle() {
        return horizontalAngle;
    }

    public double getVerticalAngle() {
        return verticalAngle;
    }

    public double getPercentArea() {
        return percentArea;
    }

    public void setSkew(double skew){
        this.skew = skew;
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
