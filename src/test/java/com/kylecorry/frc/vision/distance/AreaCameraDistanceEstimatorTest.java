package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaCameraDistanceEstimatorTest {

    private Target target;

    private DistanceEstimator distanceEstimator;

    @Before
    public void setUp() throws Exception {
        target = new Target(0, 10, 75, -90, null);
        distanceEstimator = new AreaCameraDistanceEstimator(new AreaCameraDistanceEstimator.AreaDistancePair(100, 0), new AreaCameraDistanceEstimator.AreaDistancePair(50, 10));
    }

    @Test
    public void getDistance() {
        assertEquals(5, distanceEstimator.getDistance(target), 0.001);
    }
}