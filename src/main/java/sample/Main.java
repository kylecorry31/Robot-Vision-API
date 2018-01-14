package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        OpenCVManager.getInstance().load(new SystemProperties());
        UISwitcher.getInstance().setStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/filter.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Robot Vision API Testing");
        primaryStage.setScene(new Scene(root, 640, 720));
        primaryStage.show();
        UISwitcher.getInstance().switchToPage(UISwitcher.FILTER_PAGE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
