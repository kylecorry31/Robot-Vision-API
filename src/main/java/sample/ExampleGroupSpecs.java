package sample;


import com.kylecorry.frc.vision.targetDetection.TargetGroupSpecs;

public class ExampleGroupSpecs implements TargetGroupSpecs {

	@Override
	public double getTargetWidthRatio() {
		return 1;
	}

	@Override
	public double getTargetHeightRatio() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Alignment getAlignment() {
		// TODO Auto-generated method stub
		return Alignment.TOP;
	}

	@Override
	public double getGroupWidth() {
		return 10.25 / 12.0;
	}

	@Override
	public double getGroupHeight() {
		// TODO Auto-generated method stub
		return 5 / 12.0;
	}

}
