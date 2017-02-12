package com.kylecorry.frc.vision;

import java.util.List;

import org.opencv.core.Mat;

public abstract class Detector<T> {

	private Processor<T> processor;

	/**
	 * Detect objects in an image.
	 * 
	 * @param frame
	 *            The image to detect objects in.
	 * @return The list of objects in the image.
	 */
	public abstract List<T> detect(Mat frame);

	/**
	 * Accept a frame and process the output of detect using the processor if
	 * supplied.
	 * 
	 * @param frame
	 *            The image to process.
	 */
	public void receiveFrame(Mat frame) {
		if (processor != null) {
			processor.receiveDetections(detect(frame));
		}
	}

	/**
	 * Set the processor of the detector for processing the output of the
	 * detect.
	 * 
	 * @param processor
	 *            The processor to process the objects found in the image.
	 */
	public void setProcessor(Processor<T> processor) {
		this.processor = processor;
	}

	public static interface Processor<T> {
		/**
		 * Receive the objects detected in the image to process.
		 * 
		 * @param detections
		 *            The detections in the image.
		 */
		void receiveDetections(List<T> detections);
	}

}
