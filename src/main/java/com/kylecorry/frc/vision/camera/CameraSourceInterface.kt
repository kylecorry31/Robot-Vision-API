package com.kylecorry.frc.vision.camera

import org.opencv.core.Mat

/**
 * An interface to abstract away some of the features of cameras.
 */
interface CameraSourceInterface {

    /**
     * Get the current image from the camera.

     * @return The current image from the camera.
     */
    val picture: Mat

    /**
     * Start the camera. A call to this method will start
     * the steam of the camera images to the Camera Server.
     */
    fun start()

    /**
     * Stop the camera from streaming.
     */
    fun stop()

    /**
     * Set the brightness of the camera from 0 to 100 inclusive.

     * @param brightness The brightness of the camera.
     */
    fun setBrightness(brightness: Int)

    /**
     * Set the exposure of the camera from 0 to 100 inclusive.

     * @param exposure The exposure of the camera.
     */
    fun setExposure(exposure: Int)


    /**
     * Allow the camera to choose its own exposure automatically.
     */
    fun setExposureAuto()

    /**
     * Set the resolution of the camera.

     * @param width  The width of the image.
     * *
     * @param height The height of the image.
     */
    fun setResolution(width: Int, height: Int)

    /**
     * Set the FPS of the camera.

     * @param fps The FPS of the camera.
     */
    fun setFPS(fps: Int)
}
