package com.kylecorry.frc.vision.camera;

import java.util.Objects;

/**
 * A class which represents camera settings which are needed for targeting.
 */
public class CameraSettings {

    private boolean inverted;

    private FOV fov;

    private Resolution resolution;


    /**
     * Creates an instance of the CameraSettings class.
     * @param inverted Whether the camera is upside-down.
     * @param fov The field of view of the camera in degrees.
     * @param resolution The resolution of the camera in pixels.
     */
    public CameraSettings(boolean inverted, FOV fov, Resolution resolution) {
        this.inverted = inverted;
        this.fov = fov;
        this.resolution = resolution;
    }

    /**
     * Determines whether the camera is upside-down.
     * @return True if the camera is upside-down.
     */
    public boolean isInverted() {
        return inverted;
    }

    /**
     * Set if the camera is upside-down.
     * @param inverted True if the camera is upside-down.
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }


    /**
     * Get the field of view of the camera in degrees.
     * @return The field of view in degrees.
     */
    public FOV getFov() {
        return fov;
    }

    /**
     * Set the field of view of the camera in degrees.
     * @param fov The field of view in degrees.
     */
    public void setFov(FOV fov) {
        this.fov = fov;
    }

    /**
     * Get the resolution of the camera's image in pixels.
     * @return The resolution of the image.
     */
    public Resolution getResolution() {
        return resolution;
    }

    /**
     * Set the resolution of the camera's image in pixels.
     * @param resolution  The resolution of the image.
     */
    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "CameraSettings{" +
                "inverted=" + inverted +
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
                Objects.equals(fov, settings.fov) &&
                Objects.equals(resolution, settings.resolution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inverted, fov, resolution);
    }
}
