package com.kylecorry.frc.vision.detection;

public interface TargetGroupSpecs {

	enum Alignment {
		LEFT, TOP
	}

	double getTargetWidthRatio();

	double getTargetHeightRatio();

	Alignment getAlignment();

	double getGroupWidth();

	double getGroupHeight();
}
