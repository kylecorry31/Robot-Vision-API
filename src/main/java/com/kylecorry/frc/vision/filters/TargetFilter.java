package com.kylecorry.frc.vision.filters;

import org.opencv.core.Mat;

public interface TargetFilter {
    Mat filter(Mat image);
}
