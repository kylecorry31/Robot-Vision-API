package com.kylecorry.frc.vision.detection

import org.opencv.core.Range

interface TargetSpecs {

    /**
     * Gets the hue of the target from 0 to 180 degrees inclusive.

     * @return The target's hue.
     */
    val hue: Range

    /**
     * Gets the saturation of the target from 0 to 255 inclusive.

     * @return The target's saturation.
     */
    val saturation: Range

    /**
     * Gets the value of the target from 0 to 255 inclusive.

     * @return The target's value.
     */
    val value: Range

    /**
     * Gets the width of the target.

     * @return The target's width.
     */
    val width: Double

    /**
     * Gets the height of the target.

     * @return The target's height.
     */
    val height: Double

    /**
     * Gets the area of the target (not the bounding box, unless the target
     * fills the entire bounding box).

     * @return The target's area.
     */
    val area: Double

    /**
     * Gets the minimum area of the target in pixels for it to be analyzed.

     * @return The minimum required area in pixels.
     */
    val minPixelArea: Int
}
