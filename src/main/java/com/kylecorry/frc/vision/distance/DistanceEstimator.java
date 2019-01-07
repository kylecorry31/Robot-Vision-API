package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;

/**
 * An interface for objects which estimate a target's distance from the camera.
 */
public interface DistanceEstimator {

    /**
     * Get the distance to the target from the camera.
     * @param target The target.
     * @return The distance to the target from the camera - unit specified by developer.
     */
    double getDistance(Target target);

}
