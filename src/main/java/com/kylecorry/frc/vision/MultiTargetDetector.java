package com.kylecorry.frc.vision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

// TODO: Add better way to identify which detector found what target
public class MultiTargetDetector extends Detector<Target> {

    private List<Detector<? extends Target>> detectors;

    /**
     * Create a MultiTargetDetector to detect multiple types of targets in an image.
     *
     * @param detectors The detector for each target to identify.
     */
    public MultiTargetDetector(List<Detector<? extends Target>> detectors) {
        this.detectors = detectors;
    }

    /**
     * Create a MultiTargetDetector to detect multiple types of targets in an image.
     *
     * @param detectors The detector for each target to identify.
     */
    public MultiTargetDetector(Detector<? extends Target>... detectors) {
        this(Arrays.asList(detectors));
    }

    /**
     * Detect the targets in the image.
     *
     * @param frame The image to detect targets in.
     * @return The list of possible targets ordered by confidence from greatest to least and by which detector found the target.
     */
    @Override
    public List<Target> detect(Mat frame) {
        List<Target> targets = new ArrayList<>();
        for (Detector<? extends Target> detector : detectors) {
            targets.addAll(detector.detect(frame));
        }
        return targets;
    }

    @Deprecated
    public static class Builder {

        private List<Detector<? extends Target>> detectors;

        public Builder() {
            detectors = new ArrayList<>();
        }

        public Builder add(Detector<? extends Target> detector) {
            detectors.add(detector);
            return this;
        }

        public MultiTargetDetector build() {
            return new MultiTargetDetector(detectors);
        }
    }

}
