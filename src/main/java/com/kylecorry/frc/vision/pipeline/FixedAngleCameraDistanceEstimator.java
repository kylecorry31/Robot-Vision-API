package com.kylecorry.frc.vision.pipeline;

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
    public double getDistance(TargetOutput target) {
        return (targetHeight - cameraHeight) / Math.tan(Math.toRadians(target.getVerticalAngle()) + Math.toRadians(cameraMountingAngle));
    }
}
