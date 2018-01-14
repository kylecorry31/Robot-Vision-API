package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.geometry.Point;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Size;

import static org.junit.Assert.*;

public class DoubleTargetTest {

    private SingleTarget target1, target2;
    private DoubleTarget target;

    @Before
    public void setup(){
        target1 = new SingleTarget(0.8, 10, 10, new Point(0, 0, 0), new Point(5, 5, 0), new Size(100, 100));
        target2 = new SingleTarget(0.8, 10, 20, new Point(20, 20, 0), new Point(25, 20, 0), new Size(100, 100));
        target = DoubleTarget.makeDoubleTarget(target1, target2, 0.8);
    }

    @Test
    public void testCenter(){
        assertEquals(new Point(15, 20, 0), target.getCenterPosition());
        assertEquals(new Point(15, 12.5, 0), target.getCenterOfMass());
    }

    @Test
    public void testDimensions(){
        assertEquals(40, target.getHeight(), 0.0);
        assertEquals(30, target.getWidth(), 0.0);
    }

    @Test
    public void testConfidence(){
        assertEquals(0.8, target.getIsTargetProbability(), 0.0);
    }

    @Test
    public void getTargets(){
        assertEquals(target1, target.getFirstTarget());
        assertEquals(target2, target.getSecondTarget());
    }


}