package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                // Load the properties file
                Properties properties = new Properties();
                InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("application.properties");
                if (input == null) {
                    System.err.println("Unable to find application.properties");
                    return null;
                }
                properties.load(input);

                // Extract properties
                String url = properties.getProperty("db.url");
                String driverName = properties.getProperty("db.driver");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");

                // Load the driver
                Class.forName(driverName);

                // Create the connection
                con = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                System.err.println("Driver not found: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Failed to create the database connection: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error reading the properties file: " + e.getMessage());
            }
        }
        return con;
    }
}
