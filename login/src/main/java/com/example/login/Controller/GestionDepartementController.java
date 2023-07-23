package com.example.login.Controller;

import com.example.login.LoadInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionDepartementController {

    public GestionDepartementController() {
        path = "/com/example/login/Departement/";
        loadInt = new LoadInt();
    }
    public AnchorPane gestionDepartementPane;
    public BorderPane gestionDepartementBorder;
    @FXML
    private Button returnButton;

    private LoadInt loadInt;

    private String path;



    public void ajouterDepartement(MouseEvent mouseEvent) {
        System.out.println("ajouterDepartement");
        gestionDepartementBorder.setCenter(loadInt.loadInt(path +"AjouterDepartement.fxml"));
    }

    public void modifierDepartement(MouseEvent mouseEvent) {
        System.out.println("modifierDepartement");
        gestionDepartementBorder.setCenter(loadInt.loadInt(path +"ModifierDepartement.fxml"));
    }

    public void supprimerDepartement(MouseEvent mouseEvent) {
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
