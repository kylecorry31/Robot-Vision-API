package com.kylecorry.frc.vision.targetDetection;

import java.util.List;

import org.opencv.core.Mat;

public abstract class Detector<T> {

	/**
	 * Detect objects in an image.
	 * 
	 * @param frame
	 *            The image to detect objects in.
	 * @return The list of objects in the image.
	 */
	public abstract List<T> detect(Mat frame);

}
