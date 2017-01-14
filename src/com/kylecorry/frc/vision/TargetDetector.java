package com.kylecorry.frc.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class TargetDetector extends Detector<Target> {

	TargetSpecs targetSpecs;

	TargetDetector(TargetSpecs specs, Processor<Target> processor) {
		this.targetSpecs = specs;
		setProcessor(processor);
	}

	@Override
	public List<Target> detect(Mat frame) {
		double[] hue = { targetSpecs.getHue().start, targetSpecs.getHue().end };
		double[] sat = { targetSpecs.getSaturation().start, targetSpecs.getSaturation().end };
		double[] val = { targetSpecs.getValue().start, targetSpecs.getValue().end };
		Pipeline p = new Pipeline();
		p.source0 = frame;
		p.process(hue, sat, val, targetSpecs.getMinPixelArea());
		List<MatOfPoint> contours = p.filterContoursOutput();
		List<Target> detections = new ArrayList<>();
		for (MatOfPoint contour : contours) {
			Rect boundary = Imgproc.boundingRect(contour);
			double aspectRatio = (boundary.width / (double) boundary.height);
			double aspectScore = Scorer.score(aspectRatio, targetSpecs.getWidth() / targetSpecs.getHeight());

			double areaRatio = Imgproc.contourArea(contour) / (double) boundary.area();
			double areaScore = Scorer.score(areaRatio,
					targetSpecs.getArea() / (targetSpecs.getHeight() * targetSpecs.getWidth()));

			double confidence = Math.round((aspectScore + areaScore) / 2) / 100.0;

			Target target = new Target(confidence, boundary.width, boundary.height,
					new Position(boundary.x, boundary.y));
			detections.add(target);
		}
		detections.sort((a, b)->{
			if(b.getIsTargetProbability() > a.getIsTargetProbability()){
				return 1;
			} else if (a.getIsTargetProbability() > b.getIsTargetProbability()){
				return -1;
			}
			return 0;
		});
		return detections;
	}

	public static class Builder {
		private TargetSpecs specs;
		private Processor<Target> processor;

		public Builder setTargetSpecs(TargetSpecs specs) {
			this.specs = specs;
			return this;
		}

		public Builder setProcessor(Processor<Target> processor) {
			this.processor = processor;
			return this;
		}

		public TargetDetector build() {
			return new TargetDetector(specs, processor);
		}

	}

	class Pipeline {

		// Outputs
		private Mat hsvThresholdOutput = new Mat();
		private Mat cvErodeOutput = new Mat();
		private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
		private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();

		// Sources
		private Mat source0;

		/**
		 * This constructor sets up the pipeline
		 */
		public Pipeline() {
		}

		/**
		 * This is the primary method that runs the entire pipeline and updates
		 * the outputs.
		 */
		public void process(double[] hsvThresholdHue, double[] hsvThresholdSaturation, double[] hsvThresholdValue,
				double filterContoursMinArea) {
			// Step HSV_Threshold0:
			Mat hsvThresholdInput = source0;
			hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue,
					hsvThresholdOutput);

			// Step CV_erode0:
			Mat cvErodeSrc = hsvThresholdOutput;
			Mat cvErodeKernel = new Mat();
			org.opencv.core.Point cvErodeAnchor = new org.opencv.core.Point(-1, -1);
			double cvErodeIterations = 1.0;
			int cvErodeBordertype = Core.BORDER_CONSTANT;
			Scalar cvErodeBordervalue = new Scalar(-1);
			cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue,
					cvErodeOutput);

			// Step Find_Contours0:
			Mat findContoursInput = cvErodeOutput;
			boolean findContoursExternalOnly = false;
			findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

			// Step Filter_Contours0:
			ArrayList<MatOfPoint> filterContoursContours = findContoursOutput;
			double filterContoursMinPerimeter = 0;
			double filterContoursMinWidth = 0;
			double filterContoursMaxWidth = 1000;
			double filterContoursMinHeight = 0;
			double filterContoursMaxHeight = 1000;
			double[] filterContoursSolidity = { 0, 100 };
			double filterContoursMaxVertices = 1000000;
			double filterContoursMinVertices = 0;
			double filterContoursMinRatio = 0;
			double filterContoursMaxRatio = 1000;
			filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter,
					filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight,
					filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices,
					filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);

		}

		/**
		 * This method is a generated setter for source0.
		 * 
		 * @param source
		 *            the Mat to set
		 */
		public void setsource0(Mat source0) {
			this.source0 = source0;
		}

		/**
		 * This method is a generated getter for the output of a HSV_Threshold.
		 * 
		 * @return Mat output from HSV_Threshold.
		 */
		public Mat hsvThresholdOutput() {
			return hsvThresholdOutput;
		}

		/**
		 * This method is a generated getter for the output of a CV_erode.
		 * 
		 * @return Mat output from CV_erode.
		 */
		public Mat cvErodeOutput() {
			return cvErodeOutput;
		}

		/**
		 * This method is a generated getter for the output of a Find_Contours.
		 * 
		 * @return ArrayList<MatOfPoint> output from Find_Contours.
		 */
		public ArrayList<MatOfPoint> findContoursOutput() {
			return findContoursOutput;
		}

		/**
		 * This method is a generated getter for the output of a
		 * Filter_Contours.
		 * 
		 * @return ArrayList<MatOfPoint> output from Filter_Contours.
		 */
		public ArrayList<MatOfPoint> filterContoursOutput() {
			return filterContoursOutput;
		}

		/**
		 * Segment an image based on hue, saturation, and value ranges.
		 *
		 * @param input
		 *            The image on which to perform the HSL threshold.
		 * @param hue
		 *            The min and max hue
		 * @param sat
		 *            The min and max saturation
		 * @param val
		 *            The min and max value
		 * @param output
		 *            The image in which to store the output.
		 */
		private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val, Mat out) {
			Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
			Core.inRange(out, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), out);
		}

		/**
		 * Expands area of lower value in an image.
		 * 
		 * @param src
		 *            the Image to erode.
		 * @param kernel
		 *            the kernel for erosion.
		 * @param anchor
		 *            the center of the kernel.
		 * @param iterations
		 *            the number of times to perform the erosion.
		 * @param borderType
		 *            pixel extrapolation method.
		 * @param borderValue
		 *            value to be used for a constant border.
		 * @param dst
		 *            Output Image.
		 */
		private void cvErode(Mat src, Mat kernel, org.opencv.core.Point anchor, double iterations, int borderType,
				Scalar borderValue, Mat dst) {
			if (kernel == null) {
				kernel = new Mat();
			}
			if (anchor == null) {
				anchor = new org.opencv.core.Point(-1, -1);
			}
			if (borderValue == null) {
				borderValue = new Scalar(-1);
			}
			Imgproc.erode(src, dst, kernel, anchor, (int) iterations, borderType, borderValue);
		}

		/**
		 * Sets the values of pixels in a binary image to their distance to the
		 * nearest black pixel.
		 * 
		 * @param input
		 *            The image on which to perform the Distance Transform.
		 * @param type
		 *            The Transform.
		 * @param maskSize
		 *            the size of the mask.
		 * @param output
		 *            The image in which to store the output.
		 */
		private void findContours(Mat input, boolean externalOnly, List<MatOfPoint> contours) {
			Mat hierarchy = new Mat();
			contours.clear();
			int mode;
			if (externalOnly) {
				mode = Imgproc.RETR_EXTERNAL;
			} else {
				mode = Imgproc.RETR_LIST;
			}
			int method = Imgproc.CHAIN_APPROX_SIMPLE;
			Imgproc.findContours(input, contours, hierarchy, mode, method);
		}

		/**
		 * Filters out contours that do not meet certain criteria.
		 * 
		 * @param inputContours
		 *            is the input list of contours
		 * @param output
		 *            is the the output list of contours
		 * @param minArea
		 *            is the minimum area of a contour that will be kept
		 * @param minPerimeter
		 *            is the minimum perimeter of a contour that will be kept
		 * @param minWidth
		 *            minimum width of a contour
		 * @param maxWidth
		 *            maximum width
		 * @param minHeight
		 *            minimum height
		 * @param maxHeight
		 *            maximimum height
		 * @param Solidity
		 *            the minimum and maximum solidity of a contour
		 * @param minVertexCount
		 *            minimum vertex Count of the contours
		 * @param maxVertexCount
		 *            maximum vertex Count
		 * @param minRatio
		 *            minimum ratio of width to height
		 * @param maxRatio
		 *            maximum ratio of width to height
		 */
		private void filterContours(List<MatOfPoint> inputContours, double minArea, double minPerimeter,
				double minWidth, double maxWidth, double minHeight, double maxHeight, double[] solidity,
				double maxVertexCount, double minVertexCount, double minRatio, double maxRatio,
				List<MatOfPoint> output) {
			final MatOfInt hull = new MatOfInt();
			output.clear();
			// operation
			for (int i = 0; i < inputContours.size(); i++) {
				final MatOfPoint contour = inputContours.get(i);
				final Rect bb = Imgproc.boundingRect(contour);
				if (bb.width < minWidth || bb.width > maxWidth)
					continue;
				if (bb.height < minHeight || bb.height > maxHeight)
					continue;
				final double area = Imgproc.contourArea(contour);
				if (area < minArea)
					continue;
				if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter)
					continue;
				Imgproc.convexHull(contour, hull);
				MatOfPoint mopHull = new MatOfPoint();
				mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
				for (int j = 0; j < hull.size().height; j++) {
					int index = (int) hull.get(j, 0)[0];
					double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1] };
					mopHull.put(j, 0, point);
				}
				final double solid = 100 * area / Imgproc.contourArea(mopHull);
				if (solid < solidity[0] || solid > solidity[1])
					continue;
				if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)
					continue;
				final double ratio = bb.width / (double) bb.height;
				if (ratio < minRatio || ratio > maxRatio)
					continue;
				output.add(contour);
			}
		}

	}

}
