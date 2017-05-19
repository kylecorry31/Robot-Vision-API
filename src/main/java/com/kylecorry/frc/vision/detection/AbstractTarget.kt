package com.kylecorry.frc.vision.detection

import com.kylecorry.frc.vision.camera.CameraSpecs
import com.kylecorry.geometry.Point
import org.opencv.core.Size

/**
 * Created by Kylec on 5/16/2017.
 */
abstract class AbstractTarget {

    /**
     * Get the probability that this is the specified target from 0 to 1
     * inclusive.

     * @return The probability that this is the target.
     */
    abstract val probability: Double

    var imageSize: Size? = null

    /**
     * Get the width of the target.

     * @return The target width.
     */
    abstract val width: Double

    /**
     * Get the height of the target.

     * @return The target height.
     */
    abstract val height: Double

    /**
     * Get the position of the top left pixel in the target group.

     * @return The top left pixel position.
     */
    abstract val position: Point

    /**
     * Get the center of mass of the target group in pixels.

     * @return The center of mass in pixels.
     */
    abstract val centerOfMass: Point

    /**
     * Get the position of the center of the target in pixels.

     * @return The center pixel position.
     */
    val centerPosition: Point
        get() = Point(position.x + width / 2.0, position.y + height / 2.0, 0.0)

    /**
     * Compute the distance to the target.

     * @param heightRelativeToCamera The height of the target relative to the camera (distance from camera to target along Y axis).
     * *
     * @param horizontalViewAngle    The horizontal view angle in degrees.
     * *
     * @return The distance to the target in the same units as the heightRelativeToCamera.
     */
    fun computeDistance(heightRelativeToCamera: Double, horizontalViewAngle: Double): Double {
        return CameraSpecs.calculateFocalLengthPixels(imageSize!!.width.toInt(), horizontalViewAngle) * heightRelativeToCamera / (centerPosition.y - imageSize!!.height / 2.0 + 0.5)
    }


    /**
     * Compute the yaw angle to the target from the center of the camera. This returns angle to the target from the coordinate frame placed on the camera.
     * So 0 is directly to the right of the camera, 180 is directly to the left, and 90 is directly ahead.
     * To convert it to allow for the left of center to be negative, and right of center to be positive subtract this angle from 90.

     * @param horizontalViewAngle The horizontal view angle in degrees.
     * *
     * @return The angle to the target from the coordinate frame centered on the camera.
     */
    fun computeAngle(horizontalViewAngle: Double): Double {
        return 90 - Math.toDegrees(Math.atan((centerPosition.x - imageSize!!.width / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels(imageSize!!.width.toInt(), horizontalViewAngle)))
    }

    /**
     * Compute the coordinates of the target group in 3D space.

     * @param heightRelativeToCamera The height of the target relative to the camera (distance from camera to target along Y axis).
     * *
     * @param horizontalViewAngle    The horizontal view angle in degrees.
     * *
     * @return The coordinates of the target group in the same units as the heightRelativeToCamera.
     */
    fun computeCoordinates(heightRelativeToCamera: Double, horizontalViewAngle: Double): Point {
        val distance = computeDistance(heightRelativeToCamera, horizontalViewAngle)
        val x = distance * (centerPosition.x - imageSize!!.width / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels(imageSize!!.width.toInt(), horizontalViewAngle)
        val y = -distance * (centerPosition.y - imageSize!!.height / 2.0 + 0.5) / CameraSpecs.calculateFocalLengthPixels(imageSize!!.width.toInt(), horizontalViewAngle)
        return Point(x, y, distance)
    }


}
