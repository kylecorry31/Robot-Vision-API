package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;

public class FixedAngleCameraDistanceEstimator implements DistanceEstimator {

    private double targetHeight;
    private double cameraHeight;
    private double cameraMountingAngle;

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
