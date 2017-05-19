import com.kylecorry.frc.vision.detection.TargetGroupSpecs

/**
 * Created by Kyle on 3/6/2017.
 */
class ExampleGroupSpecs : TargetGroupSpecs {
    override val targetWidthRatio: Double
        get() = 0.0

    override val targetHeightRatio: Double
        get() = 0.0

    override val alignment: TargetGroupSpecs.Alignment
        get() = TargetGroupSpecs.Alignment.LEFT

    override val groupWidth: Double
        get() = 0.0

    override val groupHeight: Double
        get() = 0.0
}
