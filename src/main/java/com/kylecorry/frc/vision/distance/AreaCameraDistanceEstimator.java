package com.kylecorry.frc.vision.distance;

import com.kylecorry.frc.vision.targeting.Target;

/**
 * A class which uses area to estimate distance.
 */
public class AreaCameraDistanceEstimator implements DistanceEstimator {

    private AreaDistancePair close, far;

    /**
     * Creates an instance of an area distance estimator.
     * @param close The percent of the area the target takes up at close range.
     * @param far The percent of the area the target takes up at far range.
     */
    public AreaCameraDistanceEstimator(AreaDistancePair close, AreaDistancePair far) {
        this.close = close;
        this.far = far;
    }

    @Override
    public double getDistance(Target target) {

        double minArea = far.areaPercentage;
        double maxArea = close.areaPercentage;

        double minDistance = close.distance;
        double maxDistance = far.distance;

        double areaNorm = normalize(target.getPercentArea(), minArea, maxArea);

        return denormalize(areaNorm, minDistance, maxDistance);
    }


    private double normalize(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    private double denormalize(double value, double min, double max) {
        return (max - min) * value + min;
    }

    /**
     * A class which represents a pair of area percentage and distance.
     */
    public static class AreaDistancePair {
        private double areaPercentage;
        private double distance;

        /**
         * Creates a representation of a target having a certain percent of the image area at a distance.
         * @param areaPercentage The percentage of the total image area from 0-100%.
         * @param distance The actual distance to the target from the camera in any unit.
         */
        public AreaDistancePair(double areaPercentage, double distance) {
            this.areaPercentage = areaPercentage;
            this.distance = distance;
        }
    }
}
