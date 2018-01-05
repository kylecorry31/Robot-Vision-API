package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

public class BoilerBottomTarget implements TargetSpecs {
    @Override
    public double getWidth() {
        return 0.381;
    }

    @Override
    public double getHeight() {
        return 0.0508;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }
}
