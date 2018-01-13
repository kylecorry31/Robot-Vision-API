package com.kylecorry.frc.vision.frc.targets2016;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetDetector;

public class CastleDetector extends TargetDetector {
    public CastleDetector(TargetFilter filter, ContourFilter contourFilter) {
        super(new CastleTarget(), filter, contourFilter);
    }
}
