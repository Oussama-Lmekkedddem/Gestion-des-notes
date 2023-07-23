package com.example.login;

import com.example.login.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    public PasswordField passwordPasswordField;
    @FXML
    public Button loginButton;
    @FXML
    public TextField usernameTextField;
    @FXML
    public Label loginMessageLabel;


    public void loginButtonOnAction(ActionEvent e) {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void validateLogin() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String verifyLogin = "select count(1) from utilisateur where Name = '" + usernameTextField.getText() + "' and passwordUtilisateur= '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    openHomePage(); // Call the method to open the new page
                } else {
                    loginMessageLabel.setText("Invalid login");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openHomePage() {
        try {
            // Load the new FXML file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage primaryStage = (Stage) loginButton.getScene().getWindow();
            primaryStage.setScene(homePageScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
