package com.kylecorry.frc.vision.targeting;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.BrightnessFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import com.kylecorry.frc.vision.Range;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static org.junit.Assert.*;

public class TargetFinderTest {

    private TargetFinder targetFinder;

    @Before
    public void setUp() throws Exception {
        OpenCVManager.getInstance().load(new SystemProperties());
        TargetFilter filter = new BrightnessFilter(200, 255);
        ContourFilter contourFilter = new StandardContourFilter(new Range(0.03, 100), new Range(0, 100), new Range(0, 100), 640 * 480);
        CameraSettings cameraSettings = new CameraSettings(false, new FOV(60, 60), new Resolution(640, 480));

        targetFinder = new TargetFinder(cameraSettings, filter, contourFilter, TargetGrouping.SINGLE);

    }

    @Test
    public void findTargets() {

        Mat image = Mat.zeros(640, 480, CvType.CV_8UC3);
        Imgproc.rectangle(image, new Point(1, 1), new Point(2, 2), Scalar.all(255));
        Imgproc.rectangle(image, new Point(40, 245), new Point(50, 255), Scalar.all(255));

        List<Target> targets = targetFinder.findTargets(image);

        assertEquals(1, targets.size());

        assertEquals(-26.3472946, targets.get(0).getHorizontalAngle(), 0.15);

        assertEquals((10 * 10.0) / (640 * 480) * 100, targets.get(0).getPercentArea(), 0.005);

        assertEquals(-90.0, targets.get(0).getSkew(), 0.01);

        assertEquals(-1.4469, targets.get(0).getVerticalAngle(), 0.15);

        image.release();


    }
}