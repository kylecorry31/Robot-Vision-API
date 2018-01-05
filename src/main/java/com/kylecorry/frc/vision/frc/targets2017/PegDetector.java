package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.targetDetection.TargetGroupDetector;

public class PegDetector extends TargetGroupDetector {
    public PegDetector(IndividualPegDetector targetDetector) {
        super(targetDetector, new PegTarget());
    }
}
