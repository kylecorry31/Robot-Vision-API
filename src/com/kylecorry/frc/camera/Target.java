package com.kylecorry.frc.camera;

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

	public double getIsTargetProbability() {
		return confidence;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Position getPosition() {
		return position;
	}

	public double computeDistance(int imageWidth, double targetActualWidth, double cameraViewAngle) {
		double normalizedWidth;
		normalizedWidth = 2 * getWidth() / imageWidth;
		return targetActualWidth / (normalizedWidth * Math.tan(Math.toRadians(cameraViewAngle / 2)));
	}

	public double computeAngle(int imageWidth, double cameraViewAngle) {
		double aimingCoordinate = (getPosition().x / imageWidth) * 2 - 1;
		return aimingCoordinate * cameraViewAngle / 2;
	}

}
