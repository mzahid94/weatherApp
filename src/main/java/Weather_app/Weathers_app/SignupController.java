package Weather_app.Weathers_app;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {
	
	private String fname = "",lname = "",city = "",state = "",zip = "",username = "",password = "",confirmPassword = "";
	private String errors = "Error:\n";
	private int errorCount = 0;
	
	@FXML private TextField firstNameField,lastNameField,cityField,stateField,zipField,usernameField;
	@FXML private PasswordField passwordField,passwordConfirmField;
	@FXML private TextArea errorField;
	
	@FXML
	private void goToSecondary() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) errorField.getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void pressSignUp() throws IOException {
		errorField.styleProperty().setValue("-fx-background-color:red");
		errors = "Error:\n";
		errorCount = 0;
		errorField.setVisible(false);
		username = usernameField.getText();
		fname = firstNameField.getText();
		lname = lastNameField.getText();
		city = cityField.getText();
		state = stateField.getText();
		zip = zipField.getText();
		password = passwordField.getText();
		confirmPassword = passwordConfirmField.getText();
		
		checkEmpty(fname,"First Name");
		checkEmpty(city,"City");
		checkEmpty(state,"State");
		checkZip(zip);
		checkUsername(username);
		checkPassword(password,confirmPassword);
		
		if(errorCount > 0) {
			errorField.setText(errors);
			errorField.setVisible(true);
		}
		else {
			
			// Connect to the database and retrieve the user information
	        DBConnector db = new DBConnector();
	        
	        
	        //For now im returning the user in case we want to display the name or something but can remove this and not return anything
	        User queryResult = db.addUser(username, password, fname, lname, zip, city, state);
	        
	        if(queryResult == null) {
	        	errorField.setText(errors + "Sign up Unsuccessful.\n");
	        	errorField.setVisible(true);
	        }
			
			System.out.println("information correct.");
			
			System.out.println("Name: " + queryResult.getFirstName()  + " " + queryResult.getLastName() + "/n zipcode: " + 
					queryResult.getZipcode() + "/n city: " + queryResult.getCity() + "/n state: " + queryResult.getState());

			errorField.styleProperty().setValue("-fx-background-color:green");
			errorField.setText("Sign up successful.");
			errorField.setVisible(true);
			
			
			//Sending back to home page for now.
//			App.setRoot("primary");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
	        Parent root = fxmlLoader.load();
	        Stage stage = (Stage) errorField.getScene().getWindow();
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
	}
	
	private void checkEmpty(String name,String type) {
		name = name.trim();
		
		if(name.equals("")) {
			errors += type + " cannot be empty.\n";
			errorCount++;
		}
	}
	
	private void checkEmail(String email) {
		Pattern pattern = Pattern.compile("(^[a-zA-Z0-9_]+)@([a-zA-Z]+)\\.([a-zA-Z]+)$");
		Matcher matcher = pattern.matcher(email);
		boolean result = matcher.find();
		if(email.length() == 0) {
			errors += "Email can not be empty. \n";
			errorCount++;
		}
		else if(!result) {
			errors += "Email has incorrect format.\n";
			errorCount++;
		}
		
	}
	
	private void checkZip(String zip) {
		Pattern pattern = Pattern.compile("^\\d{5}([- ]\\d{4})?$");
		Matcher matcher = pattern.matcher(zip);
		
		if(!matcher.find()) {
			errors += "Zip code not valid. \n";
			errorCount++;
		}
	}
	
	private void checkUsername(String username) {
		checkEmpty(username,"Username");
	}
	
	private void checkPassword(String password,String confirm) {
		boolean letter = false,number = false,special = false;
		if(!password.equals(confirm)) {
			errors += "Passwords do not match. \n";
			errorCount++;
		}
		if(password.length() < 5) {
			errors += "Password length too short. \n";
			errorCount++;
		}
		for(char c: password.toCharArray()) {
			if(Character.isLetter(c)) {
				letter = true;
			}
			if(Character.isDigit(c)) {
				number = true;
			}
			if(!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c)) {
				special = true;
			}
		}
		
		if(!letter || !number || !special) {
			errors += "Password must contain a letter number and special character. \n";
			errorCount++;
		}
	}
}