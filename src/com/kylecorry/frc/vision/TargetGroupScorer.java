package com.kylecorry.frc.vision;

public class TargetGroupScorer {
	public static double widthRatioScore(TargetGroup target, double idealRatio) {
		return Scorer.score(target.getFirstTarget().getWidth() / target.getSecondTarget().getWidth(), idealRatio);
	}

	public static double heightRatioScore(TargetGroup target, double idealRatio) {
		return Scorer.score(target.getFirstTarget().getHeight() / target.getSecondTarget().getHeight(), idealRatio);
	}

	public static double topAlignmentScore(TargetGroup target) {
		return Scorer.score(((target.getSecondTarget().getPosition().y - target.getFirstTarget().getPosition().y)
				/ target.getFirstTarget().getHeight()) + 1, 1);
	}

	public static double leftAlignmentScore(TargetGroup target) {
		return Scorer.score(((target.getSecondTarget().getPosition().x - target.getFirstTarget().getPosition().x)
				/ target.getFirstTarget().getWidth()) + 1, 1);
	}

	public static double targetWidthToGroupWidthScore(TargetGroup target, double idealRatio) {
		return Scorer.score(target.getFirstTarget().getWidth() / (target.getSecondTarget().getPosition().x
				+ target.getSecondTarget().getWidth() - target.getFirstTarget().getPosition().x), idealRatio);
	}

	public static double targetHeightToGroupHeightScore(TargetGroup target, double idealRatio) {
		return Scorer.score(target.getFirstTarget().getHeight() / (target.getSecondTarget().getPosition().y
				+ target.getSecondTarget().getHeight() - target.getFirstTarget().getPosition().y), idealRatio);
	}
}
