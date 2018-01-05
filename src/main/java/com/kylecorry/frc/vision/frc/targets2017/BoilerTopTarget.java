package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

public class BoilerTopTarget implements TargetSpecs {
    @Override
    public double getWidth() {
        return 0.381;
    }

    @Override
    public double getHeight() {
        return 0.1016;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }
}
