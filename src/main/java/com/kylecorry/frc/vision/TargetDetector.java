package com.kylecorry.frc.vision;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.ConvexHullContourFilter;
import com.kylecorry.frc.vision.contourFinders.ContourFinder;
import com.kylecorry.frc.vision.contourFinders.StandardContourFinder;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.geometry.Point;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;

public class TargetDetector extends Detector<Target> {

    TargetSpecs targetSpecs;

    /**
     * Create a TargetDetector to find a single target.
     *
     * @param specs     The specifications of the target.
     * @param processor The processor to handle the targets when detected.
     */
    public TargetDetector(TargetSpecs specs, Processor<Target> processor) {
        this.targetSpecs = specs;
        setProcessor(processor);
    }

    /**
     * Create a TargetDetector to find a single target.
     *
     * @param specs The specifications of the target.
     */
    public TargetDetector(TargetSpecs specs) {
        this(specs, null);
    }

    /**
     * Detect the target in the image.
     *
     * @param frame The image to detect targets in.
     * @return The list of possible targets ordered by confidence from greatest to least.
     */
    @Override
    public List<Target> detect(Mat frame) {
        TargetFilter filter = new HSVFilter(targetSpecs.getHue(), targetSpecs.getSaturation(), targetSpecs.getValue());
        Mat filtered = filter.filter(frame);

        ContourFinder contourFinder = new StandardContourFinder();

        List<MatOfPoint> contours = contourFinder.findContours(filtered);

        filtered.release();

        ContourFilter contourFilter = new ConvexHullContourFilter(targetSpecs.getMinPixelArea(), 0, new Range(0, 1000), new Range(0, 1000), new Range(0, 100), new Range(0, 1000000), new Range(0, 1000));
        contours = contourFilter.filterContours(contours);


        List<Target> detections = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Rect boundary = Imgproc.boundingRect(contour);
            double aspectRatio = (boundary.width / (double) boundary.height);
            double aspectScore = Scorer.score(aspectRatio, targetSpecs.getWidth() / targetSpecs.getHeight());

            double areaRatio = Imgproc.contourArea(contour) / (double) boundary.area();
            double areaScore = Scorer.score(areaRatio,
                    targetSpecs.getArea() / (targetSpecs.getHeight() * targetSpecs.getWidth()));

            double confidence = Math.round((aspectScore + areaScore) / 2) / 100.0;

            Moments moments = Imgproc.moments(contour);

            Point centerOfMass = new Point(moments.m10 / moments.m00, moments.m01 / moments.m00, 0);

            Target target = new Target(confidence, boundary.width, boundary.height,
                    new Point(boundary.x, boundary.y, 0), centerOfMass, frame.size());
            detections.add(target);
        }
        detections.sort((a, b) -> {
            if (b.getIsTargetProbability() > a.getIsTargetProbability()) {
                return 1;
            } else if (a.getIsTargetProbability() > b.getIsTargetProbability()) {
                return -1;
            }
            return 0;
        });
        return detections;
    }

    /**
     * @deprecated This class was a bit confusing to use, instead use the constructors in {@link TargetDetector} instead.
     */
    @Deprecated
    public static class Builder {
        private TargetSpecs specs;
        private Processor<Target> processor;

        public Builder setTargetSpecs(TargetSpecs specs) {
            this.specs = specs;
            return this;
        }

        public Builder setProcessor(Processor<Target> processor) {
            this.processor = processor;
            return this;
        }

        public TargetDetector build() {
            return new TargetDetector(specs, processor);
        }

    }

}
