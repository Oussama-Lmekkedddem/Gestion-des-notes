package com.example.login.Controller.Departement;

import com.example.login.Model.entité.Departement;
import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Professeur;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterDepartementController implements Initializable {

    @FXML
    private ComboBox<Professeur> professeurList;
    @FXML
    private Button ajouterDepartmentBtn;
    @FXML
    private Label designationDepartementMsg;
    @FXML
    private TextField designationDepartementField;
    @FXML
    private AnchorPane ajouterDepartementPane;

    private ObservableList<Professeur> observableListProfesseur;
    private IntegerProperty professeurId;
    private ObjectProperty<Professeur> professeurSelected;
    private StringProperty designationDepartement;

    private final int MAX_LENGTH = 100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateProfesseurComboBox();
        initializeBindings();
    }

    public AjouterDepartementController() {
        designationDepartement = new SimpleStringProperty();
        professeurId = new SimpleIntegerProperty();
        professeurSelected = new SimpleObjectProperty<>();
    }

    private void initializeBindings() {
        designationDepartement.bindBidirectional(designationDepartementField.textProperty());
        professeurSelected.bind(professeurList.valueProperty());
    }

    @FXML
    void ajouterDepartement(ActionEvent event) {
        if (professeurList.getSelectionModel().isEmpty()) {
            designationDepartementMsg.setText("* Ce champ est obligatoire");
            designationDepartementMsg.setVisible(true);
            return; // Return if no professor is selected
        } else {
            designationDepartementMsg.setVisible(false);
        }

        if (designationDepartement.get().isEmpty()) {
            designationDepartementMsg.setText("* Ce champ est obligatoire");
            designationDepartementMsg.setVisible(true);
            return; // Return if designation is empty
        } else {
            designationDepartementMsg.setVisible(false);
        }

        // Retrieve the selected professor and department information
        Professeur selectedProfesseur = professeurSelected.get();
        String departmentDesignation = designationDepartement.get();

        // Connect to the database
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();



        try {
            Statement statement1 = connection.createStatement();


            // Retrieve the last existing ID from the database table
            ResultSet resultSet = statement1.executeQuery("SELECT MAX(idDepartement) FROM departement");
            int lastId = 0;
            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }

            // Generate the new ID by incrementing the last ID
            int newId = lastId + 1;
            // Create the SQL query to insert the department
            String insertQuery = "INSERT INTO departement (idDepartement ,nomDepart, professeurId) VALUES (? ,?, ?)";

            // Prepare the statement and set the parameters
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, newId);
            statement.setString(2, departmentDesignation);
            statement.setInt(3, selectedProfesseur.getIdProfesseur());

            // Execute the statement
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            // Clear the input fields
            designationDepartement.set("");
            professeurList.getSelectionModel().clearSelection();

            // Show an alert message to inform the user that the filiere is added
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Departement Added");
            alert.setHeaderText(null);
            alert.setContentText("The departement  has been added successfully!");
            alert.showAndWait();
        } catch (SQLException e) {
            // Handle any potential errors
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

            professeurList.setItems(FXCollections.observableArrayList(professeurs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
