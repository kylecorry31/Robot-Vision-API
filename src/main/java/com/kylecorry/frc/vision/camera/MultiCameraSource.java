package com.kylecorry.frc.vision.camera;

import com.kylecorry.frc.vision.Experimental;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.DriverStation;
import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to allow for the switching between multiple cameras.
 */
@Experimental
public class MultiCameraSource implements CameraSourceInterface {

    private Map<String, VideoCamera> cameras;
    public static final String DEFAULT = "default";
    private CvSink cvSink;
    private VideoCamera currentCamera;
    private MjpegServer server;
    private Mat frame = new Mat();

    /**
     * Create a MultiCameraSource with a default camera.
     *
     * @param defaultCamera The default camera.
     */
    public MultiCameraSource(VideoCamera defaultCamera) {
        this();
        addCamera(defaultCamera, DEFAULT);
        switchToCamera(DEFAULT);
    }

    /**
     * Create a MultiCameraSource with a default camera.
     *
     * @param name          The name of the MultiCameraSource.
     * @param port          The port the MultiCameraSource streams to.
     * @param defaultCamera The default camera.
     */
    public MultiCameraSource(String name, int port, VideoCamera defaultCamera) {
        this(name, port);
        addCamera(defaultCamera, DEFAULT);
        switchToCamera(DEFAULT);
    }

    /**
     * Create a MultiCameraSource with no cameras by default.
     */
    public MultiCameraSource() {
        this("multicamerasource", 1181);
    }

    /**
     * Create a MultiCameraSource with no cameras by default.
     *
     * @param name The name of the MultiCameraSource.
     * @param port The port the MultiCameraSource streams to.
     */
    public MultiCameraSource(String name, int port) {
        cvSink = new CvSink("opencv_" + name);
        server = new MjpegServer("server_" + name, port);
        cameras = new HashMap<>();
    }

    /**
     * Add a camera to the MultiCameraSource.
     *
     * @param camera The camera to add.
     * @param name   The name for the camera.
     */
    public void addCamera(VideoCamera camera, String name) {
        cameras.put(name, camera);
    }

    /**
     * Switch to the camera with the given name. If the camera is not present, an {@link InvalidCameraException} will be thrown.
     *
     * @param name The name of the camera to switch to.
     */
    public void switchToCamera(String name) {
        if (cameras.containsKey(name)) {
            currentCamera = cameras.get(name);
            cvSink.setSource(currentCamera);
            server.setSource(currentCamera);
        } else {
            throw new InvalidCameraException(name);
        }
    }

    @Override
    public Mat getPicture() {
        long frameTime = cvSink.grabFrame(frame);
        if (frameTime == 0) {
            String error = cvSink.getError();
            DriverStation.reportError(error, true);
            return null;
        } else {
            return frame;
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setBrightness(int brightness) {
        currentCamera.setBrightness(brightness);
    }

    @Override
    public void setExposure(int exposure) {
        currentCamera.setExposureManual(exposure);
    }

    @Override
    public void setExposureAuto() {
        currentCamera.setExposureAuto();
    }

    @Override
    public void setResolution(int width, int height) {
        currentCamera.setResolution(width, height);
    }

    @Override
    public void setFPS(int fps) {
        currentCamera.setFPS(fps);
    }
}
