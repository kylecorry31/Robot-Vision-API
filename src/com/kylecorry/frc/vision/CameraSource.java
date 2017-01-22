package com.kylecorry.frc.vision;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

public class CameraSource {

	public static enum Type {
		USB, HTTP
	}

	private VideoCamera camera;
	private CvSink sink = new CvSink("CameraSource CvSink");
	private Mat frame = new Mat();
	private Thread detectionThread;
	private Detector<?> detector;

	CameraSource(VideoCamera camera, Detector<?> detector) {
		this.camera = camera;
		this.detector = detector;
		sink.setSource(camera);
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

	/**
	 * Start the camera and target detection. A call to this method will start
	 * the steam of the camera images to the Camera Server and will begin the
	 * target detection processes.
	 */
	public void start() {
		CameraServer.getInstance().startAutomaticCapture(camera);
		detectionThread = createDetectionThread();
		detectionThread.start();
	}

	/**
	 * Get the current image from the camera.
	 * 
	 * @return The current image from the camera.
	 */
	public Mat getPicture() {
		long frameTime = sink.grabFrame(frame);
		if (frameTime == 0) {
			String error = sink.getError();
			DriverStation.reportError(error, true);
			return null;
		} else {
			return frame;
		}
	}

	/**
	 * Stop the camera from streaming and detecting targets.
	 */
	public void stop() {
		detectionThread.interrupt();
		CameraServer.getInstance().removeCamera(camera.getName());
	}

	/**
	 * Set the brightness of the camera from 0 to 100 inclusive.
	 * 
	 * @param brightness
	 *            The brightness of the camera.
	 */
	public void setBrightness(int brightness) {
		camera.setBrightness(brightness);
	}

	/**
	 * Set the exposure of the camera from 0 to 100 inclusive.
	 * 
	 * @param exposure
	 *            The exposure of the camera.
	 */
	public void setExposure(int exposure) {
		camera.setExposureManual(exposure);
	}

	/**
	 * Allow the camera to choose its own exposure automatically.
	 */
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

		/**
		 * Create a CameraSource.Builder without a detector.
		 */
		public Builder() {
			detector = null;
		}

		/**
		 * Create a CameraSource.Builder with a detector for target detection.
		 * 
		 * @param detector
		 *            The detector to process images with.
		 */
		public Builder(Detector<?> detector) {
			this.detector = detector;
		}

		/**
		 * Set the type of camera that the CameraSource will get its images
		 * from.
		 * 
		 * @param cameraType
		 *            The type of camera.
		 * @return This Builder for chaining.
		 */
		public Builder setType(Type cameraType) {
			this.cameraType = cameraType;
			return this;
		}

		/**
		 * Set the port of the camera if the camera is a USB camera. This
		 * defaults to 0.
		 * 
		 * @param port
		 *            The USB port number of the camera.
		 * @return This Builder for chaining.
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		/**
		 * Set the url of the camera if the camera is an HTTP camera.
		 * 
		 * @param url
		 *            The url of the camera.
		 * @return This Builder for chaining.
		 */
		public Builder setURL(String url) {
			this.url = url;
			return this;
		}

		/**
		 * Set the frames per second of the camera.
		 * 
		 * @param fps
		 *            The FPS of the camera.
		 * @return This Builder for chaining.
		 */
		public Builder setFps(int fps) {
			this.fps = fps;
			return this;
		}

		/**
		 * Set the resolution of the camera.
		 * 
		 * @param width
		 *            The width of the camera image in pixels.
		 * @param height
		 *            The height of the camera image in pixels.
		 * @return This Builder for chaining.
		 */
		public Builder setResolution(int width, int height) {
			this.width = width;
			this.height = height;
			return this;
		}

		/**
		 * Build the CameraSource with the set attributes.
		 * 
		 * @return The CameraSource.
		 */
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
