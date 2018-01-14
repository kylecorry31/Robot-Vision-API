package com.kylecorry.frc.vision.targetDetection;

public interface DoubleTargetSpecs {

	enum Alignment {
		LEFT, TOP
	}

	double getTargetWidthRatio();

	double getTargetHeightRatio();

	Alignment getAlignment();

	double getGroupWidth();

	double getGroupHeight();
}
