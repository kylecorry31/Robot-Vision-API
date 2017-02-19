package com.kylecorry.frc.vision;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

public class CameraSource implements CameraSourceInterface {

    @Deprecated
    public enum Type {
        USB, HTTP
    }

    private VideoCamera camera;
    private CvSink sink = new CvSink("CameraSource CvSink");
    private Mat frame = new Mat();
    private Thread detectionThread;
    private Detector<?> detector;

    /**
     * Create a CameraSource from a {@link VideoCamera} with a {@link Detector} to process the images asynchronously.
     *
     * @param camera   The camera.
     * @param detector The detector to process the images.
     */
    public CameraSource(VideoCamera camera, Detector<?> detector) {
        this.camera = camera;
        setDetector(detector);
        sink.setSource(camera);
    }

    /**
     * Create a CameraSource from a {@link VideoCamera}.
     *
     * @param camera The camera.
     */
    public CameraSource(VideoCamera camera) {
        this(camera, null);
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
        CameraServer.getInstance().startAutomaticCapture(camera);
        detectionThread = createDetectionThread();
        detectionThread.start();
    }

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
     * Set the detector of the camera.
     *
     * @param detector The detector.
     */
    public void setDetector(Detector<?> detector) {
        this.detector = detector;
    }

    public void stop() {
        if (detectionThread != null)
            detectionThread.interrupt();
        CameraServer.getInstance().removeCamera(camera.getName());
    }

    public void setBrightness(int brightness) {
        camera.setBrightness(brightness);
    }

    public void setExposure(int exposure) {
        camera.setExposureManual(exposure);
    }

    public void setResolution(int width, int height) {
        camera.setResolution(width, height);
    }

    public void setFPS(int fps) {
        camera.setFPS(fps);
    }
    
    public void setExposureAuto() {
        camera.setExposureAuto();
    }

    /**
     * @deprecated This class was a bit confusing to use, instead use the constructors in {@link CameraSource} instead.
     */
    @Deprecated
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
         * @param detector The detector to process images with.
         */
        public Builder(Detector<?> detector) {
            this.detector = detector;
        }

        /**
         * Set the type of camera that the CameraSource will get its images
         * from.
         *
         * @param cameraType The type of camera.
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
         * @param port The USB port number of the camera.
         * @return This Builder for chaining.
         */
        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        /**
         * Set the url of the camera if the camera is an HTTP camera.
         *
         * @param url The url of the camera.
         * @return This Builder for chaining.
         */
        public Builder setURL(String url) {
            this.url = url;
            return this;
        }

        /**
         * Set the frames per second of the camera.
         *
         * @param fps The FPS of the camera.
         * @return This Builder for chaining.
         */
        public Builder setFps(int fps) {
            this.fps = fps;
            return this;
        }

        /**
         * Set the resolution of the camera.
         *
         * @param width  The width of the camera image in pixels.
         * @param height The height of the camera image in pixels.
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
