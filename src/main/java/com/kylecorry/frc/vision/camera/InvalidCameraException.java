package com.kylecorry.frc.vision.camera;

/**
 * Created by Kylec on 2/19/2017.
 */
public class InvalidCameraException extends RuntimeException {
    public InvalidCameraException(String cameraName) {
        super("Camera, " + cameraName + ", is not in the MultiCameraSource");
    }
}
