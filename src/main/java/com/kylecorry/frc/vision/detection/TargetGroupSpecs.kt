package com.kylecorry.frc.vision.detection

interface TargetGroupSpecs {

    enum class Alignment {
        LEFT, TOP
    }

    val targetWidthRatio: Double

    val targetHeightRatio: Double

    val alignment: Alignment

    val groupWidth: Double

    val groupHeight: Double
}
