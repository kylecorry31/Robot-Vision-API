package com.kylecorry.frc.vision.camera;

import org.opencv.core.Mat;

/**
 * An interface to abstract away some of the features of cameras.
 */
public interface CameraSourceInterface {

    /**
     * Get the current image from the camera.
     *
     * @return The current image from the camera.
     */
    Mat getPicture();

    /**
     * Start the camera and target detection. A call to this method will start
     * the steam of the camera images to the Camera Server and will begin the
     * target detection processes.
     */
    void start();

    /**
     * Stop the camera from streaming and detecting targetDetection.
     */
    void stop();

    /**
     * Set the brightness of the camera from 0 to 100 inclusive.
     *
     * @param brightness The brightness of the camera.
     */
    void setBrightness(int brightness);

    /**
     * Set the exposure of the camera from 0 to 100 inclusive.
     *
     * @param exposure The exposure of the camera.
     */
    void setExposure(int exposure);


    /**
     * Allow the camera to choose its own exposure automatically.
     */
    void setExposureAuto();

    /**
     * Set the resolution of the camera.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     */
    void setResolution(int width, int height);

    /**
     * Set the FPS of the camera.
     *
     * @param fps The FPS of the camera.
     */
    void setFPS(int fps);
}
