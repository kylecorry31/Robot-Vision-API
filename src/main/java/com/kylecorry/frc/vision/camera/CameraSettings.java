package com.kylecorry.frc.vision.camera;

import java.util.Objects;

public class CameraSettings {

    private boolean inverted;

    private int exposure;

    private FOV fov;

    private Resolution resolution;


    public CameraSettings(boolean inverted, int exposure, FOV fov, Resolution resolution) {
        this.inverted = inverted;
        this.exposure = exposure;
        this.fov = fov;
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

    public FOV getFov() {
        return fov;
    }

    public void setFov(FOV fov) {
        this.fov = fov;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "CameraSettings{" +
                "inverted=" + inverted +
                ", exposure=" + exposure +
                ", fov=" + fov +
                ", resolution=" + resolution +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraSettings settings = (CameraSettings) o;
        return inverted == settings.inverted &&
                exposure == settings.exposure &&
                Objects.equals(fov, settings.fov) &&
                Objects.equals(resolution, settings.resolution);
    }

    @Override
    public int hashCode() {

        return Objects.hash(inverted, exposure, fov, resolution);
    }
}
