package com.kylecorry.frc.vision.camera;

import java.util.Objects;

public class FOV {

    private double horizontalDegrees, verticalDegrees;

    public FOV(double horizontalDegrees, double verticalDegrees) {
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
