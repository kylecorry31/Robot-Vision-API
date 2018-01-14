package com.kylecorry.frc.vision.camera;

import static org.junit.Assert.*;

import org.junit.Test;

public class CameraSettingsTest {

    @Test
    public void testSettings() {
        boolean inverted = false;
        FOV fov = new FOV(60, 50);
        Resolution resolution = new Resolution(320, 240);
        int exposure = 2;
        CameraSettings settings = new CameraSettings(inverted, fov, resolution);

        assertEquals(inverted, settings.isInverted());
        assertEquals(fov, settings.getFov());
        assertEquals(resolution, settings.getResolution());

    }

}