package com.example.login.Controller.Niveau;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import com.example.login.Model.entité.Filiere;
import com.example.login.Model.entité.Professeur;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AjouterNiveauController {
    private StringProperty titre;
    private IntegerProperty filiereId;
    public Button ajouterClasseBtn;
    public Label filiereMsg;
    public ComboBox<Filiere> filiereList;
    public TextField designationClasseField;
    public Label designationClasseMsg;
    public AnchorPane ajouterClassePane;
    private SimpleObjectProperty<Filiere> filiereSelected;


    @FXML
    void initialize() {
        populateFiliereComboBox();
    }

    public AjouterNiveauController() {

        titre = new SimpleStringProperty();
        filiereId = new SimpleIntegerProperty();
        filiereSelected = new SimpleObjectProperty<Filiere>();

    }
    public void ajouterClasse(ActionEvent actionEvent) {
        if (!designationClasseField.getText().isEmpty()) {
            try {
                titre.set(designationClasseField.getText());
            } catch (NumberFormatException e) {
                titre.set("");
                e.printStackTrace();
            }
        }

        if (!filiereList.getSelectionModel().isEmpty()) {
            filiereId.bindBidirectional(filiereList.getSelectionModel().getSelectedItem().idFiliereProperty());
        }
        if (designationClasseField.getText().isEmpty()) {
            designationClasseMsg.setText("* Ce champ est obligatoire");
            designationClasseMsg.setVisible(true);
        } else {
            designationClasseMsg.setVisible(false);
        }


        if (filiereId.get() == 0) {
            filiereMsg.setText("* Ce champ est obligatoire");
            filiereMsg.setVisible(true);
        } else {
            filiereMsg.setVisible(false);
        }

        if (!designationClasseField.getText().isEmpty() &&  filiereId.get() > 0 ) {
            try {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getConnection();
                Statement statement = connection.createStatement();

                // Retrieve the last existing ID from the database table
                ResultSet resultSet = statement.executeQuery("SELECT MAX(IDniveau) FROM niveau");
                int lastId = 0;
                if (resultSet.next()) {
                    lastId = resultSet.getInt(1);
                }

                // Generate the new ID by incrementing the last ID
                int newId = lastId + 1;

                String query = "INSERT INTO niveau (IDniveau, nomniveau, filiereId) VALUES (?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(query);
                statement1.setInt(1, newId);
                statement1.setString(2, designationClasseField.getText());
                statement1.setInt(3, filiereId.get());
                statement1.executeUpdate();

                connection.close();

                // Show an alert message to inform the user that the filiere is added
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Classe Added");
                alert.setHeaderText(null);
                alert.setContentText("The classe has been added successfully!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void populateFiliereComboBox() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filiere");

            List<Filiere> filieres = new ArrayList<>();
            while (resultSet.next()) {
                int idFiliere = resultSet.getInt("idfiliere");
                String designationFiliere = resultSet.getString("nomfiliere");

                Filiere filiere = new Filiere.FiliereBuilder()
                        .setIdFiliere(idFiliere)
                        .setDesignationFiliere(designationFiliere)
                        .build();

                filieres.add(filiere);
            }

            filiereList.getItems().addAll(filieres);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}