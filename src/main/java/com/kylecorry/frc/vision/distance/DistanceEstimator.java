package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;

public interface DistanceEstimator {

    double getDistance(Target target);

}
