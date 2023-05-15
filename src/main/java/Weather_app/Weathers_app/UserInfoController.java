package Weather_app.Weathers_app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserInfoController {

    private User curr;
    private String state;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> favoriteCityCombo;

    @FXML
    private Button addFavoriteButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label maxTempLabel;

    @FXML
    private Label minTempLabel;

    @FXML
    private Label precipitationLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label precipitationProbabilityLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private Label conditionsLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label feelsLikeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Label sourceLabel;

    @FXML
    private Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void logOut() throws IOException {
//    	App.setRoot("secondary");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) logoutButton.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void searchLocation() {
        String location = searchField.getText().trim().replace(" ", "%20");

        WeatherData newLocWeather = new WeatherData(location);

        if (newLocWeather.error) {
            searchField.setPromptText("Location Error try another location");
        } else {
            setWeather(location, newLocWeather);
        }
    }

    @FXML
    public void changedLocation() {
        System.out.println(favoriteCityCombo.getValue());
        String location = favoriteCityCombo.getValue();
        WeatherData newLocWeather = new WeatherData(location);
        setWeather(location, newLocWeather);
    }

    @FXML
    public void addFavoriteCity() {

        if (!favoriteCityCombo.getItems().contains(cityLabel.getText())) {
            System.out.println("adding favorite city");
            favoriteCityCombo.getItems().add(cityLabel.getText());
            //database code

            DBConnector db = new DBConnector();

            db.addFavoriteCity(curr.getId(), cityLabel.getText(), "", 0);
        }

    }

    public void setFavoriteCities(List<String> favoriteCities) {
        favoriteCityCombo.getItems().setAll(favoriteCities);
    }

    public void setWeather(String newloc, WeatherData weatherData) {
        ImageIconMapping imageIconMapping;
        dateLabel.setText(weatherData.getDateTime().toLocalDate().toString());
        imageIconMapping = new ImageIconMapping("label_date");
        dateLabel.setGraphic(imageIconMapping.setHeightAndWidth(40,40));
        cityLabel.setText(newloc.toUpperCase());
        imageIconMapping = new ImageIconMapping("label_location");
        cityLabel.setGraphic(imageIconMapping.setHeightAndWidth(35,35));

        maxTempLabel.setText("Max Temperature: " + (int) weatherData.getMaxTemp() + "℃");
        imageIconMapping = new ImageIconMapping("label_max_temp");
        maxTempLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        minTempLabel.setText("Min Temperature: " + (int) weatherData.getMinTemp() + "℃");
        imageIconMapping = new ImageIconMapping("label_min_temp");
        minTempLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        precipitationLabel.setText("Precipitation: " + weatherData.getPrecip());
        imageIconMapping = new ImageIconMapping("label_rain");
        precipitationLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        temperatureLabel.setText("Temperature: " + (int) weatherData.getTemperature());
        imageIconMapping = new ImageIconMapping(weatherData.getIconCode());
        temperatureLabel.setGraphic(imageIconMapping.setHeightAndWidth(50, 50));

        feelsLikeLabel.setText("Feels Like: " + (int) Math.round(weatherData.getFeelsLike()));

        humidityLabel.setText("Humidty:" + weatherData.getHumidity());
        imageIconMapping = new ImageIconMapping("label_humidity");
        humidityLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        conditionsLabel.setText("Conditions: " + weatherData.getConditions());

        windSpeedLabel.setText("Wind Speed: " + weatherData.getWindSpeed());
        imageIconMapping = new ImageIconMapping("label_wind_speed");
        windSpeedLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        precipitationProbabilityLabel.setText("Rain Prob: " + weatherData.getPrecipitationProbability() + "%");
        descriptionLabel.setText("" + weatherData.getDescription());
    }

    public void setUser(User currentUser, WeatherData weatherData) {

        this.curr = currentUser;
        welcomeLabel.setText("Welcome, " + currentUser.getFirstName().toUpperCase());

        // Add favorite cities
        // Get the favorite cities from your database or any other source
        List<String> favoriteCities = currentUser.getFavoriteCities();
        ImageIconMapping imageIconMapping;

        setFavoriteCities(favoriteCities);
        dateLabel.setText(weatherData.getDateTime().toLocalDate().toString());
        imageIconMapping = new ImageIconMapping("label_date");
        dateLabel.setGraphic(imageIconMapping.setHeightAndWidth(40,40));

        cityLabel.setText(currentUser.getCity().toUpperCase());
        imageIconMapping = new ImageIconMapping("label_location");
        cityLabel.setGraphic(imageIconMapping.setHeightAndWidth(35,35));

        maxTempLabel.setText("Max Temperature: " + (int) weatherData.getMaxTemp() + "℃");
        imageIconMapping = new ImageIconMapping("label_max_temp");
        maxTempLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        minTempLabel.setText("Min Temperature: " + (int) weatherData.getMinTemp() + "℃");
        imageIconMapping = new ImageIconMapping("label_min_temp");
        minTempLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        precipitationLabel.setText("Precipitation: " + weatherData.getPrecip());
        imageIconMapping = new ImageIconMapping("label_rain");
        precipitationLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        temperatureLabel.setText("Temperature: " + (int) weatherData.getTemperature() + "℃");
        imageIconMapping = new ImageIconMapping(weatherData.getIconCode());
        temperatureLabel.setGraphic(imageIconMapping.setHeightAndWidth(50, 50));

        feelsLikeLabel.setText("Feels Like: " + (int) weatherData.getFeelsLike());

        humidityLabel.setText("Humidty:" + weatherData.getHumidity());
        imageIconMapping = new ImageIconMapping("label_humidity");
        humidityLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        conditionsLabel.setText("Conditions: " + weatherData.getConditions());

        windSpeedLabel.setText("Wind Speed: " + weatherData.getWindSpeed());
        imageIconMapping = new ImageIconMapping("label_wind_speed");
        windSpeedLabel.setGraphic(imageIconMapping.setHeightAndWidth(25,25));

        precipitationProbabilityLabel.setText("Rain Prob: " + weatherData.getPrecipitationProbability() + "%");
        descriptionLabel.setText("" + weatherData.getDescription());

        if (currentUser.getFirstName().equals("Guest")) {
            favoriteCityCombo.setVisible(false);
            addFavoriteButton.setVisible(false);
            searchField.setVisible(false);
            searchButton.setVisible(false);
        }

    }

    public void launchSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        root = loader.load();
        Settings weatherScreenController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}