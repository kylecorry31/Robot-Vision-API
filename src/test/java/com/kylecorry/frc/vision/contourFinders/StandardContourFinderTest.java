package com.kylecorry.frc.vision.contourFinders;

import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StandardContourFinderTest {

    private StandardContourFinder contourFinder;

    @Before
    public void setup(){
        OpenCVManager.getInstance().load(new SystemProperties());
    }

    @Test
    public void testFindExternalContours(){
        contourFinder = new StandardContourFinder();
        Mat image = Mat.zeros(100, 100, CvType.CV_8UC1);
        Imgproc.rectangle(image, new Point(10, 10), new Point(20, 20), Scalar.all(255));

        List<MatOfPoint> contours = contourFinder.findContours(image);

        List<Point> expected = Arrays.asList(new Point(10, 10), new Point(10, 20), new Point(20, 20), new Point(20, 10));

        assertEquals(1, contours.size());

        List<Point> contour = contours.get(0).toList();

        assertEquals(expected.size(), contour.size());

        for (Point point : expected) {
            assertTrue(contour.contains(point));
        }

    }

}