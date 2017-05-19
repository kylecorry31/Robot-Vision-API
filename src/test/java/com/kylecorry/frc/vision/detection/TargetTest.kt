package com.kylecorry.frc.vision.detection

import com.kylecorry.geometry.Point
import org.junit.Test
import org.opencv.core.Size

import org.junit.Assert.*

/**
 * Created by Kylec on 3/26/2017.
 */
class TargetTest {

    @Test
    fun testAngle() {
        val target = Target(1.0, 10.0, 10.0, Point(40.0, 245.0, 0.0), Point(40.0, 245.0, 0.0), Size(640.0, 480.0))
        var angle = target.computeAngle(60.0)
        assertEquals(116.3, angle, 0.1)

        val target2 = Target(1.0, 10.0, 10.0, Point(315.0, 195.0, 0.0), Point(315.0, 195.0, 0.0), Size(640.0, 480.0))
        angle = target2.computeAngle(60.0)
        assertEquals(90.0, angle, 0.1)
    }

}