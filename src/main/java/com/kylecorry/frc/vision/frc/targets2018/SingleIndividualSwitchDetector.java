package com.kylecorry.frc.vision.frc.targets2018;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetDetector;

public class SingleIndividualSwitchDetector extends TargetDetector {
    public SingleIndividualSwitchDetector(TargetFilter filter, ContourFilter contourFilter) {
        super(new SingleIndividualSwitchTarget(), filter, contourFilter);
    }
}
