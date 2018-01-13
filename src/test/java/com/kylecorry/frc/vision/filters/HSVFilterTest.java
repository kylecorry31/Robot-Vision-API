package com.kylecorry.frc.vision.filters;

import com.kylecorry.frc.vision.testUtils.OpenCVImageUtils;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import com.kylecorry.geometry.Range;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import static org.junit.Assert.*;

public class HSVFilterTest {

    private HSVFilter filter;

    @Before
    public void setup(){
        OpenCVManager.getInstance().load(new SystemProperties());
    }

    @Test
    public void testFilter() {
        Mat image = Mat.zeros(4, 4, CvType.CV_8UC3);
        image.row(0).setTo(new Scalar(255, 0, 0));
        image.row(1).setTo(new Scalar(0, 0, 255));
        image.row(2).setTo(new Scalar(0, 255, 0));


        filter = new HSVFilter(new Range(0, 10), new Range(0, 255), new Range(1, 255));

        Mat red = filter.filter(image);

        Mat redRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        redRow.row(1).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(redRow, red));

        filter = new HSVFilter(new Range(115, 125), new Range(0, 255), new Range(1, 255));

        Mat blue = filter.filter(image);

        Mat blueRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        blueRow.row(0).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(blueRow, blue));

        filter = new HSVFilter(new Range(55, 65), new Range(0, 255), new Range(1, 255));

        Mat green = filter.filter(image);

        Mat greenRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        greenRow.row(2).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(greenRow, green));

        red.release();
        blue.release();
        green.release();
        redRow.release();
        blueRow.release();
        greenRow.release();
        image.release();


    }
}