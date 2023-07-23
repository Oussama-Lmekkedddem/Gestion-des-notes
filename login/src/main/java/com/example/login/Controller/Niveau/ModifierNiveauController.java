package com.example.login.Controller.Niveau;

import com.example.login.Controller.GestionFiliereController;
import com.example.login.Controller.GestionNiveauController;
import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import com.example.login.Model.entité.Filiere;
import com.example.login.Model.entité.Niveau;
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

public class ModifierNiveauController {
    GestionNiveauController tmp = new GestionNiveauController();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    GestionNiveauController gestionNiveauController = new GestionNiveauController();

    @FXML
    public Button modifierClasseBtn;
    @FXML
    public ComboBox<Filiere> filiereList;
    @FXML
    public TextField designationClasseField;
    @FXML
    public AnchorPane modifierClassePane;

    public void modifierClasse(ActionEvent actionEvent) {
        Niveau niveau = tmp.getNiveau();
        niveau.setTitre(designationClasseField.getText());
        niveau.setFiliereId(filiereList.getSelectionModel().getSelectedItem().getIdFiliere());

        // Update the database
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE niveau SET titre = ?, filiereId = ? WHERE idNiveau = ?")) {

            statement.setString(1, niveau.getTitre());
            statement.setInt(2, niveau.getFiliereId());
            statement.setLong(3, niveau.getIdNiveau());

            statement.executeUpdate();
            System.out.println("Classe updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update classe!");
        }
    }

    @FXML
    void initialize() {
        populateFiliereComboBox();
    }

    private void populateFiliereComboBox() {
        try {
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filiere");

            List<Filiere> filieres = new ArrayList<>();
            while (resultSet.next()) {
                int idFiliere = resultSet.getInt("idFiliere");
                String designationFiliere = resultSet.getString("nomfiliere");

                Filiere filiere = new Filiere.FiliereBuilder()
                        .setIdFiliere(idFiliere)
                        .setDesignationFiliere(designationFiliere)
                        .build();

                filieres.add(filiere);
            }

            filiereList.getItems().addAll(filieres);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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
