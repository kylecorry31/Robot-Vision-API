package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;

import java.util.LinkedList;
import java.util.List;

/**
 * Creates a combined target from targets. Uses the biggest two targets.
 */
public class TripleTargetConverter implements ContourToTargetConverter {
    @Override
    public List<Target> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings) {
        List<Target> targets = new LinkedList<>();
        List<Target> singleTargets = new SingleTargetConverter().convertContours(contours, cameraSettings);

        if(singleTargets.size() < 3){
            return targets;
        }

        Target target1 = singleTargets.get(0);
        Target target2 = singleTargets.get(1);
        Target target3 = singleTargets.get(2);

        targets.add(combineTargets(target1, target2, target3, cameraSettings));

        return targets;
    }

    private Target combineTargets(Target target1, Target target2, Target target3, CameraSettings cameraSettings){

        Point[] target1Points = new Point[12];

        target1.getBoundary().points(target1Points);

        Point[] target2Points = new Point[4];

        target2.getBoundary().points(target2Points);

        for (int i = 0; i < target2Points.length; i++){
            target1Points[4 + i] = target2Points[i];
        }

        Point[] target3Points = new Point[4];

        target3.getBoundary().points(target3Points);

        for (int i = 0; i < target3Points.length; i++){
            target1Points[8 + i] = target3Points[i];
        }


        MatOfPoint2f allPoints = new MatOfPoint2f(target1Points);


        RotatedRect combined = Imgproc.minAreaRect(allPoints);

        RectToTargetHelper rectToTargetHelper = new RectToTargetHelper(cameraSettings);

        return rectToTargetHelper.convertRectToTarget(combined);
    }
}
