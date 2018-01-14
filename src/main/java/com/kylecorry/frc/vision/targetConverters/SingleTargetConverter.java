package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SingleTargetConverter implements ContourToTargetConverter {
    @Override
    public List<Target> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings) {
        List<Target> targets = new LinkedList<>();

        RectToTargetHelper rectToTargetHelper = new RectToTargetHelper(cameraSettings);

        for(MatOfPoint contour: contours){

            final RotatedRect boundary = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

            Target target = rectToTargetHelper.convertRectToTarget(boundary);

            targets.add(target);
        }

        targets.sort((target, t1) -> {
            if(target.getPercentArea() > t1.getPercentArea()){
                return -1;
            } else if(target.getPercentArea() == t1.getPercentArea()){
                return 0;
            } else {
                return 1;
            }
        });

        return targets;
    }
}
