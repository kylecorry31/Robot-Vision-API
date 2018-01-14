package com.kylecorry.frc.vision.targetDetection;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFinders.ContourFinder;
import com.kylecorry.frc.vision.contourFinders.StandardContourFinder;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.geometry.Point;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;

public class TargetDetector extends Detector<SingleTarget> {

    protected TargetSpecs targetSpecs;
    private TargetFilter filter;
    private ContourFilter contourFilter;


    /**
     * Create a TargetDetector to find a single target.
     *
     * @param specs The specifications of the target.
     */
    public TargetDetector(TargetSpecs specs, TargetFilter filter, ContourFilter contourFilter) {
        this.targetSpecs = specs;
        this.filter = filter;
        this.contourFilter = contourFilter;
    }

    /**
     * Detect the target in the image.
     *
     * @param frame The image to detect targetDetection in.
     * @return The list of possible targetDetection ordered by confidence from greatest to least.
     */
    @Override
    public List<SingleTarget> detect(Mat frame) {
        Mat filtered = filter.filter(frame);

        ContourFinder contourFinder = new StandardContourFinder();

        List<MatOfPoint> contours = contourFinder.findContours(filtered);

        filtered.release();

        contours = contourFilter.filterContours(contours);


        List<SingleTarget> detections = new ArrayList<>();
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

            SingleTarget target = new SingleTarget(confidence, boundary.width - 1, boundary.height - 1,
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

}
