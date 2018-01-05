package com.kylecorry.frc.vision.contourFilters;

import org.opencv.core.MatOfPoint;

import java.util.List;

public interface ContourFilter {
    List<MatOfPoint> filterContours(List<MatOfPoint> contours);
}
