package com.kylecorry.frc.vision.targetConverters;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DoubleTargetConverterTest {

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

        List<Point> points2 = Arrays.asList(new Point(30, 10), new Point(30, 15), new Point(35, 15), new Point(35, 10));
        MatOfPoint contour2 = new MatOfPoint();
        contour2.fromList(points2);

        List<MatOfPoint> contours = Arrays.asList(contour, contour2);

        DoubleTargetConverter targetConverter = new DoubleTargetConverter();

        List<Target> targets = targetConverter.convertContours(contours, cameraSettings);

        assertEquals(1, targets.size());

        RotatedRect rect = new RotatedRect(new Point(22.5, 15), new Size(10, 25), -90);

        assertEquals(rect, targets.get(0).getBoundary());

    }

}