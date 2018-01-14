package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetDetector;

public class BoilerTargetDetector extends DoubleTargetDetector {
    public BoilerTargetDetector(BoilerTopTargetDetector targetDetector) {
        super(targetDetector, new BoilerTarget());
    }

    public BoilerTargetDetector(BoilerBottomTargetDetector targetDetector) {
        super(targetDetector, new BoilerTarget());
    }
}
