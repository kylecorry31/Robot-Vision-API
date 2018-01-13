package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.targetDetection.TargetDetector;
import com.kylecorry.frc.vision.targetDetection.TargetGroupDetector;
import com.kylecorry.frc.vision.targetDetection.TargetGroupSpecs;

public class SingleSwitchDetector extends TargetGroupDetector {
    public SingleSwitchDetector(SingleIndividualSwitchDetector targetDetector) {
        super(targetDetector, new SingleSwitchTarget());
    }
}
