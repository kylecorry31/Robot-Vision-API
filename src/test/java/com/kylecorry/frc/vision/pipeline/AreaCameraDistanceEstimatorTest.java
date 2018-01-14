package com.kylecorry.frc.vision.pipeline;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaCameraDistanceEstimatorTest {

    private TargetOutput target;

    private DistanceEstimator distanceEstimator;

    @Before
    public void setUp() throws Exception {
        target = new TargetOutput(0, 10, 75, -90);
        distanceEstimator = new AreaCameraDistanceEstimator(new AreaCameraDistanceEstimator.AreaDistancePair(100, 0), new AreaCameraDistanceEstimator.AreaDistancePair(50, 10));
    }

    @Test
    public void getDistance() {
        assertEquals(5, distanceEstimator.getDistance(target), 0.001);
    }
}