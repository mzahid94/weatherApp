package Weather_app.Weathers_app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryController {
	
	private String setLocation = "";
	@FXML private TextField locationText;
	@FXML private Text resultText;
    private WeatherData weatherData;
    
    @FXML
    private TextArea errorField;
    
    @FXML
    private Button primaryButton;

    @FXML
    private void getWeatherInfo(String zipCodeField) {
    	
		String zipCode = zipCodeField;
        weatherData = new WeatherData(zipCode);
        //String weatherInfo = String.format("Temperature: %.2f°F - %.2f°F\nPrecipitation: %.2f inches\nSource: %s", weatherData.getMinTemp(), weatherData.getMaxTemp(), weatherData.getPrecip(), weatherData.getSource());
       
		//weatherInfoLabel.setText(weatherInfo);
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
    	errorField.setVisible(false);
        String loc = locationText.getText().trim().replace(" ", "%20");
    	getWeatherInfo(loc);
    	if(weatherData.error) {
    		errorField.styleProperty().setValue("-fx-background-color:red");
    		errorField.setText("Something went wrong, try a different location.\n");
    		errorField.setVisible(true);
    	}
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userinfo.fxml"));
        Parent root = fxmlLoader.load();
        UserInfoController userInfoController = fxmlLoader.getController();
        User currentUser = new User(-1, "", "", "Guest", "", loc, locationText.getText().trim(), "",new ArrayList<String>() {});
//        String [] fav = {};
//        userInfoController.setFavoriteCities(fav);
        userInfoController.setUser(currentUser, weatherData);

        Scene scene = new Scene(root,640,480);
        Stage stage = (Stage) primaryButton.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Close the login window
//        Stage loginStage = (Stage) primaryButton.getScene().getWindow();
//        loginStage.close();
 
    }
    
    @FXML
    private void updateLocation(KeyEvent e) throws IOException {
    	System.out.println("updating location");
    	System.out.println(e);
    	setLocation += e.getText();
    	System.out.println(setLocation);
    }
    
    
    @FXML
    private void goToLoginPage() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) primaryButton.getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToSignUpPage() throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) primaryButton.getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
