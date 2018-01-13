package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Kyle on 4/7/2017.
 */
public class UISwitcher {

    public static String VISION_PAGE = "camera.fxml";
    public static String HOME_PAGE = "mainGUI.fxml";
    public static String FILTER_PAGE = "filter.fxml";

    private static UISwitcher instance;
    private Stage stage;

    public static synchronized UISwitcher getInstance() {
        if (instance == null)
            instance = new UISwitcher();
        return instance;
    }


    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void switchToPage(String page, Bundle data){
        if(stage == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + page));
        try {
            Parent root = loader.load();
            IController controller = loader.getController();
            controller.init(data);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToPage(String page){
        switchToPage(page, new Bundle());
    }

}
