import com.kylecorry.frc.vision.detection.TargetSpecs
import org.opencv.core.Range

/**
 * Created by Kylec on 2/12/2017.
 */
class ExampleSpecs : TargetSpecs {
    override val hue: Range
        get() = Range(0, 255)

    override val saturation: Range
        get() = Range(0, 255)

    override val value: Range
        get() = Range(0, 255)

    override val width: Double
        get() = 0.0

    override val height: Double
        get() = 0.0

    override val area: Double
        get() = 0.0

    override val minPixelArea: Int
        get() = 0
}
