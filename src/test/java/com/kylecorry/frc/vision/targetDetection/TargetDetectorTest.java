package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.frc.vision.filters.BrightnessFilter;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static org.junit.Assert.*;

public class TargetDetectorTest {

    private TargetDetector targetDetector;

    @Test
    public void testTargetDetector(){
        targetDetector = new TargetDetector(new SquareTargetSpecs(), new BrightnessFilter(200, 255), 10);

        Mat image = Mat.zeros(100, 100, CvType.CV_8UC3);
        Imgproc.rectangle(image, new Point(1, 1), new Point(2, 2), Scalar.all(255));
        Imgproc.rectangle(image, new Point(10, 10), new Point(30, 30), Scalar.all(255));

        List<Target> targets = targetDetector.detect(image);

        assertEquals(1, targets.size());

        Target target = targets.get(0);

        assertEquals(new com.kylecorry.geometry.Point(20, 20, 0), target.getCenterOfMass());

        assertEquals(20, target.getHeight(), 0.0001);

        assertEquals(20, target.getWidth(), 0.0001);

        assertEquals(new com.kylecorry.geometry.Point(20, 20, 0), target.getCenterPosition());

        assertEquals(1.0, target.getIsTargetProbability(), 0.1);

        assertEquals(new com.kylecorry.geometry.Point(10, 10, 0), target.getPosition());

        assertEquals(image.size(), target.imageSize);
    }

}