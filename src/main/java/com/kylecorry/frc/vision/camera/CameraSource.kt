package com.kylecorry.frc.vision.camera

import org.opencv.core.Mat

import edu.wpi.cscore.CvSink
import edu.wpi.cscore.VideoCamera
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.DriverStation

open class CameraSource
/**
 * Create a CameraSource from a [VideoCamera].

 * @param camera The camera.
 */
(private val camera: VideoCamera) : CameraSourceInterface {
    private val sink = CvSink("CameraSource CvSink")
    private val frame = Mat()


    override fun start() {
        CameraServer.getInstance().startAutomaticCapture(camera)
    }

    override val picture: Mat
        get() {
            val frameTime = sink.grabFrame(frame)
            if (frameTime == 0L) {
                val error = sink.error
                DriverStation.reportError(error, true)
            }
            return frame
        }

    override fun stop() {
        CameraServer.getInstance().removeCamera(camera.name)
    }

    override fun setBrightness(brightness: Int) {
        camera.brightness = brightness
    }

    override fun setExposure(exposure: Int) {
        camera.setExposureManual(exposure)
    }

    override fun setResolution(width: Int, height: Int) {
        camera.setResolution(width, height)
    }

    override fun setFPS(fps: Int) {
        camera.setFPS(fps)
    }

    override fun setExposureAuto() {
        camera.setExposureAuto()
    }

}
