package com.kylecorry.frc.vision.examples;

import com.kylecorry.frc.vision.Range;
import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.targetConverters.TargetUtils;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.targeting.TargetFinder;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Test;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FRCExample2019 {

    private final static double TARGET_HEIGHT = 5.5;
    private final static boolean SHOULD_DRAW = true;


    @Test
    public void test(){
        // The following 3 lines are for desktop usage, assign the Mat image to the camera image when deploying to a robot
        OpenCVManager.getInstance().load(new SystemProperties());
        URL imgURL = getClass().getResource("/frc-vision-target-2019-1.JPG"); // Change this to test other images
        Mat image = Imgcodecs.imread(imgURL.getFile());

        // This uses a sample image, which has been edited to make the HSV easier to see for this examples (targets painted green)
        Resolution resolution = new Resolution(720, 478);
        CameraSettings cameraSettings = new CameraSettings(false, new FOV(50, 40), resolution);
        TargetFilter targetFilter = new HSVFilter(new Range(50, 70), new Range(100, 255), new Range(100, 255));
        ContourFilter contourFilter = new StandardContourFilter(new Range(0.03, 100), new Range(0, 100), new Range(0.2, 4), resolution.getArea());
        TargetGrouping targetGrouping = TargetGrouping.SINGLE;

        TargetFinder targetFinder = new TargetFinder(cameraSettings, targetFilter, contourFilter, targetGrouping);

        // Find targets
        List<Target> targets = targetFinder.findTargets(image);

        // Draw the target center points
        if (SHOULD_DRAW) {
            for (Target target : targets) {
                Imgproc.drawMarker(image, target.getBoundary().center, new Scalar(0, 0, 255), Core.TYPE_GENERAL, 12, 2);
            }
        }

        List<Target> bays = new ArrayList<>();

        // Sort the targets by x coordinates
        targets.sort(Comparator.comparingDouble(target -> target.getBoundary().center.x));

        // If the current target is a left and the next is a right, make it a pair
        for (int i = 0; i < targets.size() - 1; i++) {
            Target current = targets.get(i);
            Target next = targets.get(i + 1);

            // Determine if the targets are a left and right pair -> you may want to verify that they are close enough as well
            if (isLeftTarget(current) && isRightTarget(next)){
                // Combine the targets
                Target bay = TargetUtils.combineTargets(current, next, cameraSettings);
                bays.add(bay);
                // Skip the next target
                i++;
            }
        }

        if (SHOULD_DRAW) {
            // Draw bays
            for (Target bay : bays) {
                drawBay(image, bay);
            }

            // The following line is for desktop testing, use the CameraServer to display the image on a robot
            Imgcodecs.imwrite("frc-vision-target-2019-output.jpg", image);
        }
    }

    /**
     * Draw the bay
     * @param image The image to draw on.
     * @param bay The vision targets above the bay.
     */
    private void drawBay(Mat image, Target bay){
        RotatedRect boundary = bay.getBoundary();

        double height = boundary.boundingRect().height;
        double verticalPPI = height / TARGET_HEIGHT;
        double holeYDist = 8.25;
        double centerY = boundary.center.y + holeYDist * verticalPPI;

        Imgproc.circle(image, new Point(boundary.center.x, centerY), boundary.boundingRect().width/2, new Scalar(255, 0, 255), 4);
        Imgproc.drawMarker(image, new Point(boundary.center.x, centerY), new Scalar(255, 0, 255), Core.TYPE_GENERAL, 30, 2);
    }

    /**
     * Determines if a target is a left vision target.
     * @param target The target.
     * @return True if it is a left target.
     */
    private boolean isLeftTarget(Target target){
        return target.getSkew() < 0;
    }

    /**
     * Determines if a target is a right vision target.
     * @param target The target.
     * @return True if it is a right target.
     */
    private boolean isRightTarget(Target target){
        return target.getSkew() > 0;
    }

}
