package com.kylecorry.frc.vision.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.opencv.core.Scalar;

public class RGBFilter implements TargetFilter {

    private Range red;
    private Range green;
    private Range blue;

    public RGBFilter(Range red, Range green, Range blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public Mat filter(Mat image) {
        Mat out = new Mat();
        Scalar lower = new Scalar(blue.start, green.start, red.start);
        Scalar upper = new Scalar(blue.end, green.end, red.end);
        Core.inRange(image, lower, upper, out);
        return out;
    }

}
