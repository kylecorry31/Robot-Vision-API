package com.kylecorry.frc.vision.targeting;

import org.junit.Test;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;

import static org.junit.Assert.*;

public class TargetTest {

    @Test
    public void testFields(){
        Target target = new Target(10, 15, 12, -90, new RotatedRect(new Point(10, 10), new Size(10, 10), -90));

        assertEquals(10, target.getHorizontalAngle(), 0.0);
        assertEquals(15, target.getVerticalAngle(), 0.0);
        assertEquals(12, target.getPercentArea(), 0.0);
        assertEquals(-90, target.getSkew(), 0.0);
        assertEquals(new RotatedRect(new Point(10, 10), new Size(10, 10), -90), target.getBoundary());
    }

    @Test
    public void testEquals(){
        Target target = new Target(10, 15, 12, -90, new RotatedRect(new Point(10, 10), new Size(10, 10), -90));
        Target target1 = new Target(10, 15, 12, -90, new RotatedRect(new Point(10, 10), new Size(10, 10), -90));
        Target target2 = new Target(10, 8, 12, -90, new RotatedRect(new Point(10, 10), new Size(10, 10), -90));

        assertEquals(target, target1);
        assertNotEquals(target, target2);
    }

}