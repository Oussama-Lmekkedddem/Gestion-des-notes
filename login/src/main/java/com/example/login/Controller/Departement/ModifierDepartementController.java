package com.example.login.Controller.Departement;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Professeur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModifierDepartementController {
    public Button modifierDepartementBtn;
    @FXML
    private ComboBox<Professeur> professeurList;
    public TextField designationDepartementField;
    public AnchorPane modifierDepartementPane;

    @FXML
    public void initialize() {
        populateProfesseurComboBox();
    };
    public void modifierDepartement(ActionEvent actionEvent) {
    }
    private void populateProfesseurComboBox() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM professeur");

            List<Professeur> professeurs = new ArrayList<>();
            while (resultSet.next()) {
                int idProfesseur = resultSet.getInt("IDprof");
                String nomProfesseur = resultSet.getString("firstname");
                String prenomProfesseur = resultSet.getString("lastname");

                Professeur professeur = new Professeur.ProfesseurBuilder()
                        .setIdProfesseur(idProfesseur)
                        .setNomProfesseur(nomProfesseur)
                        .setPrenomProfesseur(prenomProfesseur)
                        .build();

                professeurs.add(professeur);
            }

            professeurList.getItems().addAll(professeurs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
