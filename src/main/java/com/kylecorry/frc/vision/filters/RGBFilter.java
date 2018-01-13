package com.kylecorry.frc.vision.filters;

import com.kylecorry.geometry.Range;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class RGBFilter implements TargetFilter {

    private Range red;
    private Range green;
    private Range blue;

    /**
     * Create an RGB filter.
     * @param red The red value from 0 - 255 inclusive.
     * @param green The green value from 0 - 255 inclusive.
     * @param blue The blue value from 0 - 255 inclusive.
     */
    public RGBFilter(Range red, Range green, Range blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public Mat filter(Mat image) {
        Mat out = new Mat();
        Scalar lower = new Scalar(blue.getLow(), green.getLow(), red.getLow());
        Scalar upper = new Scalar(blue.getHigh(), green.getHigh(), red.getHigh());
        Core.inRange(image, lower, upper, out);
        return out;
    }

}
