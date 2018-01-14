package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetSpecs;
import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

public class BoilerTarget implements DoubleTargetSpecs {

    private TargetSpecs top, bottom;

    public BoilerTarget(){
        top = new BoilerTopTarget();
        bottom = new BoilerBottomTarget();
    }

    @Override
    public double getTargetWidthRatio() {
        return 1;
    }

    @Override
    public double getTargetHeightRatio() {
        return top.getHeight() / bottom.getHeight();
    }

    @Override
    public Alignment getAlignment() {
        return Alignment.LEFT;
    }

    @Override
    public double getGroupWidth() {
        return top.getWidth();
    }

    @Override
    public double getGroupHeight() {
        return 0.254;
    }
}
