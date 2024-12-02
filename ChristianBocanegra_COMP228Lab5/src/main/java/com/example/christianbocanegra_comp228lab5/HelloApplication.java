package com.example.christianbocanegra_comp228lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {

    // Utility class for managing database connections.
    public static class DatabaseConnector {
        private static final String URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD"; // Database URL
        private static final String USER = "COMP214_F24_er_5"; // Database username
        private static final String PASSWORD = "password"; // Database password

        // Establishes and returns a connection to the database.
        public static Connection connect() throws SQLException {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
                throw e;
            }
        }
    }

    // Entry point for the JavaFX application. Sets up the user interface.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")); // Load the FXML file
        Scene scene = new Scene(fxmlLoader.load(), 850, 600); // Create a Scene with specified dimensions
        stage.setTitle("Player Game Info"); // Set the title of the window
        stage.setScene(scene); // Attach the scene to the stage
        stage.show(); // Display the stage
    }

    // Main method: Entry point for launching the application.
    public static void main(String[] args) {
        try {
            // Attempt to connect to the database at startup
            Connection connection = DatabaseConnector.connect();
            System.out.println("Database connected"); // Log success message
            connection.close(); // Close the connection after testing
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage()); // Log failure message
        }

        launch(); // Start the JavaFX application
    }
}
