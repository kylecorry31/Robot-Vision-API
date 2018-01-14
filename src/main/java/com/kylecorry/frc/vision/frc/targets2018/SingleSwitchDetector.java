package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetDetector;

public class SingleSwitchDetector extends DoubleTargetDetector {
    public SingleSwitchDetector(SingleIndividualSwitchDetector targetDetector) {
        super(targetDetector, new SingleSwitchTarget());
    }
}
