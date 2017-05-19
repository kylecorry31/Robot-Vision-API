package com.kylecorry.frc.vision.detection

import java.util.ArrayList
import java.util.Arrays

import org.opencv.core.Mat

// TODO: Add better way to identify which detector found what target
class MultiTargetDetector
/**
 * Create a MultiTargetDetector to detect multiple types of targets in an image.

 * @param detectors The detector for each target to identify.
 */
(private val detectors: List<Detector<out Target>>) : Detector<Target>() {

    /**
     * Create a MultiTargetDetector to detect multiple types of targets in an image.

     * @param detectors The detector for each target to identify.
     */
    constructor(vararg detectors: Detector<out Target>) : this(Arrays.asList(*detectors)) {}

    /**
     * Detect the targets in the image.

     * @param frame The image to detect targets in.
     * *
     * @return The list of possible targets ordered by confidence from greatest to least and by which detector found the target.
     */
    override fun detect(frame: Mat): List<Target> {
        val targets = ArrayList<Target>()
        for (detector in detectors) {
            targets.addAll(detector.detect(frame))
        }
        return targets
    }


    @Deprecated("This class was a bit confusing to use, instead use the constructors in {@link MultiTargetDetector} instead.")
    class Builder {

        private val detectors: MutableList<Detector<out Target>>

        init {
            detectors = ArrayList<Detector<out Target>>()
        }

        fun add(detector: Detector<out Target>): Builder {
            detectors.add(detector)
            return this
        }

        fun build(): MultiTargetDetector {
            return MultiTargetDetector(detectors)
        }
    }

}
