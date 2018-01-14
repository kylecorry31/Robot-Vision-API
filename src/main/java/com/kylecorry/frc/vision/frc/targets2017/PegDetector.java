package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.DoubleTargetDetector;

public class PegDetector extends DoubleTargetDetector {
    public PegDetector(IndividualPegDetector targetDetector) {
        super(targetDetector, new PegTarget());
    }
}
