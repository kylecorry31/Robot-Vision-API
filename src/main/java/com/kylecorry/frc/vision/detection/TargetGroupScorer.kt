package com.kylecorry.frc.vision.detection

internal object TargetGroupScorer {

    /**
     * Calculate a score from 0 to 100 for the ratio of the widths of the targets.

     * @param target     The target group.
     * *
     * @param idealRatio The ideal ratio of the first individual target width over the second individual target width.
     * *
     * @return A score from 0 to 100.
     */
    fun widthRatioScore(target: TargetGroup, idealRatio: Double): Double {
        return Scorer.score(target.firstTarget.width / target.secondTarget.width, idealRatio)
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the heights of the targets.

     * @param target     The target group.
     * *
     * @param idealRatio The ideal ratio of the first individual target height over the second individual target height.
     * *
     * @return A score from 0 to 100.
     */
    fun heightRatioScore(target: TargetGroup, idealRatio: Double): Double {
        return Scorer.score(target.firstTarget.height / target.secondTarget.height, idealRatio)
    }

    /**
     * Calculates a score from 0 to 100 for the targets being aligned to the top of the bounding box drawn around the target group.

     * @param target The target group.
     * *
     * @return a score from 0 to 100.
     */
    fun topAlignmentScore(target: TargetGroup): Double {
        return Scorer.score((target.secondTarget.position.y - target.firstTarget.position.y) / target.firstTarget.height + 1, 1.0)
    }

    /**
     * Calculates a score from 0 to 100 for the targets being aligned to the left of the bounding box drawn around the target group.

     * @param target The target group.
     * *
     * @return a score from 0 to 100.
     */
    fun leftAlignmentScore(target: TargetGroup): Double {
        return Scorer.score((target.secondTarget.position.x - target.firstTarget.position.x) / target.firstTarget.width + 1, 1.0)
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the width of the first target and the target group width.

     * @param target     The target group.
     * *
     * @param idealRatio The ideal ratio of the first individual target width over the target group width.
     * *
     * @return A score from 0 to 100.
     */
    fun targetWidthToGroupWidthScore(target: TargetGroup, idealRatio: Double): Double {
        return Scorer.score(target.firstTarget.width / (target.secondTarget.position.x + target.secondTarget.width - target.firstTarget.position.x), idealRatio)
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the height of the first target and the target group height.

     * @param target     The target group.
     * *
     * @param idealRatio The ideal ratio of the first individual target height over the target group height.
     * *
     * @return A score from 0 to 100.
     */
    fun targetHeightToGroupHeightScore(target: TargetGroup, idealRatio: Double): Double {
        return Scorer.score(target.firstTarget.height / (target.secondTarget.position.y + target.secondTarget.height - target.firstTarget.position.y), idealRatio)
    }
}
