package Weather_app.Weathers_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.sql.*;

public class DBConnector {
    
    private static final String url = "jdbc:mysql://database-1.cxr2qxhdhnqo.us-east-2.rds.amazonaws.com:3306/weatherapp";
    private static final String user = "admin";
    private static final String password = "Java123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

	public User getUserByUsernameAndPassword(String username, String password) {
		
		 try (Connection conn = DBConnector.getConnection()) {
			 
	            //String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	            String query ="SELECT users.id, users.username, users.first_name, users.last_name, users.city, users.state, users.zipcode, GROUP_CONCAT(favorite_cities.city) AS favorite_cities\n"
	            		+ "FROM users\n"
	            		+ "LEFT JOIN favorite_cities ON users.id = favorite_cities.id\n"
	            		+ "WHERE users.username = ? AND users.password = ?\n"
	            		+ "GROUP BY users.id;";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();

	            // Retrieve the user information from the result set
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String firstName = rs.getString("first_name");
	                String lastName = rs.getString("last_name");
	                String zipcode = rs.getInt("zipcode")+"";
	                String city = rs.getString("city");
	                String state = rs.getString("state");
	                
	                //check if its null
	                
	                String favoriteCities = rs.getString("favorite_cities");
	                ArrayList<String> favoriteCitiesList;
	                if(favoriteCities != null)
	                	favoriteCitiesList = new ArrayList<>(Arrays.asList(favoriteCities.split(",")));
	                else {
	                	favoriteCitiesList = new ArrayList<>() {};
	                }
	                

	                // Create a new User object with the retrieved information
	                return new User(id, username, password, firstName, lastName, zipcode, city, state,favoriteCitiesList);
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	}
	
	
	public boolean userExist(String username, String password) {
		if(getUserByUsernameAndPassword(username,password) != null) {
			return true;
		}
		return false;
	}

	public User addUser(String username, String password, String fname, String lname, String zip, String city, String state) {
		 
		try (Connection conn = DBConnector.getConnection()) {
			
				if(userExist(username,password)) {
					return null;
				}
	            String query = "INSERT INTO users (username, password, first_name, last_name, zipcode, city, state) VALUES (?, ?, ?, ?, ?, ?, ?);";
	            
	            		
	            PreparedStatement stmt = conn.prepareStatement(query);
	            
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            stmt.setString(3, fname);
	            stmt.setString(4, lname);
	            stmt.setString(5, zip);
	            stmt.setString(6, city);
	            stmt.setString(7, state);
	            
	            int rowsAffected = stmt.executeUpdate();

	            if (rowsAffected > 0) {
	                return getUserByUsernameAndPassword(username, password);
	            } else {
	                
	                return null;
	            }
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	}
	
	public void addFavoriteCity(int id ,String city, String state, int zipCode) {
		
		// Check if city, state, zip code
	    if (city == null) {
	        city = "";
	    }
//	    if (state == null) {
//	        state = "";
//	    }
	    
	    int zip = Objects.requireNonNullElse(zipCode, 0);
	    
	    try (Connection conn = DBConnector.getConnection()) {
	        String query = "INSERT INTO favorite_cities (id, city, state, zipcode) VALUES (?, ?, ?,?);";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, id);
	        stmt.setString(2, city);
	        stmt.setString(3, state);
	        stmt.setInt(4, zip);
	        
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("City added successfully.");
	        } else {
	            System.out.println("Failed to add city.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	
}




