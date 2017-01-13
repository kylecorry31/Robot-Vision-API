package com.kylecorry.frc.vision;

public interface TargetGroupSpecs {

	public static enum Alignment {
		LEFT, TOP
	}

	double getTargetWidthRatio();

	double getTargetHeightRatio();

	Alignment getAlignment();

	double getGroupWidth();

	double getGroupHeight();
}
