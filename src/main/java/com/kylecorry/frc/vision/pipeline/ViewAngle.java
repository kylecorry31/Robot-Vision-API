package com.kylecorry.frc.vision.pipeline;

public class ViewAngle {

    private double horizontalDegrees, verticalDegrees;

    public ViewAngle(double horizontalDegrees, double verticalDegrees) {
        this.horizontalDegrees = horizontalDegrees;
        this.verticalDegrees = verticalDegrees;
    }

    public double getHorizontalDegrees() {
        return horizontalDegrees;
    }

    public void setHorizontalDegrees(double horizontalDegrees) {
        this.horizontalDegrees = horizontalDegrees;
    }

    public double getVerticalDegrees() {
        return verticalDegrees;
    }

    public void setVerticalDegrees(double verticalDegrees) {
        this.verticalDegrees = verticalDegrees;
    }
}
