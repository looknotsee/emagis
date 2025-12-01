package project.core;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;

    double scale = 1.5;

    double baseheight = 640;
    double basewidth = 360;

    double height = baseheight * scale;
    double width = basewidth * scale;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginMagis2"), width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // FXML files are placed under resources/project/, so load them with an absolute path
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/project/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}