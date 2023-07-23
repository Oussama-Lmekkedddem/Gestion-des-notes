package com.example.login.Controller.Proffeseur;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModifierProfesseurController {
    public TextField nomProfesseurField;
    public TextField prenomProfesseurField;
    public TextField emailProfesseurField;
    public ComboBox departementList;
    public Button modifierProfesseurBtn;

    public void modifierProfesseur(ActionEvent actionEvent) {

    }
    private void populateDepartementComboBox() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM departement");

            List<Departement> departements = new ArrayList<>();
            while (resultSet.next()) {
                int idDepartement = resultSet.getInt("idDepartement");
                String nomDepartement = resultSet.getString("nomDepart");

                Departement departement = new Departement.DepartementBuilder()
                        .setIdDepartement(idDepartement)
                        .setDesignationDepartement(nomDepartement)
                        .build();

                departements.add(departement);
            }

            departementList.getItems().addAll(departements);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
