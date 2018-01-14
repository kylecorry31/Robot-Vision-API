package com.kylecorry.frc.vision.camera;

import org.junit.Test;

import static org.junit.Assert.*;

public class FOVTest {

    @Test
    public void testDegrees() {
        FOV fov = new FOV(10, 20);
        assertEquals(10, fov.getHorizontalDegrees(), 0.0);
        assertEquals(20, fov.getVerticalDegrees(), 0.0);
    }

    @Test
    public void testEquals(){
        FOV fov = new FOV(10, 20);
        FOV fov1 = new FOV(10, 20);
        FOV fov2 = new FOV(11, 20);

        assertEquals(fov, fov1);
        assertNotEquals(fov, fov2);
    }
}