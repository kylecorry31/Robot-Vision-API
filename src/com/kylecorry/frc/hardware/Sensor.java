package com.kylecorry.frc.hardware;

public class Sensor {

	public static enum SensorType {
		ACCELEROMETER, ENCODER, POTENTIOMETER, GYROSCOPE
	}

	private String name;
	private SensorType sensorType;
	private SensorDriver driver;

	Sensor(String name, SensorType sensorType, SensorDriver driver) {
		this.name = name;
		this.sensorType = sensorType;
		this.driver = driver;
	}

	public String getName() {
		return name;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public SensorDriver getDriver() {
		return driver;
	}

	public static class Builder {

		private String name;
		private SensorType sensorType;
		private SensorDriver driver;

		public Builder() {

		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setType(SensorType type) {
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
