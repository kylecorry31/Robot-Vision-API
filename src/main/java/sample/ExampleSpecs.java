package sample;

import com.kylecorry.frc.vision.targetDetection.TargetSpecs;


public class ExampleSpecs implements TargetSpecs {

    @Override
    public double getWidth() {
        return 8;
    }

    @Override
    public double getHeight() {
        return 11;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }


}
