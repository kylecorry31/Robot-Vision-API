package com.kylecorry.frc.vision.camera;

import com.kylecorry.frc.vision.Detector;
import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

public class CameraSource implements CameraSourceInterface {

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

}
