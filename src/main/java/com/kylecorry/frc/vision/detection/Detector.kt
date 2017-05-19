package com.kylecorry.frc.vision.detection

import org.opencv.core.Mat

abstract class Detector<T> {

    private var processor: Processor<T>? = null

    /**
     * Detect objects in an image.

     * @param frame
     * *            The image to detect objects in.
     * *
     * @return The list of objects in the image.
     */
    abstract fun detect(frame: Mat): List<T>

    /**
     * Accept a frame and process the output of detect using the processor if
     * supplied.

     * @param frame
     * *            The image to process.
     */
    fun receiveFrame(frame: Mat) {
        if (processor != null) {
            processor!!.receiveDetections(detect(frame))
        }
    }

    /**
     * Set the processor of the detector for processing the output of the
     * detect.

     * @param processor
     * *            The processor to process the objects found in the image.
     */
    fun setProcessor(processor: Processor<T>) {
        this.processor = processor
    }

    interface Processor<T> {
        /**
         * Receive the objects detected in the image to process.

         * @param detections
         * *            The detections in the image.
         */
        fun receiveDetections(detections: List<T>)
    }

}
