package com.kylecorry.frc.vision.camera

import com.kylecorry.frc.vision.Experimental
import edu.wpi.cscore.CvSink
import edu.wpi.cscore.MjpegServer
import edu.wpi.cscore.VideoCamera
import edu.wpi.first.wpilibj.DriverStation
import org.opencv.core.Mat

import java.util.HashMap

/**
 * A class to allow for the switching between multiple cameras.
 */
@Experimental
class MultiCameraSource
/**
 * Create a MultiCameraSource with no cameras by default.

 * @param name The name of the MultiCameraSource.
 * *
 * @param port The port the MultiCameraSource streams to.
 */
@JvmOverloads constructor(name: String = "multicamerasource", port: Int = 1181) : CameraSourceInterface {

    private val cameras: MutableMap<String, VideoCamera>
    private val cvSink: CvSink
    private var currentCamera: VideoCamera? = null
    private val server: MjpegServer
    private val frame = Mat()

    /**
     * Create a MultiCameraSource with a default camera.

     * @param defaultCamera The default camera.
     */
    constructor(defaultCamera: VideoCamera) : this() {
        addCamera(defaultCamera, DEFAULT)
        switchToCamera(DEFAULT)
    }

    /**
     * Create a MultiCameraSource with a default camera.

     * @param name          The name of the MultiCameraSource.
     * *
     * @param port          The port the MultiCameraSource streams to.
     * *
     * @param defaultCamera The default camera.
     */
    constructor(name: String, port: Int, defaultCamera: VideoCamera) : this(name, port) {
        addCamera(defaultCamera, DEFAULT)
        switchToCamera(DEFAULT)
    }

    init {
        cvSink = CvSink("opencv_" + name)
        server = MjpegServer("server_" + name, port)
        cameras = HashMap<String, VideoCamera>()
    }

    /**
     * Add a camera to the MultiCameraSource.

     * @param camera The camera to add.
     * *
     * @param name   The name for the camera.
     */
    fun addCamera(camera: VideoCamera, name: String) {
        cameras.put(name, camera)
    }

    /**
     * Switch to the camera with the given name. If the camera is not present, an [InvalidCameraException] will be thrown.

     * @param name The name of the camera to switch to.
     */
    fun switchToCamera(name: String) {
        if (cameras.containsKey(name)) {
            currentCamera = cameras[name]
            cvSink.source = currentCamera
            server.source = currentCamera
        } else {
            throw InvalidCameraException(name)
        }
    }

    override val picture: Mat
        get() {
            val frameTime = cvSink.grabFrame(frame)
            if (frameTime == 0L) {
                val error = cvSink.error
                DriverStation.reportError(error, true)
            }
            return frame
        }

    override fun start() {}

    override fun stop() {}

    override fun setBrightness(brightness: Int) {
        currentCamera!!.brightness = brightness
    }

    override fun setExposure(exposure: Int) {
        currentCamera!!.setExposureManual(exposure)
    }

    override fun setExposureAuto() {
        currentCamera!!.setExposureAuto()
    }

    override fun setResolution(width: Int, height: Int) {
        currentCamera!!.setResolution(width, height)
    }

    override fun setFPS(fps: Int) {
        currentCamera!!.setFPS(fps)
    }

    companion object {
        val DEFAULT = "default"
    }
}
/**
 * Create a MultiCameraSource with no cameras by default.
 */
