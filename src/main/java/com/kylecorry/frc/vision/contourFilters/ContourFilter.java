package com.kylecorry.frc.vision.contourFilters;

import org.opencv.core.MatOfPoint;

import java.util.List;

/**
 * An interface for objects which filter contour lists.
 */
public interface ContourFilter {
    /**
     * Filter a list of contours.
     * @param contours The contours to filter.
     * @return The filtered contours.
     */
    List<MatOfPoint> filterContours(List<MatOfPoint> contours);
}
