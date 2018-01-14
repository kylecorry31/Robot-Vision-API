package com.kylecorry.frc.vision.pipeline;

import org.opencv.core.MatOfPoint;

import java.util.List;

public interface ContourToTargetConverter {

    List<TargetOutput> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings);

}
