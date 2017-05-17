package com.kylecorry.frc.vision.camera;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

public class CameraSource implements CameraSourceInterface {

    private VideoCamera camera;
    private CvSink sink = new CvSink("CameraSource CvSink");
    private Mat frame = new Mat();

    /**
     * Create a CameraSource from a {@link VideoCamera}.
     *
     * @param camera The camera.
     */
    public CameraSource(VideoCamera camera) {
        this.camera = camera;
    }


    public void start() {
        CameraServer.getInstance().startAutomaticCapture(camera);
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

    public void stop() {
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
