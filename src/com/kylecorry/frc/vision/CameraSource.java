package com.kylecorry.frc.vision;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraSource {

	public static enum Type {
		USB, HTTP
	}

	private VideoCamera camera;
	private CvSink sink;
	private Mat frame;
	private Thread detectionThread;
	private Detector<?> detector;

	CameraSource(VideoCamera camera, Detector<?> detector) {
		this.camera = camera;
		this.detector = detector;
	}

	private Thread createDetectionThread() {
		Thread t = new Thread(() -> {
			while (!Thread.interrupted()) {
				if (detector != null)
					detector.receiveFrame(getPicture());
			}
		});
		t.setDaemon(true);
		return t;
	}

	public void start() {
		CameraServer.getInstance().addCamera(camera);
		sink = CameraServer.getInstance().getVideo(camera);
		detectionThread = createDetectionThread();
		detectionThread.start();
	}

	public Mat getPicture() {
		if (sink != null) {
			sink.grabFrame(frame);
			return frame;
		}
		return null;
	}

	public void stop() {
		detectionThread.interrupt();
		sink = null;
		CameraServer.getInstance().removeCamera(camera.getName());
	}

	public void setBrightness(int brightness) {
		camera.setBrightness(brightness);
	}

	public void setExposure(int exposure) {
		camera.setExposureManual(exposure);
	}

	public void setExposureAuto() {
		camera.setExposureAuto();
	}

	public static class Builder {

		private Detector<?> detector;

		private Type cameraType = Type.USB;
		private int port = 0;
		private String url = "";
		private int fps = 15;
		private int width = -1, height = -1;

		public Builder(Detector<?> detector) {
			this.detector = detector;
		}

		public Builder setType(Type cameraType) {
			this.cameraType = cameraType;
			return this;
		}

		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		public Builder setURL(String url) {
			this.url = url;
			return this;
		}

		public Builder setFps(int fps) {
			this.fps = fps;
			return this;
		}

		public Builder setResolution(int width, int height) {
			this.width = width;
			this.height = height;
			return this;
		}

		public CameraSource build() {
			VideoCamera camera;

			switch (cameraType) {
			case HTTP:
				camera = new HttpCamera("cam", url);
				break;
			case USB:
			default:
				camera = new UsbCamera("cam", port);
			}

			camera.setFPS(fps);
			if (width != -1 && height != -1)
				camera.setResolution(width, height);
			return new CameraSource(camera, detector);
		}

	}

}
