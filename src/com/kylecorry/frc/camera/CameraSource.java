package com.kylecorry.frc.camera;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraSource {

	public static final int USB_CAMERA = 0, HTTP_CAMERA = 1;

	private VideoCamera camera;
	private CvSink sink;
	private Mat frame;
	private Thread detectionThread;
	private volatile boolean detecting = false;

	CameraSource(VideoCamera camera, Detector<?> detector) {
		this.camera = camera;
		detectionThread = new Thread(() -> {
			while (detecting) {
				if (detector != null)
					detector.receiveFrame(getPicture());
			}
		});
		detectionThread.setDaemon(true);
	}

	public void start() {
		CameraServer.getInstance().addCamera(camera);
		sink = CameraServer.getInstance().getVideo(camera);
		detecting = true;
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
		detecting = false;
		try {
			detectionThread.join(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

		private int cameraType = USB_CAMERA;
		private int port = 0;
		private String url = "";
		private int fps = 15;
		private int width = -1, height = -1;

		public Builder(Detector<?> detector) {
			this.detector = detector;
		}

		public Builder setType(int cameraType) {
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
			case HTTP_CAMERA:
				camera = new HttpCamera("cam", url);
				break;
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
