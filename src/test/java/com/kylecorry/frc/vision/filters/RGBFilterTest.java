package com.kylecorry.frc.vision.filters;

import com.kylecorry.frc.vision.testUtils.OpenCVImageUtils;
import com.kylecorry.frc.vision.testUtils.OpenCVManager;
import com.kylecorry.frc.vision.testUtils.SystemProperties;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.opencv.core.Scalar;

import static org.junit.Assert.assertTrue;

public class RGBFilterTest {

    private RGBFilter filter;

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


        filter = new RGBFilter(new Range(1, 255), new Range(0, 0), new Range(0, 0));

        Mat red = filter.filter(image);

        Mat redRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        redRow.row(1).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(redRow, red));

        filter = new RGBFilter(new Range(0, 0), new Range(0, 0), new Range(1, 255));

        Mat blue = filter.filter(image);

        Mat blueRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        blueRow.row(0).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(blueRow, blue));

        filter = new RGBFilter(new Range(0, 0), new Range(1, 255), new Range(0, 0));

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
