package com.kylecorry.frc.vision.targeting;

import org.opencv.core.RotatedRect;

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

    public double getSkew() {
        return skew;
    }
}
