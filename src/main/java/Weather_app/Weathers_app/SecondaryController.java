package Weather_app.Weathers_app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton,signupButton,guestButton;

    @FXML
    private TextArea loginMessage;

    private User currentUser;
    
    
    
    private Labeled weatherInfoLabel;
    
    private WeatherData weatherData;
    
    @FXML
    private void getWeatherInfo(String zipCodeField) {
    	
		String zipCode = zipCodeField;
        weatherData = new WeatherData(zipCode);
        String weatherInfo = String.format("Temperature: %.2f°F - %.2f°F\nPrecipitation: %.2f inches\nSource: %s", weatherData.getMinTemp(), weatherData.getMaxTemp(), weatherData.getPrecip(), weatherData.getSource());
       
		//weatherInfoLabel.setText(weatherInfo);
    }

    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        loginMessage.setVisible(false);


		loginButton.setDisable(true);
		signupButton.setDisable(true);
		guestButton.setDisable(true);
		usernameField.setEditable(false);
		passwordField.setEditable(false);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	try {
            		// Connect to the database and retrieve the user information
                    DBConnector db = new DBConnector();
	        		currentUser = db.getUserByUsernameAndPassword(username, password);
	        		// Display the user information on a new FXML
	                if (currentUser != null) {
	                    // Set the current user in the SecondaryController class
	                    //this.currentUser = currentUser;
	                    
	                    String zipCode = currentUser.getZipcode();
	
	                    getWeatherInfo(zipCode);
	                    
	                    // Load the user info FXML and pass the currentUser object to it
	                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userinfo.fxml"));
	                    Parent root = fxmlLoader.load();
	                    UserInfoController userInfoController = fxmlLoader.getController();
	                    userInfoController.setUser(currentUser, weatherData);
	
	//                    App.setRoot("userinfo");
	//                    Scene scene = new Scene(root,640,480);
	//                    Stage stage = new Stage();
	//                    stage.setResizable(false);
	//                    stage.setScene(scene);
	//                    stage.show();
	                    
	                    Stage stage = (Stage) loginButton.getScene().getWindow();
	                    
	                    Scene scene = new Scene(root);
	                    stage.setScene(scene);
	                    stage.show();
	
	                    // Close the login window
	//                    Stage loginStage = (Stage) loginButton.getScene().getWindow();
	//                    loginStage.close();
	                } else {
	                	loginMessage.styleProperty().setValue("-fx-background-color:red");
	                    loginMessage.setText("Invalid login");
	                    loginMessage.setVisible(true);
	                }
        		}catch(IOException e) {
        			e.printStackTrace();
        		}
        		finally {
        			loginButton.setDisable(false);
        			signupButton.setDisable(false);
        			guestButton.setDisable(false);
        			usernameField.setEditable(true);
        			passwordField.setEditable(true);
        		}
			}
        });

    }
    
    @FXML
    private void switchToSignup() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
