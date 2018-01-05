package com.kylecorry.frc.vision.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class HSVFilter implements TargetFilter {

    private Range hue;
    private Range saturation;
    private Range value;

    public HSVFilter(Range hue, Range saturation, Range value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    @Override
    public Mat filter(Mat image) {
        Mat hsvImg = new Mat();
        Imgproc.cvtColor(image, hsvImg, Imgproc.COLOR_BGR2HSV);
        Scalar lower = new Scalar(hue.start % 360 / 2, saturation.start, value.start);
        Scalar upper = new Scalar(hue.end % 360 / 2, saturation.end, value.end);
        Core.inRange(hsvImg, lower, upper, hsvImg);
        return hsvImg;
    }

}
