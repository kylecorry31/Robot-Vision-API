package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint;

import java.util.List;

public interface ContourToTargetConverter {

    List<Target> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings);

}
