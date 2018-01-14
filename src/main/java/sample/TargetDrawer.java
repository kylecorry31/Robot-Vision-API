package sample;

import com.kylecorry.frc.vision.targeting.Target;
import javafx.scene.paint.Color;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kyle on 4/6/2017.
 */
public class TargetDrawer {

    Scalar outlineColor, pointColor;

    public TargetDrawer(Color outline, Color point){
        outlineColor = new Scalar(outline.getBlue() * 255, outline.getGreen() * 255, outline.getRed() * 255);
        pointColor = new Scalar(point.getBlue() * 255, point.getGreen() * 255, point.getRed() * 255);
    }

    public void draw(Target target, Mat image){
        RotatedRect rect = target.getBoundary();
        Point[] points = new Point[4];
        rect.points(points);
        MatOfPoint contour = new MatOfPoint(points);

        List<MatOfPoint> contours = Arrays.asList(contour);

        Imgproc.drawContours(image, contours, 0, outlineColor);

        Imgproc.drawMarker(image, target.getBoundary().center, pointColor);
    }

}
