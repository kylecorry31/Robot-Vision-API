package com.kylecorry.frc.vision.pipeline;

public class TargetOutput {

    private double horizontalAngle, verticalAngle;

    private double percentArea;

    private double skew;

    public TargetOutput(double horizontalAngle, double verticalAngle, double percentArea, double skew) {
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.percentArea = percentArea;
        this.skew = skew;
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
