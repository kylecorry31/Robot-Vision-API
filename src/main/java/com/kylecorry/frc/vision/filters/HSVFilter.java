package com.kylecorry.frc.vision.filters;

import com.kylecorry.frc.vision.Range;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * An HSV filter.
 */
public class HSVFilter implements TargetFilter {

    private Range hue;
    private Range saturation;
    private Range value;

    /**
     * Create a HSV filter.
     * @param hue The hue of the target from 0 - 180 degrees inclusive.
     * @param saturation The saturation of the target from 0 - 255 inclusive.
     * @param value The value of the target from 0 - 255 inclusive.
     */
    public HSVFilter(Range hue, Range saturation, Range value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    @Deprecated
    public Range getHue() {
        return hue;
    }

    @Deprecated
    public void setHue(Range hue) {
        this.hue = hue;
    }

    @Deprecated
    public Range getSaturation() {
        return saturation;
    }

    @Deprecated
    public void setSaturation(Range saturation) {
        this.saturation = saturation;
    }

    @Deprecated
    public Range getValue() {
        return value;
    }

    @Deprecated
    public void setValue(Range value) {
        this.value = value;
    }

    @Override
    public Mat filter(Mat image) {
        Mat hsvImg = new Mat();
        Imgproc.cvtColor(image, hsvImg, Imgproc.COLOR_BGR2HSV);
        Scalar lower = new Scalar(hue.getLow(), saturation.getLow(), value.getLow());
        Scalar upper = new Scalar(hue.getHigh(), saturation.getHigh(), value.getHigh());
        Core.inRange(hsvImg, lower, upper, hsvImg);
        return hsvImg;
    }

}
