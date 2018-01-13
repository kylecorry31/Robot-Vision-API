package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class OpenCVImageUtils {

    public static boolean matEquals(Mat img1, Mat img2){
        Mat out = new Mat();
        Core.compare(img1, img2, out, Core.CMP_NE);
        return Core.countNonZero(out) == 0;
    }

}
