package com.kylecorry.frc.vision;

import java.util.List;

import org.opencv.core.Mat;

public abstract class Detector<T> {

	private Processor<T> processor;

	public abstract List<T> detect(Mat frame);

	public void receiveFrame(Mat frame) {
		if (processor != null) {
			processor.receiveDetections(detect(frame));
		}
	}

	public void setProcessor(Processor<T> processor) {
		this.processor = processor;
	}

	public static interface Processor<T> {
		void receiveDetections(List<T> detections);
	}

}
