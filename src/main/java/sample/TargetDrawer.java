package sample;

import com.kylecorry.frc.vision.targetDetection.Target;
import javafx.scene.paint.Color;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

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
        Rect boundary = new Rect(MathExt.roundToInt(target.getPosition().x), MathExt.roundToInt(target.getPosition().y),
                MathExt.roundToInt(target.getWidth()), MathExt.roundToInt(target.getHeight()));
        ImageEditor.drawRectangleToMat(image, boundary, outlineColor);

        Imgproc.drawMarker(image, new org.opencv.core.Point(target.getCenterPosition().x, target.getCenterPosition().y),
                pointColor);
    }

}
