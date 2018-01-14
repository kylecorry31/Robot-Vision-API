package com.kylecorry.frc.vision.pipeline;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFinders.ContourFinder;
import com.kylecorry.frc.vision.contourFinders.StandardContourFinder;
import com.kylecorry.frc.vision.filters.TargetFilter;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.List;

public class TargetFinder {

    private CameraSettings cameraSettings;

    private TargetFilter thresholdFilter;

    private ContourFilter contourFilter;

    private ContourFinder contourFinder;

    private ContourToTargetConverter contourToTargetConverter;


    public TargetFinder(CameraSettings cameraSettings, TargetFilter thresholdFilter, ContourFilter contourFilter, TargetGrouping grouping) {
        this.cameraSettings = cameraSettings;
        this.thresholdFilter = thresholdFilter;
        this.contourFilter = contourFilter;

        contourFinder = new StandardContourFinder();

        switch (grouping) {
            case SINGLE:
                contourToTargetConverter = new SingleTargetConverter();
                break;
            case DOUBLE:
            case TRIPLE:
            default:
                contourToTargetConverter = new SingleTargetConverter();
        }
    }

    public List<TargetOutput> findTargets(Mat image) {
        Mat filtered = thresholdFilter.filter(image);
        List<MatOfPoint> contours = contourFinder.findContours(filtered);
        filtered.release();
        contours = contourFilter.filterContours(contours);
        return contourToTargetConverter.convertContours(contours, cameraSettings);
    }

}
