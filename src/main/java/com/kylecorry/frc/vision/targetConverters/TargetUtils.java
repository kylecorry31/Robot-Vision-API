package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;

/**
 * A class containing miscellaneous target utilities.
 */
public class TargetUtils {

    /**
     * Converts a rotated rectangle to a target.
     * @param rectangle The rectangle containing a target.
     * @param settings The settings of the camera.
     * @return The target.
     */
    public static Target rectangleToTarget(RotatedRect rectangle, CameraSettings settings){
        RectToTargetHelper rectToTargetHelper = new RectToTargetHelper(settings);
        return rectToTargetHelper.convertRectToTarget(rectangle);
    }

    /**
     * Combine two targets into a single target.
     * @param target1 The first target.
     * @param target2 The second target.
     * @param cameraSettings The settings of the camera.
     * @return The combined target.
     */
    public static Target combineTargets(Target target1, Target target2, CameraSettings cameraSettings){
        Point[] target1Points = new Point[8];
        target1.getBoundary().points(target1Points);
        Point[] target2Points = new Point[4];
        target2.getBoundary().points(target2Points);
        System.arraycopy(target2Points, 0, target1Points, 4, target2Points.length);
        MatOfPoint2f allPoints = new MatOfPoint2f(target1Points);
        RotatedRect combined = Imgproc.minAreaRect(allPoints);
        RectToTargetHelper rectToTargetHelper = new RectToTargetHelper(cameraSettings);
        return rectToTargetHelper.convertRectToTarget(combined);
    }

}
