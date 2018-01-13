package com.kylecorry.frc.vision.frc.targets2017;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetDetector;

public class BoilerTopTargetDetector extends TargetDetector {
    public BoilerTopTargetDetector(TargetFilter filter, ContourFilter contourFilter) {
        super(new BoilerTopTarget(), filter, contourFilter);
    }
}
