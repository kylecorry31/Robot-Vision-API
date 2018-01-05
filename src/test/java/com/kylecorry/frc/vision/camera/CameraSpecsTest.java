package com.kylecorry.frc.vision.camera;

import com.kylecorry.frc.vision.camera.CameraSpecs;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 3/26/2017.
 */
public class CameraSpecsTest {

    @Test
    public void testFocalLength(){
        assertEquals(554.3, CameraSpecs.calculateFocalLengthPixels(640, 60), 0.1);
    }

}