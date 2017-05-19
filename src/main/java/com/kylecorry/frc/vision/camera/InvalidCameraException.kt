package com.kylecorry.frc.vision.camera

/**
 * Created by Kylec on 2/19/2017.
 */
class InvalidCameraException(cameraName: String) : RuntimeException("Camera, $cameraName, is not in the MultiCameraSource")
