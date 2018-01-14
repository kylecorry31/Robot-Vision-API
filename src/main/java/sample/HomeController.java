//package sample;
//
//
//import com.kylecorry.frc.vision.contourFilters.ContourFilter;
//import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
//import com.kylecorry.frc.vision.filters.HSVFilter;
//import com.kylecorry.frc.vision.filters.TargetFilter;
//import com.kylecorry.frc.vision.targetDetection.TargetSpecs;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import org.opencv.core.Range;
//
//public class HomeController implements IController {
//
//    @FXML
//    TextField minHueTF;
//
//    @FXML
//    TextField maxHueTF;
//
//    @FXML
//    TextField minSatTF;
//
//    @FXML
//    TextField maxSatTF;
//
//    @FXML
//    TextField minValTF;
//
//    @FXML
//    TextField maxValTF;
//
//    @FXML
//    TextField heightTF;
//
//    @FXML
//    TextField widthTF;
//
//    @FXML
//    TextField areaTF;
//
//    @FXML
//    TextField pixelAreaTF;
//
//    @FXML
//    TextField distTF;
//
//    @FXML
//    TextField viewAngleTF;
//
//    public void startVision() {
//
//
//        boolean req = required(minHueTF) &&
//                required(maxHueTF) && required(minSatTF) &&
//                required(maxSatTF) && required(minValTF) &&
//                required(maxValTF) && required(widthTF) &&
//                required(heightTF) && required(areaTF) &&
//                required(pixelAreaTF) && required(viewAngleTF);
//
//        if (!req)
//            return;
//
//        TargetSpecs targetSpecs = new TargetSpecs() {
//            @Override
//            public double getWidth() {
//                return Double.valueOf(widthTF.getText());
//            }
//
//            @Override
//            public double getHeight() {
//                return Double.valueOf(heightTF.getText());
//            }
//
//            @Override
//            public double getArea() {
//                return Double.valueOf(areaTF.getText());
//            }
//
//        };
//
//        TargetFilter filter = new HSVFilter(new Range(Integer.valueOf(minHueTF.getText()), Integer.valueOf(maxHueTF.getText())),
//                new Range(Integer.valueOf(minSatTF.getText()), Integer.valueOf(maxSatTF.getText())),
//                new Range(Integer.valueOf(minValTF.getText()), Integer.valueOf(maxValTF.getText())));
//
//
//        ContourFilter contourFilter = new StandardContourFilter(new com.kylecorry.geometry.Range(10, 100),
//                new com.kylecorry.geometry.Range(50, 100), new com.kylecorry.geometry.Range(0, 20), 640*480);
//
//
//        String distString = distTF.getText();
//        double relHeight = Double.MAX_VALUE;
//        if (!distString.isEmpty()) {
//            relHeight = Double.valueOf(distString);
//        }
//
//        double angle = Double.valueOf(viewAngleTF.getText());
//
//
//        Bundle data = new Bundle();
//        data.putDouble(Resources.KEY_REL_HEIGHT, relHeight);
//        data.putDouble(Resources.KEY_ANGLE, angle);
//        data.putTargetSpecs(Resources.KEY_TARGET_SPECS, targetSpecs);
//        data.putFilter(Resources.KEY_TARGET_FILTER, filter);
//        data.putContourFilter(Resources.KEY_CONTOUR_FILTER, contourFilter);
//
//
//        UISwitcher.getInstance().switchToPage(UISwitcher.FILTER_PAGE, data);
//    }
//
//
//    public boolean required(TextField field) {
//        String fieldText = field.getText();
//        if (fieldText.isEmpty()) {
//            field.requestFocus();
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void init(Bundle data) {
//
//    }
//}
