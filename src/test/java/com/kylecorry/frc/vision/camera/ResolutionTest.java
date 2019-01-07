package com.kylecorry.frc.vision.camera;

import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Test;
import org.opencv.core.Mat;

import static org.junit.Assert.*;

public class ResolutionTest {

    @Test
    public void testResolution(){
        Resolution resolution = new Resolution(320, 240);
        assertEquals(320, resolution.getWidth());
        assertEquals(240, resolution.getHeight());
    }

    @Test
    public void testEquals(){
        Resolution resolution = new Resolution(320, 240);
        Resolution resolution1 = new Resolution(320, 240);
        Resolution resolution2 = new Resolution(640, 480);

        assertEquals(resolution, resolution1);
        assertNotEquals(resolution, resolution2);
    }

    @Test
    public void testArea(){
        Resolution resolution = new Resolution(320, 240);
        Resolution resolution2 = new Resolution(10, 10);

        assertEquals(320 * 240, resolution.getArea());
        assertEquals(100, resolution2.getArea());
    }


    @Test
    public void testScale(){
        Resolution resolution = new Resolution(640, 480);

        assertEquals(resolution, resolution.scaleBy(1));
        assertEquals(new Resolution(320, 240), resolution.scaleBy(0.5));
        assertEquals(new Resolution(1280, 960), resolution.scaleBy(2));
    }

    @Test
    public void testFromMat(){
        OpenCVManager.getInstance().load(new SystemProperties());
        Mat mat = Mat.zeros(10, 20, 0);

        assertEquals(new Resolution(20, 10), Resolution.fromMat(mat));

        mat.release();
    }

}