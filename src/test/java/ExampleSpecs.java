import com.kylecorry.frc.vision.TargetSpecs;
import org.opencv.core.Range;

/**
 * Created by Kylec on 2/12/2017.
 */
public class ExampleSpecs implements TargetSpecs {
    @Override
    public Range getHue() {
        return null;
    }

    @Override
    public Range getSaturation() {
        return null;
    }

    @Override
    public Range getValue() {
        return null;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public int getMinPixelArea() {
        return 0;
    }
}
