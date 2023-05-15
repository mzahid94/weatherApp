package Weather_app.Weathers_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
//    public static User person;
//    public static UserInfoController userInfo = new UserInfoController();
//    public static WeatherData weather;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("secondary"), 640, 480);
        stage.setResizable(false);
        stage.setScene(scene);

        stage.setTitle("Weather app");
        Image titleIcon = new Image("file:icons\\TitleBarIcon.png");
        stage.getIcons().add(titleIcon);

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}