package com.kylecorry.frc.vision;

import com.kylecorry.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 3/26/2017.
 */
public class TargetTest {

    @Test
    public void testAngle() {
        Target target = new Target(1, 10, 10, new Point(40, 245, 0));
        double angle = target.computeAngle(640, 60);
        assertEquals(116.3, angle, 0.1);

        Target target2 = new Target(1, 10, 10, new Point(315, 195, 0));
        angle = target2.computeAngle(640, 60);
        assertEquals(90, angle, 0.1);
    }

}