package com.kylecorry.frc.vision.targetDetection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

public class DoubleTargetDetector extends Detector<DoubleTarget> {

    private TargetDetector targetDetector;
    private DoubleTargetSpecs specs;

    public DoubleTargetDetector(TargetDetector targetDetector, DoubleTargetSpecs specs) {
        this.targetDetector = targetDetector;
        this.specs = specs;
    }

    /**
     * Detect the target group in the image.
     *
     * @param frame The image to detect target groups in.
     * @return The list of possible target groups ordered by confidence from greatest to least.
     */
    @Override
    public List<DoubleTarget> detect(Mat frame) {
        List<DoubleTarget> groups = new ArrayList<>();
        List<SingleTarget> targets = targetDetector.detect(frame);
        for (SingleTarget target : targets) {
            for (SingleTarget other : targets) {
                if (other.equals(target))
                    continue;
                DoubleTarget group = DoubleTarget.makeDoubleTarget(target, other, 0.0);
                double s1 = DoubleTargetScorer.heightRatioScore(group, specs.getTargetHeightRatio());
                double s2 = DoubleTargetScorer.widthRatioScore(group, specs.getTargetWidthRatio());
                double s3 = 0;
                switch (specs.getAlignment()) {
                    case LEFT:
                        s3 = DoubleTargetScorer.leftAlignmentScore(group);
                        break;
                    case TOP:
                        s3 = DoubleTargetScorer.topAlignmentScore(group);
                }
                double s4 = DoubleTargetScorer.targetWidthToGroupWidthScore(group,
                        targetDetector.targetSpecs.getWidth() / specs.getGroupWidth());
                double s5 = DoubleTargetScorer.targetHeightToGroupHeightScore(group,
                        targetDetector.targetSpecs.getHeight() / specs.getGroupHeight());
                double sum = s1 + s2 + s3 + s4 + s5;
                double confidence = sum / 5.0 / 100.0;
                group.setConfidence(confidence);
                groups.add(group);
            }
        }
        groups.sort((a, b) -> {
            if (b.getIsTargetProbability() > a.getIsTargetProbability()) {
                return 1;
            } else if (a.getIsTargetProbability() > b.getIsTargetProbability()) {
                return -1;
            }
            return 0;
        });
        return groups;
    }

}
