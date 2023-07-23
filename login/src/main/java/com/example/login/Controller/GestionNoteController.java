package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.LoadInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GestionNoteController {
    @FXML
    private TextField nomProfesseurField;

    @FXML
    private TextField nomModuleField;

    @FXML
    private TextField semestreField;

    @FXML
    private TextField sessionField;

    @FXML
    private TextField anneeField;

    @FXML
    private TextField idNiveauField;

    @FXML
    private Button returnButton;

    private LoadInt loadInt = new LoadInt();


    @FXML
    private void Genere(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the new window
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/login/gererExcel.fxml"));

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Gerer Excel");

            // Set the scene with the root node
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the new window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void returne(ActionEvent actionEvent) throws IOException {
        returnButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/login/Homepage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}


