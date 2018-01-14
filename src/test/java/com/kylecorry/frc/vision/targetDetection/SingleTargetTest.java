package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.geometry.Point;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Size;

import static org.junit.Assert.*;

public class SingleTargetTest {

    private SingleTarget target;

    @Before
    public void setup(){
        target = new SingleTarget(0.8, 10, 20, new Point(0, 0, 0), new Point(5, 10, 0), new Size(100, 100));
    }

    @Test
    public void testCenter(){
        assertEquals(new Point(5, 10, 0), target.getCenterPosition());
        assertEquals(new Point(5, 10, 0), target.getCenterOfMass());
    }


    @Test
    public void testDimensions(){
        assertEquals(20, target.getHeight(), 0.0);
        assertEquals(10, target.getWidth(), 0.0);
    }

    @Test
    public void testConfidence(){
        assertEquals(0.8, target.getIsTargetProbability(), 0.0);
    }

}