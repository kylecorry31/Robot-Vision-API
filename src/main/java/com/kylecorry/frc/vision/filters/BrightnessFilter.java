package com.kylecorry.frc.vision.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class BrightnessFilter implements TargetFilter {

    private Range brightness;

    public BrightnessFilter(Range brightness) {
        this.brightness = brightness;
    }

    public BrightnessFilter(int minBrightness, int maxBrightness){
        this.brightness = new Range(minBrightness, maxBrightness);
    }

    @Override
    public Mat filter(Mat image) {
        Mat out = new Mat();
        Imgproc.cvtColor(image, out, Imgproc.COLOR_BGR2GRAY);
        Scalar lower = new Scalar(brightness.start);
        Scalar upper = new Scalar(brightness.end);
        Core.inRange(out, lower, upper, out);
        return out;
    }

}
