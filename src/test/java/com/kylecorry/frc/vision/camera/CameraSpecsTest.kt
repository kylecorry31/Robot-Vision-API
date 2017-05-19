package com.kylecorry.frc.vision.camera

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Kylec on 3/26/2017.
 */
class CameraSpecsTest {

    @Test
    fun testFocalLength() {
        assertEquals(554.3, CameraSpecs.calculateFocalLengthPixels(640, 60.0), 0.1)
    }

}