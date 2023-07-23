package com.example.login.Controller.Filiere;

import com.example.login.Controller.GestionFiliereController;
import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import com.example.login.Model.entité.Filiere;
import com.example.login.Model.entité.Professeur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModifierFiliereController {
    GestionFiliereController tmp = new GestionFiliereController();

    public AnchorPane modifierFilierePane;
    public Button modifierFiliereBtn;
    @FXML
    private ComboBox<Departement> departementList;

    @FXML
    private ComboBox<Professeur> professeurList;
    public TextField noteEliminatoireFiliereField;
    public TextField designationFiliereField;

    @FXML
    void modifierFiliere(ActionEvent event) {
        Filiere filiere = tmp.getfiliere();
        filiere.setDepartementId(departementList.getSelectionModel().getSelectedItem().getIdDepartement());
        filiere.setDesignationFiliere(designationFiliereField.getText());
        filiere.setProfesseurId(professeurList.getSelectionModel().getSelectedItem().getIdProfesseur());
        filiere.setNoteEliminatoireFiliere(Double.valueOf(noteEliminatoireFiliereField.getText()));

        // Get the connection from the DatabaseConnection class
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            // Prepare the SQL statement
            String updateQuery = "UPDATE filiere SET departementID = ?, " +
                    "professeurID = ?, noteEliminatoire = ? WHERE nomfiliere = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            // Set the values for the parameters
            preparedStatement.setInt(1, filiere.getDepartementId());
            preparedStatement.setInt(2, filiere.getProfesseurId());
            preparedStatement.setDouble(3, filiere.getNoteEliminatoireFiliere());
            preparedStatement.setString(4, filiere.getDesignationFiliere());

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the prepared statement
            preparedStatement.close();


            // Close the database connection
            connection.close();

            if (rowsAffected > 0) {
                showInformationAlert("Filiere Modified", "The filiere has been successfully modified.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        populateDepartementComboBox();
        populateProfesseurComboBox();
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
    private void showInformationAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
