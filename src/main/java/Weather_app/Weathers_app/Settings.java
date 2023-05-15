package Weather_app.Weathers_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class Settings {

    @FXML
    RadioButton metricRadio;

    @FXML
    RadioButton imperialRadio;

    @FXML
    RadioButton ukRadio;

    @FXML
    Button goBackButton;

    @FXML
    Button saveButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ToggleGroup toggleGroup;
    UserConfigs userConfigs;

    public Settings() {
        toggleGroup = new ToggleGroup();
//        metricRadio = new RadioButton("metric");
//        imperialRadio = new RadioButton("imperial");
//        ukRadio = new RadioButton("uk");
        metricRadio.setToggleGroup(toggleGroup);
        imperialRadio.setToggleGroup(toggleGroup);
        ukRadio.setToggleGroup(toggleGroup);

        userConfigs = new UserConfigs();
        String currentUnits = userConfigs.getUnits();
        if (currentUnits.equals("metric"))
            metricRadio.setSelected(true);
        else if (currentUnits.equals("imperial"))
            imperialRadio.setSelected(true);
        else if (currentUnits.equals("uk"))
            ukRadio.setSelected(true);
    }

    public void saveAction(ActionEvent event) throws IOException {
        userConfigs.setUnits(((RadioButton)toggleGroup.getSelectedToggle()).getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userInfo.fxml"));
        root = loader.load();
        UserInfoController weatherScreenController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBackAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userInfo.fxml"));
        root = loader.load();
        UserInfoController weatherScreenController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
