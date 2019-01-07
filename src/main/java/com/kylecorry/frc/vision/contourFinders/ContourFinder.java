package com.kylecorry.frc.vision.contourFinders;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.List;

/**
 * An interface for contour finders.
 */
public interface ContourFinder {

    /**
     * Finds contours within an image.
     * @param image The image to find contours within.
     * @return The contours in the image.
     */
    List<MatOfPoint> findContours(Mat image);
}
