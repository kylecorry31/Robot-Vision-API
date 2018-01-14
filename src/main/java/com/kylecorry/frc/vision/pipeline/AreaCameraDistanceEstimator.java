package com.kylecorry.frc.vision.pipeline;

public class AreaCameraDistanceEstimator implements DistanceEstimator {

    private AreaDistancePair close, far;

    public AreaCameraDistanceEstimator(AreaDistancePair close, AreaDistancePair far) {
        this.close = close;
        this.far = far;
    }

    @Override
    public double getDistance(TargetOutput target) {

        double minArea = far.areaPercentage;
        double maxArea = close.areaPercentage;

        double minDistance = close.distance;
        double maxDistance = far.distance;

        double areaNorm = normalize(target.getPercentArea(), minArea, maxArea);

        double distance = denormalize(areaNorm, minDistance, maxDistance);

        return distance;
    }


    private double normalize(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    private double denormalize(double value, double min, double max) {
        return (max - min) * value + min;
    }


    public static class AreaDistancePair {
        private double areaPercentage;
        private double distance;

        public AreaDistancePair(double areaPercentage, double distance) {
            this.areaPercentage = areaPercentage;
            this.distance = distance;
        }
    }
}
