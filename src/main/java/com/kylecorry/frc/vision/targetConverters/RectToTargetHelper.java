package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targeting.Target;
import org.opencv.core.RotatedRect;

class RectToTargetHelper {
    private final double cameraWidth;
    private final double cameraHeight;
    private final double vpw;
    private final double vph;
    private final double imageArea;

    public RectToTargetHelper(CameraSettings cameraSettings){
         cameraWidth = cameraSettings.getResolution().getWidth();
         cameraHeight = cameraSettings.getResolution().getHeight();

        double horizontalFOV = Math.toRadians(cameraSettings.getFov().getHorizontalDegrees());
        double verticalFOV = Math.toRadians(cameraSettings.getFov().getVerticalDegrees());


        vpw = 2.0 * Math.tan(horizontalFOV / 2.0);
        vph = 2.0 * Math.tan(verticalFOV / 2.0);

        imageArea = cameraWidth * cameraHeight;
    }

    public Target convertRectToTarget(RotatedRect boundary){
        double nx = (2.0 / cameraWidth) * (boundary.center.x - cameraWidth / 2.0 - 0.5);
        double ny = (2.0 / cameraHeight) * (boundary.center.y - cameraHeight / 2.0 - 0.5);

        double x = vpw / 2.0 * nx;
        double y = vph / 2.0 * ny;

        double horizontalAngle = Math.toDegrees(Math.atan(x));
        double verticalAngle = -Math.toDegrees(Math.atan(y));

        double percentArea = boundary.size.area() / imageArea * 100.0;
        double skew = boundary.angle;
        if (boundary.size.width < boundary.size.height){
            skew = 90 + boundary.angle;
        }


        return new Target(horizontalAngle, verticalAngle, percentArea, skew, boundary);
    }

}
