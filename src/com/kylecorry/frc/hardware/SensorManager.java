package com.kylecorry.frc.hardware;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SensorManager {

	public static enum SensorDelay {
		FASTEST(0), NORMAL(20), SLOW(200);

		private final long delay;

		SensorDelay(long millis) {
			delay = millis;
		}

		public long getDelayMillis() {
			return delay;
		}
	}

	private SensorDelay minDelay = SensorDelay.SLOW;
	private Map<SensorEventListener, Sensor> sensors;
	private Thread eventThread;

	private SensorManager() {
		sensors = new HashMap<>();
	}
	
	private Thread createEventThread(){
		Thread t = new Thread(() -> {
			while (!Thread.interrupted()) {
				for (Entry<SensorEventListener, Sensor> entry : sensors.entrySet()) {
					Sensor s = entry.getValue();
					entry.getKey()
							.onSensorChanged(new SensorEvent(s, s.getDriver().read(), System.currentTimeMillis()));
				}
				try {
					Thread.sleep(minDelay.getDelayMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.setDaemon(true);
		return t;
	}

	private static class InstanceHolder {
		public static SensorManager sm = new SensorManager();
	}

	public static SensorManager getInstance() {
		return InstanceHolder.sm;
	}

	private void start() {
		eventThread = createEventThread();
		eventThread.start();
	}

	private void stop() {
		eventThread.interrupt();
	}

	public void registerListener(SensorEventListener listener, Sensor sensor, SensorDelay delay) {
		if (delay.getDelayMillis() < minDelay.getDelayMillis())
			minDelay = delay;
		if (!eventThread.isAlive())
			start();
		sensors.put(listener, sensor);
	}

	public void unregisterListener(SensorEventListener listener) {
		sensors.remove(listener);
		if (sensors.isEmpty())
			stop();
	}
}
