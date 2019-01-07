package com.kylecorry.frc.vision.filters;

import org.opencv.core.Mat;

/**
 * An interface for objects which filter an image.
 */
public interface TargetFilter {

    /**
     * Filter an image.
     * @param image The image to filter.
     * @return The filtered image.
     */
    Mat filter(Mat image);

}
