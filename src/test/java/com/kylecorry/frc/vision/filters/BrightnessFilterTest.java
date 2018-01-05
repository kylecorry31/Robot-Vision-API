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

public class BrightnessFilterTest {

    private BrightnessFilter filter;

    @Before
    public void setup(){
        OpenCVManager.getInstance().load(new SystemProperties());
    }

    @Test
    public void testFilter() {
        Mat image = Mat.zeros(4, 4, CvType.CV_8UC3);
        image.row(0).setTo(new Scalar(255, 255, 255));
        image.row(1).setTo(new Scalar(127, 127, 127));
        image.row(2).setTo(new Scalar(0, 90, 0));


        filter = new BrightnessFilter(new Range(0, 255));

        Mat all = filter.filter(image);

        Mat allRows = Mat.zeros(4, 4, CvType.CV_8UC1);
        allRows.setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(allRows, all));

        filter = new BrightnessFilter(new Range(0, 0));

        Mat zeros = filter.filter(image);

        Mat zeroRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        zeroRow.row(3).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(zeroRow, zeros));

        filter = new BrightnessFilter(new Range(127, 127));

        Mat mids = filter.filter(image);

        Mat midRow = Mat.zeros(4, 4, CvType.CV_8UC1);
        midRow.row(1).setTo(Scalar.all(255));

        assertTrue(OpenCVImageUtils.matEquals(mids, midRow));

        all.release();
        zeros.release();
        mids.release();
        allRows.release();
        zeroRow.release();
        midRow.release();
        image.release();


    }
}
