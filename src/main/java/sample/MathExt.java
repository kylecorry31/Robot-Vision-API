package sample;

public class MathExt {

	private MathExt() {
	}


	public static int roundToInt(double value) {
		return (int) Math.round(value);
	}

	public static double snap(double value, double nearest) {
		return Math.round(value / nearest) * nearest;
	}


}
