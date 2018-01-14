package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint;

import java.util.LinkedList;
import java.util.List;

public class TripleTargetConverter implements ContourToTargetConverter {
    @Override
    public List<Target> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings) {
        List<Target> targets = new LinkedList<>();
        // TODO
        return targets;
    }
}
