package com.kylecorry.frc.vision.contourFinders;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * A standard contour finder.
 */
public class StandardContourFinder implements ContourFinder {

    @Override
    public List<MatOfPoint> findContours(Mat image) {
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        int mode = Imgproc.RETR_EXTERNAL;
        int method = Imgproc.CHAIN_APPROX_SIMPLE;
        Imgproc.findContours(image, contours, hierarchy, mode, method);
        return contours;
    }
}
