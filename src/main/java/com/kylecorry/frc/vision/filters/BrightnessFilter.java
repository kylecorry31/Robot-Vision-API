package com.kylecorry.frc.vision.filters;

import com.kylecorry.frc.vision.Range;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * A brightness filter.
 */
public class BrightnessFilter implements TargetFilter {

    private Range brightness;

    /**
     * Create a brightness filter.
     *
     * @param brightness The brightness from 0 - 255 inclusive.
     */
    public BrightnessFilter(Range brightness) {
        this.brightness = brightness;
    }

    /**
     * Create a brightness filter.
     *
     * @param minBrightness The min brightness 0 - 255 inclusive.
     * @param maxBrightness The max brightness 0 - 255 inclusive.
     */
    public BrightnessFilter(double minBrightness, double maxBrightness) {
        this.brightness = new Range(minBrightness, maxBrightness);
    }

    @Deprecated
    public Range getBrightness() {
        return brightness;
    }

    @Deprecated
    public void setBrightness(Range brightness) {
        this.brightness = brightness;
    }

    @Override
    public Mat filter(Mat image) {
        Mat out = new Mat();
        Imgproc.cvtColor(image, out, Imgproc.COLOR_BGR2GRAY);
        Scalar lower = new Scalar(brightness.getLow());
        Scalar upper = new Scalar(brightness.getHigh());
        Core.inRange(out, lower, upper, out);
        return out;
    }

}
