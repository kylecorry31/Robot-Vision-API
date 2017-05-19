package com.kylecorry.frc.vision.detection

import java.util.ArrayList
import java.util.Comparator

import org.opencv.core.Mat

class TargetGroupDetector internal constructor(private val targetDetector: TargetDetector, private val specs: TargetGroupSpecs, processor: Detector.Processor<TargetGroup>? = null) : Detector<TargetGroup>() {

    init {
        if (processor != null) {
            setProcessor(processor)
        }
    }

    /**
     * Create a TargetGroupDetector to detect a target that is composed of smaller vision targets.

     * @param targetSpecs      The specifications of a single vision target in the target group.
     * *
     * @param targetGroupSpecs The specifications of a group of vision targets.
     * *
     * @param processor        The processor to handle detected target groups.
     */
    @JvmOverloads constructor(targetSpecs: TargetSpecs, targetGroupSpecs: TargetGroupSpecs, processor: Detector.Processor<TargetGroup>? = null) : this(TargetDetector(targetSpecs), targetGroupSpecs, processor) {}

    /**
     * Detect the target group in the image.

     * @param frame The image to detect target groups in.
     * *
     * @return The list of possible target groups ordered by confidence from greatest to least.
     */
    override fun detect(frame: Mat): List<TargetGroup> {
        val groups = ArrayList<TargetGroup>()
        val targets = targetDetector.detect(frame)
        for (target in targets) {
            for (other in targets) {
                if (other == target)
                    continue
                val group = TargetGroup(target, other)
                val s1 = TargetGroupScorer.heightRatioScore(group, specs.targetHeightRatio)
                val s2 = TargetGroupScorer.widthRatioScore(group, specs.targetWidthRatio)
                var s3 = 0.0
                when (specs.alignment) {
                    TargetGroupSpecs.Alignment.LEFT -> s3 = TargetGroupScorer.leftAlignmentScore(group)
                    TargetGroupSpecs.Alignment.TOP -> s3 = TargetGroupScorer.topAlignmentScore(group)
                }
                val s4 = TargetGroupScorer.targetWidthToGroupWidthScore(group,
                        targetDetector.targetSpecs.width / specs.groupWidth)
                val s5 = TargetGroupScorer.targetHeightToGroupHeightScore(group,
                        targetDetector.targetSpecs.height / specs.groupHeight)
                val sum = s1 + s2 + s3 + s4 + s5
                group.probability = sum / 5.0 / 100.0
                groups.add(group)
            }
        }

        groups.sortByDescending { it.probability }
        return groups
    }


}
/**
 * Create a TargetGroupDetector to detect a target that is composed of smaller vision targets.

 * @param targetSpecs      The specifications of a single vision target in the target group.
 * *
 * @param targetGroupSpecs The specifications of a group of vision targets.
 */
