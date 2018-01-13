package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.targetDetection.TargetGroupSpecs;

public class SingleSwitchTarget implements TargetGroupSpecs {
    @Override
    public double getTargetWidthRatio() {
        return 0.25;
    }

    @Override
    public double getTargetHeightRatio() {
        return 1;
    }

    @Override
    public Alignment getAlignment() {
        return Alignment.TOP;
    }

    @Override
    public double getGroupWidth() {
        return 0.2032;
    }

    @Override
    public double getGroupHeight() {
        return 0.38862;
    }
}
