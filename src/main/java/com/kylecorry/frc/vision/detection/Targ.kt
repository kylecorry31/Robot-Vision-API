package com.kylecorry.frc.vision.detection

import com.kylecorry.geometry.Point
import org.opencv.core.Size

/**
 * Created by Kylec on 5/19/2017.
 */
data class Targ(val confidence: Double, val width: Double, val height: Double, val topLeftPosition: Point, val centerOfMass: Point, val imageSize: Size)