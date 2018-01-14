package com.kylecorry.frc.vision.pipeline;

public class CameraSettings {

    private boolean inverted;

    private int exposure;

    private ViewAngle viewAngle;

    private Resolution resolution;


    public CameraSettings(boolean inverted, int exposure, ViewAngle viewAngle, Resolution resolution) {
        this.inverted = inverted;
        this.exposure = exposure;
        this.viewAngle = viewAngle;
        this.resolution = resolution;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public int getExposure() {
        return exposure;
    }

    public void setExposure(int exposure) {
        this.exposure = exposure;
    }

    public ViewAngle getViewAngle() {
        return viewAngle;
    }

    public void setViewAngle(ViewAngle viewAngle) {
        this.viewAngle = viewAngle;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
}
