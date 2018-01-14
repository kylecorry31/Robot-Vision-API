package com.kylecorry.frc.vision.targetDetection;

public class DoubleTargetScorer {

    /**
     * Calculate a score from 0 to 100 for the ratio of the widths of the targetDetection.
     *
     * @param target     The target group.
     * @param idealRatio The ideal ratio of the first individual target width over the second individual target width.
     * @return A score from 0 to 100.
     */
    public static double widthRatioScore(DoubleTarget target, double idealRatio) {
        return Scorer.score(target.getFirstTarget().getWidth() / target.getSecondTarget().getWidth(), idealRatio);
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the heights of the targetDetection.
     *
     * @param target     The target group.
     * @param idealRatio The ideal ratio of the first individual target height over the second individual target height.
     * @return A score from 0 to 100.
     */
    public static double heightRatioScore(DoubleTarget target, double idealRatio) {
        return Scorer.score(target.getFirstTarget().getHeight() / target.getSecondTarget().getHeight(), idealRatio);
    }

    /**
     * Calculates a score from 0 to 100 for the targetDetection being aligned to the top of the bounding box drawn around the target group.
     *
     * @param target The target group.
     * @return a score from 0 to 100.
     */
    public static double topAlignmentScore(DoubleTarget target) {
        return Scorer.score(((target.getSecondTarget().getPosition().y - target.getFirstTarget().getPosition().y)
                / target.getFirstTarget().getHeight()) + 1, 1);
    }

    /**
     * Calculates a score from 0 to 100 for the targetDetection being aligned to the left of the bounding box drawn around the target group.
     *
     * @param target The target group.
     * @return a score from 0 to 100.
     */
    public static double leftAlignmentScore(DoubleTarget target) {
        return Scorer.score(((target.getSecondTarget().getPosition().x - target.getFirstTarget().getPosition().x)
                / target.getFirstTarget().getWidth()) + 1, 1);
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the width of the first target and the target group width.
     *
     * @param target     The target group.
     * @param idealRatio The ideal ratio of the first individual target width over the target group width.
     * @return A score from 0 to 100.
     */
    public static double targetWidthToGroupWidthScore(DoubleTarget target, double idealRatio) {
        return Scorer.score(target.getFirstTarget().getWidth() / (target.getSecondTarget().getPosition().x
                + target.getSecondTarget().getWidth() - target.getFirstTarget().getPosition().x), idealRatio);
    }

    /**
     * Calculate a score from 0 to 100 for the ratio of the height of the first target and the target group height.
     *
     * @param target     The target group.
     * @param idealRatio The ideal ratio of the first individual target height over the target group height.
     * @return A score from 0 to 100.
     */
    public static double targetHeightToGroupHeightScore(DoubleTarget target, double idealRatio) {
        return Scorer.score(target.getFirstTarget().getHeight() / (target.getSecondTarget().getPosition().y
                + target.getSecondTarget().getHeight() - target.getFirstTarget().getPosition().y), idealRatio);
    }
}
