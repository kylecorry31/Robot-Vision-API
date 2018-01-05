package com.kylecorry.frc.vision.targetDetection;

public interface TargetSpecs {

	/**
	 * Gets the width of the target.
	 * 
	 * @return The target's width.
	 */
	double getWidth();

	/**
	 * Gets the height of the target.
	 * 
	 * @return The target's height.
	 */
	double getHeight();

	/**
	 * Gets the area of the target (not the bounding box, unless the target
	 * fills the entire bounding box).
	 * 
	 * @return The target's area.
	 */
	double getArea();
}
