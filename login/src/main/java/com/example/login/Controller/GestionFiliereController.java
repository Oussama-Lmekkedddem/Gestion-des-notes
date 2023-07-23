package com.example.login.Controller;

import java.io.IOException;
import java.sql.*;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Filiere;
import com.example.login.LoadInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GestionFiliereController {

    private com.example.login.LoadInt LoadInt;
    private DatabaseConnection databaseConnection;

    private String path;

    public GestionFiliereController() {
        path = "/com/example/login/Filiere/";
        LoadInt = new LoadInt();
        databaseConnection = new DatabaseConnection();
    }

    private static Filiere filiere;

    public AnchorPane gestionFilierePane;
    public BorderPane gestionFiliereBorder;
    public TableView<Filiere> filiereTableView;
    public TableColumn<Filiere, Integer> idFiliereColumn;
    public TableColumn<Filiere, String> designationFiliereColumn;
    public TableColumn<Filiere, Double> noteEliminatoireFiliereColumn;
    public TableColumn<Filiere, Integer> professeurIdColumn;
    public Button returnButton;

    @FXML
    void initialize() {
        // Set up column mappings
        idFiliereColumn.setCellValueFactory(new PropertyValueFactory<>("idFiliere"));
        designationFiliereColumn.setCellValueFactory(new PropertyValueFactory<>("designationFiliere"));
        noteEliminatoireFiliereColumn.setCellValueFactory(new PropertyValueFactory<>("noteEliminatoireFiliere"));
        professeurIdColumn.setCellValueFactory(new PropertyValueFactory<>("professeurId"));

        // Fetch data from database and populate the TableView
        populateTableView();
    }

    private void populateTableView() {
        try {
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filiere");

            while (resultSet.next()) {
                int idFiliere = resultSet.getInt("idfiliere");
                String designationFiliere = resultSet.getString("nomfiliere");
                double noteEliminatoireFiliere = resultSet.getDouble("noteEliminatoire");
                int professeurId = resultSet.getInt("professeurID");

                Filiere filiere = new Filiere.FiliereBuilder()
                        .setIdFiliere(idFiliere)
                        .setDesignationFiliere(designationFiliere)
                        .setNoteEliminatoireFiliere(noteEliminatoireFiliere)
                        .setProfesseurId(professeurId)
                        .build();
                filiereTableView.getItems().add(filiere);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void ajouterFiliere(MouseEvent event) {
        System.out.println("ajouterFiliere");
        gestionFiliereBorder.setCenter(LoadInt.loadInt(path + "AjouterFiliere.fxml"));
    }

    @FXML
    void modifierFiliere(MouseEvent event) {
        System.out.println("modifier Filiere");
        filiere = filiereTableView.getSelectionModel().getSelectedItem();
        gestionFiliereBorder.setCenter(LoadInt.loadInt(path + "ModifierFiliere.fxml"));
    }

    @FXML
    void supprimerFiliere(MouseEvent event) {
        System.out.println("supprimer Filiere");
        int selectedIndex = filiereTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Filiere filiere = filiereTableView.getItems().get(selectedIndex);
            filiereTableView.getItems().remove(selectedIndex);

            // Delete filiere from the database
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            try {
                String deleteQuery = "DELETE FROM filiere WHERE idfiliere = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, filiere.getIdFiliere());
                preparedStatement.executeUpdate();
                preparedStatement.close();

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Filiere getfiliere() {
        return filiere;
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
