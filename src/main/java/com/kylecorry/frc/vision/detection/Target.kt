package com.kylecorry.frc.vision.detection

import com.kylecorry.geometry.Point
import org.opencv.core.Size

class Target
/**
 * Create a target.

 * @param confidence   The confidence that this is the real target from 0 to 1 inclusive.
 * *
 * @param width        The width of the target in pixels.
 * *
 * @param height       The height of the target in pixels.
 * *
 * @param position     The top left position in pixels.
 * *
 * @param centerOfMass The center of mass in pixels.
 * *
 * @param imageSize    The size of the image that the target was located in.
 */
internal constructor(confidence: Double, override val width: Double, override val height: Double, override val position: Point, override val centerOfMass: Point, imageSize: Size) : AbstractTarget() {

    init {
        this.imageSize = imageSize
    }

    override val probability: Double = confidence
}
