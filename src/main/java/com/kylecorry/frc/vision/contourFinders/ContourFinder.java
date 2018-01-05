package com.kylecorry.frc.vision.contourFinders;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.List;

public interface ContourFinder {
    List<MatOfPoint> findContours(Mat image);
}
