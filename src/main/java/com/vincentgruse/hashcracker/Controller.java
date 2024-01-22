package com.vincentgruse.hashcracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;

public class Controller {

    @FXML
    private TextField hashValue;
    @FXML
    private TextField fileLocation;
    @FXML
    private Label notification;

    // Browse button event handler
    @FXML
    private void browse(ActionEvent e) {
        System.out.println("Browse selected");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt"));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            fileLocation.setText(f.getAbsolutePath());
            System.out.println(f.getAbsolutePath());
        }
    }

    String userHash;
    String userFilePath;

    // Submit button event handler
    @FXML
    public void submit(ActionEvent event) {
        System.out.println("Submit selected");
        try {
            userFilePath = fileLocation.getText();
            userHash = hashValue.getText();
            System.out.println(userHash);
            System.out.println(userFilePath);

            if (Handler.isValidInput(userHash) && Handler.isValidFile(userFilePath)) {
                new Thread(() -> {
                    // Perform MD5 hash conversion and check
                    String userString = Handler.convertToMDFive(userFilePath, userHash);
                    if (userString != null) {
                        notification.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
                        System.out.println("User string found:\n\n" + userString);
                        notification.setText("User string found:\n\n" + userString);
                    } else {
                        notification.setStyle("-fx-text-fill: #cf2e2e; -fx-font-weight: bold");
                        System.out.println("Hash value not found.");
                        notification.setText("Hash value not found.");
                    }
                }).start();

                // Set styles and notification for the searching phase
                notification.setText("Searching...");
                System.out.println("Searching...");
                fileLocation.setStyle("-fx-border-color: default; -fx-border-width: default");
                hashValue.setStyle("-fx-border-color: default; -fx-border-width: default");
                notification.setStyle("-fx-text-fill: black; -fx-font-weight: bold");

            } else if (!Handler.isValidInput(userHash) && !Handler.isValidFile(userFilePath)) {
                // Handle errors when both hash value and file are invalid
                hashValue.setStyle("-fx-border-color: #cf2e2e; -fx-border-width: 2px");
                fileLocation.setStyle("-fx-border-color: #cf2e2e; -fx-border-width: 2px");
                notification.setStyle("-fx-text-fill: #cf2e2e; -fx-font-weight: bold");
                notification.setText("*Error: Please enter a valid MD5 Hash value.\n*Error: Please choose a valid file type (.txt).");
                System.out.println("MD5 Hash and file location are invalid.");
            } else if (!Handler.isValidInput(userHash) && Handler.isValidFile(userFilePath)) {
                // Handle error when only hash value is invalid
                fileLocation.setStyle("-fx-border-color: default; -fx-border-width: default");
                hashValue.setStyle("-fx-border-color: #cf2e2e; -fx-border-width: 2px");
                notification.setStyle("-fx-text-fill: #cf2e2e; -fx-font-weight: bold");
                notification.setText("*Error: Please enter a valid MD5 Hash value.");
                System.out.println("MD5 Hash is invalid.");
            } else if (Handler.isValidInput(userHash) && !Handler.isValidFile(userFilePath)) {
                // Handle error when only the file is invalid
                hashValue.setStyle("-fx-border-color: default; -fx-border-width: default");
                fileLocation.setStyle("-fx-border-color: #cf2e2e; -fx-border-width: 2px");
                notification.setStyle("-fx-text-fill: #cf2e2e; -fx-font-weight: bold");
                notification.setText("*Error: Please choose a valid file type (.txt).");
                System.out.println("File location is invalid.");
            }
        } catch (Exception e) {
            // Handle unexpected exceptions
            notification.setText("Error");
            System.out.println("Error.");
        }
    }
}
