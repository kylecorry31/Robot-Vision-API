package sample;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.targeting.TargetFinder;
import com.kylecorry.geometry.Range;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FilterController implements IController, Initializable {

    @FXML
    ImageView camera;

    @FXML
    Slider hueMin, hueMax, satMin, satMax, valMin, valMax, areaMin, areaMax, fullMin, fullMax, aspectMin, aspectMax;

    @FXML
    Button contourToggle;

    @FXML
    Label hVA, vVA;

    @FXML
    Label dist, area;

    private boolean detectContours = false;

    private Mat image;
    private VideoCapture cap;
    private TargetFilter filter;

    TargetDrawer targetDrawer;


    private AnimationTimer cameraFeed;

    private int num = 1;

    private int frame = 0;

    public void displayCameraFeed() {


//        cap.read(image);

        frame++;

        if(frame % 100 == 0) {
            num %= 17;
            num++;
            frame = 0;
        }

        image = Imgcodecs.imread("peg/peg" + num + ".jpg");

        filter = new HSVFilter(new Range((int) hueMin.getValue(), (int) hueMax.getValue()),
                new Range((int) satMin.getValue(), (int) satMax.getValue()),
                new Range((int) valMin.getValue(), (int) valMax.getValue()));

        Mat filteredImage = filter.filter(image);


        if (detectContours) {
            ContourFilter contourFilter = new StandardContourFilter(new com.kylecorry.geometry.Range(areaMin.getValue(), areaMax.getValue()),
                    new com.kylecorry.geometry.Range(fullMin.getValue(), fullMax.getValue()),
                    new com.kylecorry.geometry.Range(aspectMin.getValue(), aspectMax.getValue()), image.size().area());

            TargetFinder detector = new TargetFinder(new CameraSettings(false, new FOV(60, 60), new Resolution((int) image.size().width, (int) image.size().height)), filter, contourFilter, TargetGrouping.DOUBLE);
            List<Target> targets = detector.findTargets(image);
            if(!targets.isEmpty()){
                area.setText(targets.get(0).getPercentArea() + "");
                hVA.setText(targets.get(0).getHorizontalAngle() + "");
                vVA.setText(targets.get(0).getVerticalAngle() + "");
//                dist.setText(targets.get(0).getPercentArea() + "");
            }
            image = filteredImage;
            Imgproc.cvtColor(image, image, Imgproc.COLOR_GRAY2BGR);
            outlineTarget(targets);
        } else {
            image.release();
            image = filteredImage;
        }


        Image img = ImageUtils.toImage(image);
        image.release();
        filteredImage.release();
        camera.setFitWidth(image.width());
        camera.setFitHeight(image.height());
        camera.setImage(img);
    }

    public void goHome() {
        cameraFeed.stop();
        cap.release();
        UISwitcher.getInstance().switchToPage(UISwitcher.HOME_PAGE);
    }

    private void outlineTarget(List<Target> targets) {
        for (Target target : targets) {
            outline(target);
        }

    }

    private void outline(Target target) {
        targetDrawer.draw(target, image);
    }

    @Override
    public void init(Bundle data) {
        image = new Mat();
        cap = new VideoCapture(0);
        targetDrawer = new TargetDrawer(Color.RED, Color.web("#00ff00"));


        filter = new HSVFilter(new Range(0, 360), new Range(0, 255), new Range(0, 255));

        cameraFeed = new AnimationTimer() {

            @Override
            public void handle(long now) {
                displayCameraFeed();
            }
        };
        cameraFeed.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hueMin.setMin(0);
        hueMin.setMax(359);
        hueMin.setValue(0);
        hueMax.setMin(0);
        hueMax.setMax(359);
        hueMax.setValue(359);
        satMin.setMin(0);
        satMin.setMax(255);
        satMin.setValue(0);
        satMax.setMin(0);
        satMax.setMax(255);
        satMax.setValue(255);
        valMin.setMin(0);
        valMin.setMax(255);
        valMin.setValue(0);
        valMax.setMin(0);
        valMax.setMax(255);
        valMax.setValue(255);

        areaMin.setMin(0);
        areaMin.setMax(100);
        areaMin.setValue(0);

        areaMax.setMin(0);
        areaMax.setMax(100);
        areaMax.setValue(100);

        fullMin.setMin(0);
        fullMin.setMax(100);
        fullMin.setValue(0);

        fullMax.setMin(0);
        fullMax.setMax(100);
        fullMax.setValue(100);

        aspectMin.setMin(0);
        aspectMin.setMax(20);
        aspectMin.setValue(0);

        aspectMax.setMin(0);
        aspectMax.setMax(20);
        aspectMax.setValue(20);
    }

    public void toggleContours() {
        detectContours = !detectContours;
    }
}
