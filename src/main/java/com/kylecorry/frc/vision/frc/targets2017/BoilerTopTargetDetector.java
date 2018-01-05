package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetDetector;

public class BoilerTopTargetDetector extends TargetDetector {
    public BoilerTopTargetDetector(TargetFilter filter, double minPixelArea) {
        super(new BoilerTopTarget(), filter, minPixelArea);
    }
}
