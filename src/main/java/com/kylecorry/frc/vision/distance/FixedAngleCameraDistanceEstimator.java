package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;

/**
 * A class which uses a fixed angle camera to estimate distance.
 */
public class FixedAngleCameraDistanceEstimator implements DistanceEstimator {

    private double targetHeight;
    private double cameraHeight;
    private double cameraMountingAngle;

    /**
     * Creates an instance of a fixed angle distance estimator.
     * @param targetHeight The height of the target off the ground.
     * @param cameraHeight The height of the camera off the ground.
     * @param cameraMountingAngle The angle of the camera in degrees.
     */
    public FixedAngleCameraDistanceEstimator(double targetHeight, double cameraHeight, double cameraMountingAngle) {
        this.targetHeight = targetHeight;
        this.cameraHeight = cameraHeight;
        this.cameraMountingAngle = cameraMountingAngle;
    }

    @Override
    public double getDistance(Target target) {
        return (targetHeight - cameraHeight) / Math.tan(Math.toRadians(target.getVerticalAngle()) + Math.toRadians(cameraMountingAngle));
    }
}
