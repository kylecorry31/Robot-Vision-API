package com.kylecorry.frc.hardware;

public class SensorEvent {
	public final Sensor sensor;
	public final double[] values;
	public final long timestamp;

	SensorEvent(Sensor sensor, double[] values, long timestamp) {
		this.sensor = sensor;
		this.values = values;
		this.timestamp = timestamp;
	}

}
