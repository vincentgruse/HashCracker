package com.vincentgruse.hashcracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    // Entry point of the application
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the user interface
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
            // Create a scene with the loaded FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Set application icon
            Image icon = new Image("SealingTechLogo.jpg");
            stage.getIcons().add(icon);
            // Set the title of the application window
            stage.setTitle("MD5 Hash Cracker");
            // Display the application window
            stage.show();
        } catch (IOException e) {
            // Handle exceptions that may occur during the initialization
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) { launch(args);}
}

