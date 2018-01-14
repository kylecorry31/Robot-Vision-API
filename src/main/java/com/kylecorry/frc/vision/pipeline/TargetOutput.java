package com.kylecorry.frc.vision.pipeline;

import org.opencv.core.RotatedRect;

public class TargetOutput {

    private double horizontalAngle, verticalAngle;

    private double percentArea;

    private double skew;

    private RotatedRect rect;

    public TargetOutput(double horizontalAngle, double verticalAngle, double percentArea, double skew, RotatedRect rect) {
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.percentArea = percentArea;
        this.skew = skew;
        this.rect = rect;
    }

    public RotatedRect getRect() {
        return rect;
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
