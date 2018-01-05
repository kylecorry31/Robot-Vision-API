package com.kylecorry.frc.vision.contourFilters;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ConvexHullContourFilter implements ContourFilter {

    private double minArea;
    private double minPerimeter;
    private Range width;
    private Range height;
    private Range solidity; // 0 - 100
    private Range vertexCount;
    private Range widthToHeightRatio;

    /**
     * A conved hull contour filter.
     *
     * @param minArea            The minimum area of the contour in pixels.
     * @param minPerimeter       The minimum perimeter of the contour in pixels.
     * @param width              The range of the width in pixels.
     * @param height             The range of the height in pixels.
     * @param solidity           The solidity of the contour from 0 to 100 inclusive.
     * @param vertexCount        The range of the number of vertices.
     * @param widthToHeightRatio The ratio of the width to the height of the contour's bounding rectangle.
     */
    public ConvexHullContourFilter(double minArea, double minPerimeter, Range width, Range height, Range solidity, Range vertexCount, Range widthToHeightRatio) {
        this.minArea = minArea;
        this.minPerimeter = minPerimeter;
        this.width = width;
        this.height = height;
        this.solidity = solidity;
        this.vertexCount = vertexCount;
        this.widthToHeightRatio = widthToHeightRatio;
    }

    @Override
    public List<MatOfPoint> filterContours(List<MatOfPoint> contours) {
        List<MatOfPoint> output = new ArrayList<>();
        // Generated and tested by GRIP, variables slightly renamed
        final MatOfInt hull = new MatOfInt();
        for (int i = 0; i < contours.size(); i++) {
            final MatOfPoint contour = contours.get(i);
            final Rect bb = Imgproc.boundingRect(contour);
            if (bb.width < width.start || bb.width > width.end)
                continue;
            if (bb.height < height.start || bb.height > height.end)
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
                double[] point = new double[]{contour.get(index, 0)[0], contour.get(index, 0)[1]};
                mopHull.put(j, 0, point);
            }
            final double solid = 100 * area / Imgproc.contourArea(mopHull);
            if (solid < solidity.start || solid > solidity.end)
                continue;
            if (contour.rows() < vertexCount.start || contour.rows() > vertexCount.end)
                continue;
            final double ratio = bb.width / (double) bb.height;
            if (ratio < widthToHeightRatio.start || ratio > widthToHeightRatio.end)
                continue;
            output.add(contour);
        }
        return output;
    }
}
