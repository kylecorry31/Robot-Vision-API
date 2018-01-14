package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SingleTargetConverterTest {

    @Before
    public void setUp() throws Exception {
        OpenCVManager.getInstance().load(new SystemProperties());
    }

    @Test
    public void testTargets(){
        CameraSettings cameraSettings = new CameraSettings(false, new FOV(60, 60), new Resolution(640, 480));


        List<Point> points = Arrays.asList(new Point(10, 10), new Point(10, 20), new Point(20, 20), new Point(20, 10));
        MatOfPoint contour = new MatOfPoint();
        contour.fromList(points);

        List<Point> points2 = Arrays.asList(new Point(30, 30), new Point(30, 35), new Point(35, 35), new Point(35, 30));
        MatOfPoint contour2 = new MatOfPoint();
        contour2.fromList(points2);

        List<MatOfPoint> contours = Arrays.asList(contour, contour2);

        SingleTargetConverter targetConverter = new SingleTargetConverter();

        List<Target> targets = targetConverter.convertContours(contours, cameraSettings);

        assertEquals(2, targets.size());

        RotatedRect rect1 = new RotatedRect(new Point(15, 15), new Size(10, 10), -90);

        assertEquals(rect1.angle, targets.get(0).getBoundary().angle, 0.01);
        assertEquals(rect1.center, targets.get(0).getBoundary().center);
        assertEquals(rect1.size, targets.get(0).getBoundary().size);

        RotatedRect rect2 = new RotatedRect(new Point(32.5, 32.5), new Size(5, 5), -90);

        assertEquals(rect2.angle, targets.get(1).getBoundary().angle, 0.01);
        assertEquals(rect2.center, targets.get(1).getBoundary().center);
        assertEquals(rect2.size, targets.get(1).getBoundary().size);

    }

}