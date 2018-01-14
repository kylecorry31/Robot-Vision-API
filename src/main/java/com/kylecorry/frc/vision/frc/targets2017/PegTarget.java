package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetSpecs;

public class PegTarget implements DoubleTargetSpecs {
    @Override
    public double getTargetWidthRatio() {
        return 0.2;
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
        return 0.26035;
    }

    @Override
    public double getGroupHeight() {
        return 0.127;
    }
}
