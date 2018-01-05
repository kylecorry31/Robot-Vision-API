package com.kylecorry.frc.vision.frc.targets2016;

import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

public class CastleTarget implements TargetSpecs {
    @Override
    public double getWidth() {
        return 0.508;
    }

    @Override
    public double getHeight() {
        return 0.3048;
    }

    @Override
    public double getArea() {
        return 0.0516128;
    }
}
