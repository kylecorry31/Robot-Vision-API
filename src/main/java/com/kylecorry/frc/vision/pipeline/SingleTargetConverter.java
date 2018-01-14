package com.kylecorry.frc.vision.pipeline;

import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SingleTargetConverter implements ContourToTargetConverter {
    @Override
    public List<TargetOutput> convertContours(List<MatOfPoint> contours, CameraSettings cameraSettings) {
        List<TargetOutput> targets = new LinkedList<>();

        final double cameraWidth = cameraSettings.getResolution().getWidth();
        final double cameraHeight = cameraSettings.getResolution().getHeight();

        final double horizontalFOV = Math.toRadians(cameraSettings.getViewAngle().getHorizontalDegrees());
        final double verticalFOV = Math.toRadians(cameraSettings.getViewAngle().getVerticalDegrees());


        final double vpw = 2.0 * Math.tan(horizontalFOV / 2.0);
        final double vph = 2.0 * Math.tan(verticalFOV / 2.0);

        final double imageArea = cameraWidth * cameraHeight;

        for(MatOfPoint contour: contours){

            final RotatedRect boundary = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

            double nx = (2.0 / cameraWidth) * (boundary.center.x - cameraWidth / 2.0 - 0.5);
            double ny = (2.0 / cameraHeight) * (boundary.center.y - cameraHeight / 2.0 - 0.5);

            double x = vpw / 2.0 * nx;
            double y = vph / 2.0 * ny;

            double horizontalAngle = Math.toDegrees(Math.atan(x));
            double verticalAngle = -Math.toDegrees(Math.atan(y));

            double percentArea = boundary.size.area() / imageArea * 100.0;
            double skew = boundary.angle;


            TargetOutput targetOutput = new TargetOutput(horizontalAngle, verticalAngle, percentArea, skew, boundary);
            targets.add(targetOutput);
        }

        targets.sort(Comparator.comparingDouble(TargetOutput::getPercentArea));

        return targets;
    }
}
