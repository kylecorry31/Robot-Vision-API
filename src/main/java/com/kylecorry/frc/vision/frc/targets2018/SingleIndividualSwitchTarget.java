package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

public class SingleIndividualSwitchTarget implements TargetSpecs {
    @Override
    public double getWidth() {
        return 0.0508;
    }

    @Override
    public double getHeight() {
        return 0.38862;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }
}
