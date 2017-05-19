package com.kylecorry.frc.vision.camera

object CameraSpecs {
    object MicrosoftLifeCam {
        /**
         * The horizontal view angle of the Microsoft LifeCam in degrees.
         */
        val HORIZONTAL_VIEW_ANGLE = 50.466
    }

    /**
     * Calculate the focal length of the camera in pixels.

     * @param fullImageWidth      The width of the full resolution image of the camera in pixels.
     * *
     * @param horizontalViewAngle The horizontal view angle of the camera in degrees.
     * *
     * @return The focal length of the camera in pixels.
     */
    fun calculateFocalLengthPixels(fullImageWidth: Int, horizontalViewAngle: Double): Double {
        return fullImageWidth / (2 * Math.tan(Math.toRadians(horizontalViewAngle / 2)))
    }
}
