package com.example.login.Controller.Proffeseur;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import com.example.login.Model.entité.Professeur;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AjouterProfesseurController {
    public AnchorPane ajouterProfesseurPane;
    public TextField nomProfesseurField;
    public Label nomProfeseurMsg;
    public TextField prenomProfesseurField;
    public Label prenomProfeseurMsg;
    public TextField emailProfesseurField;
    public Label emailProfeseurMsg;
    @FXML
    private ComboBox<Departement> departementList;
    public Button ajouterProfesseurBtn;
    private StringProperty nomProfesseur;
    private StringProperty prenomProfesseur;
    private StringProperty emailProfesseur;
    private IntegerProperty departementId;
    private SimpleObjectProperty<Departement> departementSelected;
    @FXML
    void initialize() {
        populateDepartementComboBox();
    }

    public AjouterProfesseurController() {

        nomProfesseur = new SimpleStringProperty();
        prenomProfesseur = new SimpleStringProperty();
        emailProfesseur = new SimpleStringProperty();
        departementId = new SimpleIntegerProperty();
        departementSelected = new SimpleObjectProperty<Departement>();
    }

    public void ajouterProfesseur(ActionEvent actionEvent) {
        if (nomProfesseurField.getText().isEmpty()) {
            nomProfeseurMsg.setText("* Ce champ est obligatoire");
            nomProfeseurMsg.setVisible(true);
        }
        if (prenomProfesseurField.getText().isEmpty()) {
            prenomProfeseurMsg.setText("* Ce champ est obligatoire");
            prenomProfeseurMsg.setVisible(true);
        }
        if (emailProfesseurField.getText().isEmpty()) {
            emailProfeseurMsg.setText("* Ce champ est obligatoire");
            emailProfeseurMsg.setVisible(true);
        }
        if (!departementList.getSelectionModel().isEmpty()) {
            departementId.bindBidirectional(departementList.getSelectionModel().getSelectedItem().idDepartementProperty());
        }
        if (!nomProfesseurField.getText().isEmpty() && !prenomProfesseurField.getText().isEmpty() && !emailProfesseurField.getText().isEmpty()
                &&  departementId.get() > 0) {
            try {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getConnection();
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT MAX(IDprof) FROM professeur");
                int lastId = 0;
                if (resultSet.next()) {
                    lastId = resultSet.getInt(1);
                }

                int newId = lastId + 1;

                String query = "INSERT INTO professeur (IDprof, firstname, lastname, departementId) VALUES (?, ?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(query);
                statement1.setInt(1, newId);
                statement1.setString(2, nomProfesseurField.getText());
                statement1.setString(3, prenomProfesseurField.getText());
                statement1.setInt(4, departementId.get());

                statement1.executeUpdate();

                connection.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Professeur Added");
                alert.setHeaderText(null);
                alert.setContentText("The Professeur has been added successfully!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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
