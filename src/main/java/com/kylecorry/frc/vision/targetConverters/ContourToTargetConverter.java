package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint;

import java.util.List;

/**
 * An interface for objects which convert contours to targets.
 */
public interface ContourToTargetConverter {

    /**
     * Convert contours to targets.
     * @param contours The contours containing targets.
     * @param cameraSettings The settings of the camera.
     * @return The targets.
     */
    List<Target> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings);

}
