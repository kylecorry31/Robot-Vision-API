package com.kylecorry.frc.vision;

public class Target {
	private double confidence;
	private double width, height;
	private Position position;

	Target(double confidence, double width, double height, Position position) {
		this.confidence = confidence;
		this.width = width;
		this.height = height;
		this.position = position;
	}

	/**
	 * Get how confident that this object is the specified target.
	 * 
	 * @return The confidence from 0 to 1 inclusive.
	 */
	public double getIsTargetProbability() {
		return confidence;
	}

	/**
	 * Get the width in pixels of the target's bounding box.
	 * 
	 * @return The width of the target in pixels.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Get the height in pixels of the target's bounding box.
	 * 
	 * @return The height of the target in pixels.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Get the position of the top left point of the target's bounding box.
	 * 
	 * @return The target's top left point in pixels.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Get the position of the center of the target's bounding box.
	 * 
	 * @return The target's center point in pixels.
	 */
	public Position getCenterPosition() {
		return new Position(getPosition().x + getWidth() / 2.0, getPosition().y + getHeight() / 2.0);
	}

	/**
	 * Compute the distance to the target from the camera.
	 * 
	 * @param imageWidth
	 *            The width of the full image.
	 * @param targetActualWidth
	 *            The width of the target in a given distance unit.
	 * @param cameraViewAngle
	 *            The view angle of the camera in degrees.
	 * @return The distance to the target in the same units as the
	 *         targetActualWidth.
	 */
	public double computeDistance(int imageWidth, double targetActualWidth, double cameraViewAngle) {
		double normalizedWidth;
		normalizedWidth = 2 * getWidth() / imageWidth;
		return targetActualWidth / (normalizedWidth * Math.tan(Math.toRadians(cameraViewAngle / 2)));
	}

	/**
	 * Compute the angle to the target from the center of the camera.
	 * 
	 * @param imageWidth
	 *            The width of the full image.
	 * @param cameraViewAngle
	 *            The view angle of the camera in degrees.
	 * @return The angle to the target in degrees from the center of the camera.
	 */
	public double computeAngle(int imageWidth, double cameraViewAngle) {
		double aimingCoordinate = (getCenterPosition().x / imageWidth) * 2 - 1;
		return aimingCoordinate * cameraViewAngle / 2;
	}

}
