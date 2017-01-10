package com.kylecorry.frc.hardware;

public class Sensor {

	public static final int SENSOR_TYPE_ACCELEROMETER = 0, SENSOR_TYPE_ENCODER = 1, SENSOR_TYPE_POTENTIOMETER = 2,
			SENSOR_TYPE_GYRO = 3;

	private String name;
	private int sensorType;
	private SensorDriver driver;

	Sensor(String name, int sensorType, SensorDriver driver) {
		this.name = name;
		this.sensorType = sensorType;
		this.driver = driver;
	}

	public String getName() {
		return name;
	}

	public int getSensorType() {
		return sensorType;
	}

	public SensorDriver getDriver() {
		return driver;
	}

	public static class Builder {

		private String name;
		private int sensorType;
		private SensorDriver driver;

		public Builder() {

		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setType(int type) {
			sensorType = type;
			return this;
		}

		public Builder setDriver(SensorDriver driver) {
			this.driver = driver;
			return this;
		}

		public Sensor build() {
			return new Sensor(name, sensorType, driver);
		}

	}
}
