package com.kylecorry.frc.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

public class TargetGroupDetector extends Detector<TargetGroup> {

	private TargetDetector targetDetector;
	private TargetGroupSpecs specs;

	TargetGroupDetector(TargetDetector targetDetector, TargetGroupSpecs specs, Processor<TargetGroup> processor) {
		this.targetDetector = targetDetector;
		this.specs = specs;
		setProcessor(processor);
	}

	@Override
	public List<TargetGroup> detect(Mat frame) {
		List<TargetGroup> groups = new ArrayList<>();
		List<Target> targets = targetDetector.detect(frame);
		for (Target target : targets) {
			for (Target other : targets) {
				if (other.equals(target))
					continue;
				TargetGroup group = new TargetGroup(target, other);
				double s1 = TargetGroupScorer.heightRatioScore(group, specs.getTargetHeightRatio());
				double s2 = TargetGroupScorer.widthRatioScore(group, specs.getTargetWidthRatio());
				double s3 = 0;
				switch (specs.getAlignment()) {
				case LEFT:
					s3 = TargetGroupScorer.leftAlignmentScore(group);
					break;
				case TOP:
					s3 = TargetGroupScorer.topAlignmentScore(group);
				}
				double s4 = TargetGroupScorer.targetWidthToGroupWidthScore(group,
						targetDetector.targetSpecs.getWidth() / specs.getGroupWidth());
				double s5 = TargetGroupScorer.targetHeightToGroupHeightScore(group,
						targetDetector.targetSpecs.getHeight() / specs.getGroupHeight());
				double sum = s1 + s2 + s3 + s4 + s5;
				double confidence = sum / 5.0 / 100.0;
				group.confidence = confidence;
				groups.add(group);
			}
		}
		groups.sort((a, b) -> {
			if (b.getIsTargetGroupProbability() > a.getIsTargetGroupProbability()) {
				return 1;
			} else if (a.getIsTargetGroupProbability() > b.getIsTargetGroupProbability()) {
				return -1;
			}
			return 0;
		});
		return groups;
	}

	public static class Builder {
		private TargetDetector detector;
		private Processor<TargetGroup> processor;
		private TargetGroupSpecs specs;

		public Builder setTargetSpecs(TargetSpecs specs) {
			this.detector = new TargetDetector.Builder().setTargetSpecs(specs).build();
			return this;
		}

		public Builder setTargetGroupSpecs(TargetGroupSpecs specs) {
			this.specs = specs;
			return this;
		}

		public Builder setProcessor(Processor<TargetGroup> processor) {
			this.processor = processor;
			return this;
		}

		public TargetGroupDetector build() {
			return new TargetGroupDetector(detector, specs, processor);
		}

	}

}
