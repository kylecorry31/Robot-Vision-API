package com.kylecorry.frc.vision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

public class MultiTargetDetector extends Detector<Target> {

	private List<Detector<? extends Target>> detectors;

	public MultiTargetDetector(List<Detector<? extends Target>> detectors) {
		this.detectors = detectors;
	}

	public MultiTargetDetector(Detector<? extends Target>... detectors){
	    this(Arrays.asList(detectors));
    }

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
