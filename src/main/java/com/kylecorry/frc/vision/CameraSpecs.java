package com.kylecorry.frc.vision;

public class CameraSpecs {
    public static class MicrosoftLifeCam {
        /**
         * The horizontal view angle of the Microsoft LifeCam in degrees.
         */
        public static final double HORIZONTAL_VIEW_ANGLE = 25.233 * 2;

        /**
         * The focal length of the Microsoft LifeCam in pixels.
         */
        public static final double FOCAL_LENGTH_PIXELS = 1358.0384;
    }

    /**
     * Calculate the focal length of the camera in pixels.
     *
     * @param fullImageWidth      The width of the full resolution image of the camera in pixels.
     * @param horizontalViewAngle The horizontal view angle of the camera in degrees.
     * @return The focal length of the camera in pixels.
     */
    public static double calculateFocalLengthPixels(int fullImageWidth, double horizontalViewAngle) {
        return fullImageWidth / (2 * Math.tan(Math.toRadians(horizontalViewAngle / 2)));
    }
}
