import com.kylecorry.frc.vision.detection.TargetGroupSpecs;

/**
 * Created by Kyle on 3/6/2017.
 */
public class ExampleGroupSpecs implements TargetGroupSpecs {
    @Override
    public double getTargetWidthRatio() {
        return 0;
    }

    @Override
    public double getTargetHeightRatio() {
        return 0;
    }

    @Override
    public Alignment getAlignment() {
        return null;
    }

    @Override
    public double getGroupWidth() {
        return 0;
    }

    @Override
    public double getGroupHeight() {
        return 0;
    }
}
