package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetDetector;

public class BoilerBottomTargetDetector extends TargetDetector {
    public BoilerBottomTargetDetector(TargetFilter filter, ContourFilter contourFilter) {
        super(new BoilerBottomTarget(), filter, contourFilter);
    }
}
