package com.kylecorry.frc.vision.detection

import com.kylecorry.geometry.Point

class TargetGroup
/**
 * Create a TargetGroup consisting of the first and second target.

 * @param first  The first target in the group.
 * *
 * @param second The second target in the group.
 */
(
        /**
         * Get the first target in the group.

         * @return The first target.
         */
        val firstTarget: Target,
        /**
         * Get the second target in the group.

         * @return The second target.
         */
        val secondTarget: Target) : AbstractTarget() {

    init {
        this.imageSize = firstTarget.imageSize
    }

    /**
     * Get the probability that this is the specified target group from 0 to 1
     * inclusive.

     * @return The probability that this is the target group.
     */
    override var probability: Double = 0.0
        get() = probability


    override val width: Double
        get() {
            val minLeft = Math.min(firstTarget.position.x, secondTarget.position.x)
            val maxRight = Math.max(firstTarget.position.x + firstTarget.width,
                    secondTarget.position.x + secondTarget.width)
            return maxRight - minLeft
        }


    override val height: Double
        get() {
            val minTop = Math.min(firstTarget.position.y, secondTarget.position.y)
            val maxBottom = Math.max(firstTarget.position.y + firstTarget.height,
                    secondTarget.position.y + secondTarget.height)
            return maxBottom - minTop
        }


    override val position: Point
        get() {
            val minLeft = Math.min(firstTarget.position.x, secondTarget.position.x)
            val minTop = Math.min(firstTarget.position.y, secondTarget.position.y)
            return Point(minLeft, minTop, 0.0)
        }

    override val centerOfMass: Point
        get() = Point((firstTarget.centerOfMass.x + secondTarget.centerOfMass.x) / 2.0,
                (firstTarget.centerOfMass.y + secondTarget.centerOfMass.y) / 2.0, 0.0)
}
