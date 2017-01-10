package com.kylecorry.frc.hardware;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SensorManager {

	public static final int SENSOR_DELAY_FASTEST = 0, SENSOR_DELAY_NORMAL = 1, SENSOR_DELAY_SLOW = 2;

	private int minDelay = SENSOR_DELAY_SLOW;
	private Map<SensorEventListener, Sensor> sensors;
	private boolean running = false;
	private Thread eventThread;

	private SensorManager() {
		sensors = new HashMap<>();
		eventThread = new Thread(() -> {
			while (running) {
				for (Entry<SensorEventListener, Sensor> entry : sensors.entrySet()) {
					Sensor s = entry.getValue();
					entry.getKey()
							.onSensorChanged(new SensorEvent(s, s.getDriver().read(), System.currentTimeMillis()));
				}
				try {
					Thread.sleep(delayToMillis(minDelay));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		eventThread.setDaemon(true);
	}

	private static long delayToMillis(int delay) {
		switch (delay) {
		case SENSOR_DELAY_SLOW:
			return 200;
		case SENSOR_DELAY_NORMAL:
			return 20;
		default:
			return 0;
		}
	}

	private static class InstanceHolder {
		public static SensorManager sm = new SensorManager();
	}

	public static SensorManager getInstance() {
		return InstanceHolder.sm;
	}

	private void start() {
		running = true;
		eventThread.start();
	}

	private void stop() {
		running = false;
		try {
			eventThread.wait(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void registerListener(SensorEventListener listener, Sensor sensor, int delay) {
		minDelay = Math.min(delay, minDelay);
		if (!running)
			start();
		sensors.put(listener, sensor);
	}

	public void unregisterListener(SensorEventListener listener) {
		sensors.remove(listener);
		if (sensors.isEmpty())
			stop();
	}
}
