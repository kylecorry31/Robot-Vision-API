package com.kylecorry.frc.vision.detection

import com.kylecorry.geometry.Point
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Moments

import java.util.ArrayList

class TargetDetector
/**
 * Create a TargetDetector to find a single target.

 * @param specs     The specifications of the target.
 * *
 * @param processor The processor to handle the targets when detected.
 */
@JvmOverloads constructor(internal var targetSpecs: TargetSpecs, processor: Detector.Processor<Target>? = null) : Detector<Target>() {

    init {
        setProcessor(processor!!)
    }

    /**
     * Detect the target in the image.

     * @param frame The image to detect targets in.
     * *
     * @return The list of possible targets ordered by confidence from greatest to least.
     */
    override fun detect(frame: Mat): List<Target> {
        val hue = doubleArrayOf(targetSpecs.hue.start.toDouble(), targetSpecs.hue.end.toDouble())
        val sat = doubleArrayOf(targetSpecs.saturation.start.toDouble(), targetSpecs.saturation.end.toDouble())
        val value = doubleArrayOf(targetSpecs.value.start.toDouble(), targetSpecs.value.end.toDouble())
        val p = Pipeline()
        p.process(frame, hue, sat, value, targetSpecs.minPixelArea.toDouble())
        val contours = p.filterContoursOutput()
        val detections = ArrayList<Target>()
        for (contour in contours) {
            val boundary = Imgproc.boundingRect(contour)
            val aspectRatio = boundary.width / boundary.height.toDouble()
            val aspectScore = Scorer.score(aspectRatio, targetSpecs.width / targetSpecs.height)

            val areaRatio = Imgproc.contourArea(contour) / boundary.area().toDouble()
            val areaScore = Scorer.score(areaRatio,
                    targetSpecs.area / (targetSpecs.height * targetSpecs.width))

            val confidence = Math.round((aspectScore + areaScore) / 2) / 100.0

            val moments = Imgproc.moments(contour)

            val centerOfMass = Point(moments.m10 / moments.m00, moments.m01 / moments.m00, 0.0)

            val target = Target(confidence, boundary.width.toDouble(), boundary.height.toDouble(),
                    Point(boundary.x.toDouble(), boundary.y.toDouble(), 0.0), centerOfMass, frame.size())
            detections.add(target)
        }
        detections.sortByDescending { it.probability }
        return detections
    }

    /**
     * This constructor sets up the pipeline
     */
    internal inner class Pipeline {

        // Outputs
        private val hsvThresholdOutput = Mat()
        private val cvErodeOutput = Mat()
        private val findContoursOutput = ArrayList<MatOfPoint>()
        private val filterContoursOutput = ArrayList<MatOfPoint>()

        /**
         * This is the primary method that runs the entire pipeline and updates
         * the outputs.
         */
        fun process(source0: Mat, hsvThresholdHue: DoubleArray, hsvThresholdSaturation: DoubleArray, hsvThresholdValue: DoubleArray,
                    filterContoursMinArea: Double) {
            // Step HSV_Threshold0:
            val hsvThresholdInput = source0
            hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue,
                    hsvThresholdOutput)

            // Step CV_erode0:
            val cvErodeSrc = hsvThresholdOutput
            val cvErodeKernel = Mat()
            val cvErodeAnchor = org.opencv.core.Point(-1.0, -1.0)
            val cvErodeIterations = 1.0
            val cvErodeBordertype = Core.BORDER_CONSTANT
            val cvErodeBordervalue = Scalar(-1.0)
            cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue,
                    cvErodeOutput)

            // Step Find_Contours0:
            val findContoursInput = cvErodeOutput
            val findContoursExternalOnly = false
            findContours(findContoursInput, findContoursExternalOnly, findContoursOutput)

            // Step Filter_Contours0:
            val filterContoursContours = findContoursOutput
            val filterContoursMinPerimeter = 0.0
            val filterContoursMinWidth = 0.0
            val filterContoursMaxWidth = 1000.0
            val filterContoursMinHeight = 0.0
            val filterContoursMaxHeight = 1000.0
            val filterContoursSolidity = doubleArrayOf(0.0, 100.0)
            val filterContoursMaxVertices = 1000000.0
            val filterContoursMinVertices = 0.0
            val filterContoursMinRatio = 0.0
            val filterContoursMaxRatio = 1000.0
            filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter,
                    filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight,
                    filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices,
                    filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput)

        }

        /**
         * This method is a generated getter for the output of a HSV_Threshold.

         * @return Mat output from HSV_Threshold.
         */
        fun hsvThresholdOutput(): Mat {
            return hsvThresholdOutput
        }

        /**
         * This method is a generated getter for the output of a CV_erode.

         * @return Mat output from CV_erode.
         */
        fun cvErodeOutput(): Mat {
            return cvErodeOutput
        }

        /**
         * This method is a generated getter for the output of a Find_Contours.

         * @return ArrayList<MatOfPoint> output from Find_Contours.
        </MatOfPoint> */
        fun findContoursOutput(): ArrayList<MatOfPoint> {
            return findContoursOutput
        }

        /**
         * This method is a generated getter for the output of a
         * Filter_Contours.

         * @return ArrayList<MatOfPoint> output from Filter_Contours.
        </MatOfPoint> */
        fun filterContoursOutput(): ArrayList<MatOfPoint> {
            return filterContoursOutput
        }

        /**
         * Segment an image based on hue, saturation, and value ranges.

         * @param input The image on which to perform the HSL threshold.
         * *
         * @param hue   The min and max hue
         * *
         * @param sat   The min and max saturation
         * *
         * @param val   The min and max value
         * *
         * @param out   The image in which to store the output.
         */
        private fun hsvThreshold(input: Mat, hue: DoubleArray, sat: DoubleArray, `val`: DoubleArray, out: Mat) {
            Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV)
            Core.inRange(out, Scalar(hue[0], sat[0], `val`[0]), Scalar(hue[1], sat[1], `val`[1]), out)
        }

        /**
         * Expands area of lower value in an image.

         * @param src         the Image to erode.
         * *
         * @param kernel      the kernel for erosion.
         * *
         * @param anchor      the center of the kernel.
         * *
         * @param iterations  the number of times to perform the erosion.
         * *
         * @param borderType  pixel extrapolation method.
         * *
         * @param borderValue value to be used for a constant border.
         * *
         * @param dst         Output Image.
         */
        private fun cvErode(src: Mat, kernel: Mat?, anchor: org.opencv.core.Point?, iterations: Double, borderType: Int,
                            borderValue: Scalar?, dst: Mat) {
            var kernel = kernel
            var anchor = anchor
            var borderValue = borderValue
            if (kernel == null) {
                kernel = Mat()
            }
            if (anchor == null) {
                anchor = org.opencv.core.Point(-1.0, -1.0)
            }
            if (borderValue == null) {
                borderValue = Scalar(-1.0)
            }
            Imgproc.erode(src, dst, kernel, anchor, iterations.toInt(), borderType, borderValue)
        }

        /**
         * Sets the values of pixels in a binary image to their distance to the
         * nearest black pixel.

         * @param input The image on which to perform the Distance Transform.
         */
        private fun findContours(input: Mat, externalOnly: Boolean, contours: MutableList<MatOfPoint>) {
            val hierarchy = Mat()
            contours.clear()
            val mode: Int
            if (externalOnly) {
                mode = Imgproc.RETR_EXTERNAL
            } else {
                mode = Imgproc.RETR_LIST
            }
            val method = Imgproc.CHAIN_APPROX_SIMPLE
            Imgproc.findContours(input, contours, hierarchy, mode, method)
        }

        /**
         * Filters out contours that do not meet certain criteria.

         * @param inputContours  is the input list of contours
         * *
         * @param output         is the the output list of contours
         * *
         * @param minArea        is the minimum area of a contour that will be kept
         * *
         * @param minPerimeter   is the minimum perimeter of a contour that will be kept
         * *
         * @param minWidth       minimum width of a contour
         * *
         * @param maxWidth       maximum width
         * *
         * @param minHeight      minimum height
         * *
         * @param maxHeight      maximimum height
         * *
         * @param minVertexCount minimum vertex Count of the contours
         * *
         * @param maxVertexCount maximum vertex Count
         * *
         * @param minRatio       minimum ratio of width to height
         * *
         * @param maxRatio       maximum ratio of width to height
         */
        private fun filterContours(inputContours: List<MatOfPoint>, minArea: Double, minPerimeter: Double,
                                   minWidth: Double, maxWidth: Double, minHeight: Double, maxHeight: Double, solidity: DoubleArray,
                                   maxVertexCount: Double, minVertexCount: Double, minRatio: Double, maxRatio: Double,
                                   output: MutableList<MatOfPoint>) {
            val hull = MatOfInt()
            output.clear()
            // operation
            for (contour in inputContours) {
                val bb = Imgproc.boundingRect(contour)
                if (bb.width < minWidth || bb.width > maxWidth)
                    continue
                if (bb.height < minHeight || bb.height > maxHeight)
                    continue
                val area = Imgproc.contourArea(contour)
                if (area < minArea)
                    continue
                if (Imgproc.arcLength(MatOfPoint2f(*contour.toArray()), true) < minPerimeter)
                    continue
                Imgproc.convexHull(contour, hull)
                val mopHull = MatOfPoint()
                mopHull.create(hull.size().height.toInt(), 1, CvType.CV_32SC2)
                var j = 0
                while (j < hull.size().height) {
                    val index = hull.get(j, 0)[0].toInt()
                    val point = doubleArrayOf(contour.get(index, 0)[0], contour.get(index, 0)[1])
                    mopHull.put(j, 0, *point)
                    j++
                }
                val solid = 100 * area / Imgproc.contourArea(mopHull)
                if (solid < solidity[0] || solid > solidity[1])
                    continue
                if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)
                    continue
                val ratio = bb.width / bb.height.toDouble()
                if (ratio < minRatio || ratio > maxRatio)
                    continue
                output.add(contour)
            }
        }

    }

}
/**
 * Create a TargetDetector to find a single target.

 * @param specs The specifications of the target.
 */
