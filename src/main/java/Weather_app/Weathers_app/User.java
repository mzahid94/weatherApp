package Weather_app.Weathers_app;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String zipcode;
    private String city;
    private String state;
    private ArrayList<String> favoriteCities;

    public User(int id, String username, String password, String firstName, String lastName, String zipcode, String city, String state, ArrayList<String> favoriteCities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.setFavoriteCities(favoriteCities);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }



	public void setFavoriteCities(ArrayList<String> favoriteCities) {
		this.favoriteCities = favoriteCities;
	}

	public ArrayList<String> getFavoriteCities() {
		return favoriteCities;
	}
	

	
}

