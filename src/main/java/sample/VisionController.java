//package sample;
//
//import com.kylecorry.frc.vision.targetDetection.SingleTarget;
//import com.kylecorry.frc.vision.targetDetection.Target;
//import com.kylecorry.frc.vision.targetDetection.TargetDetector;
//import com.kylecorry.geometry.Point;
//import com.sun.imageio.plugins.common.ImageUtil;
//import com.sun.org.apache.regexp.internal.RE;
//import javafx.animation.AnimationTimer;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.image.WritableImage;
//import javafx.scene.paint.Color;
//import org.opencv.core.Mat;
//import org.opencv.videoio.VideoCapture;
//
//import java.net.URL;
//import java.util.List;
//
///**
// * Created by Kyle on 4/2/2017.
// */
//public class VisionController implements IController{
//
//    TargetDrawer targetDrawer;
//
//    @FXML
//    ImageView camera;
//
//    @FXML
//    Button targetToggle;
//
//    @FXML
//    Label distanceLabel;
//
//    @FXML
//    Label angleLabel;
//
//    @FXML
//    Label confidenceLabel;
//
//    private Mat image;
//    private VideoCapture cap;
//    private TargetDetector detector;
//
//    private TargetState state = TargetState.ONE;
//    private Main main;
//    private double relHeight, angle;
//    private AnimationTimer cameraFeed;
//
//
//    public VisionController() {
//
//    }
//
//
//
//    public void displayCameraFeed() {
//        cap.read(image);
//
//        List<SingleTarget> targets = detector.detect(image);
//
//        outlineTarget(targets);
//
//        if (state != TargetState.ONE) {
//            angleLabel.setText("");
//            distanceLabel.setText("");
//            confidenceLabel.setText("");
//        }
//
//
//        Image img = ImageUtils.toImage(image);
//        camera.setFitWidth(image.width());
//        camera.setFitHeight(image.height());
//        camera.setImage(img);
//    }
//
//
//    public void toggleTargetOutlining() {
//        switch (state) {
//            case ALL:
//                targetToggle.setText("Don't outline targets");
//                state = TargetState.ONE;
//                break;
//            case ONE:
//                targetToggle.setText("Outline all targets");
//                state = TargetState.NONE;
//                break;
//            case NONE:
//                targetToggle.setText("Outline best target");
//                state = TargetState.ALL;
//        }
//    }
//
//    private void outlineTarget(List<Target> targets) {
//        if (targets.isEmpty()) {
//            angleLabel.setText("");
//            distanceLabel.setText("");
//            confidenceLabel.setText("");
//            return;
//        }
//
//        if (state == TargetState.ONE) {
//            Target target = targets.get(0);
//            outline(target);
//        } else if (state == TargetState.ALL) {
//            for (Target target : targets) {
//                outline(target);
//            }
//        }
//
//
//    }
//
//    public void goHome(){
//        cameraFeed.stop();
//        cap.release();
//        UISwitcher.getInstance().switchToPage(UISwitcher.HOME_PAGE);
//    }
//
//    private void outline( target) {
//        targetDrawer.draw(target, image);
//        if (state == TargetState.ONE) {
//            angleLabel.setText("Angle: " + Math.round(target.computeAngle(angle) * 10) / 10.0);
//            confidenceLabel.setText("Confidence: " + Math.round(target.getIsTargetProbability() * 100) / 100.0);
//
//            if (relHeight != Double.MAX_VALUE) {
//                Point coordinates = target.computeCoordinates(relHeight, angle);
//
//                double x = Math.round(coordinates.x * 10) / 10.0;
//                double y = Math.round(coordinates.y * 10) / 10.0;
//                double z = Math.round(coordinates.z * 10) / 10.0;
//
//                distanceLabel.setText("Coordinates: " + new Point(x, y, z));
//            } else {
//                distanceLabel.setText("");
//            }
//        }
//    }
//
//    @Override
//    public void init(Bundle data) {
//        this.relHeight = data.getDouble(Resources.KEY_REL_HEIGHT);
//        this.angle = data.getDouble(Resources.KEY_ANGLE);
//        image = new Mat();
//        cap = new VideoCapture(0);
//        targetDrawer = new TargetDrawer(Color.RED, Color.web("#00ff00"));
//
//        detector = new TargetDetector(data.getTargetSpecs(Resources.KEY_TARGET_SPECS), data.getFilter(Resources.KEY_TARGET_FILTER), data.getContourFilter(Resources.KEY_CONTOUR_FILTER));
//        targetToggle.setText("Don't outline targets");
//        cameraFeed = new AnimationTimer() {
//
//            @Override
//            public void handle(long now) {
//                displayCameraFeed();
//            }
//        };
//        cameraFeed.start();
//    }
//
//
//    enum TargetState {
//        ALL, ONE, NONE
//    }
//}
