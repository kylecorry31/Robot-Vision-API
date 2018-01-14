package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetSpecs;

public class SwitchTarget implements DoubleTargetSpecs {
    @Override
    public double getTargetWidthRatio() {
        return 0.071588367;
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
        return 2.83845;
    }

    @Override
    public double getGroupHeight() {
        return 0.38862;
    }
}
