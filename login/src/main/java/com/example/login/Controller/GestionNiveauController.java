package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.LoadInt;
import com.example.login.Model.entité.Filiere;
import com.example.login.Model.entité.Niveau;
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

import java.io.IOException;
import java.sql.*;

public class GestionNiveauController {
    private static Niveau niveau;

    public AnchorPane gestionClassePane;
    public BorderPane gestionClasseBoreder;
    @FXML
    public TableColumn<Niveau, Integer> idClasseColumn;
    @FXML
    public TableColumn<Niveau, String> designationClasseColumn;
    @FXML
    public TableColumn<Niveau, Integer> filiereIdColumn;
    @FXML
    public TableView<Niveau> classeTableView;
    @FXML
    private Button returnButton;
    private com.example.login.LoadInt LoadInt;
    private DatabaseConnection databaseConnection;

    private String path;

    public GestionNiveauController() {
        path = "/com/example/login/Niveau/";
        LoadInt = new LoadInt();
        databaseConnection = new DatabaseConnection();
    }

    @FXML
    void initialize() {
        // Set up column mappings
        idClasseColumn.setCellValueFactory(new PropertyValueFactory<>("idNiveau"));
        designationClasseColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        filiereIdColumn.setCellValueFactory(new PropertyValueFactory<>("filiereId"));

        // Fetch data from database and populate the TableView
        populateTableView();
    }

    private void populateTableView() {
        try {
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM niveau");

            while (resultSet.next()) {
                int idNiveau = resultSet.getInt("IDniveau");
                String titre = resultSet.getString("nomniveau");
                int filiereId = resultSet.getInt("filiereId");

                Niveau niveau = new Niveau.NiveauBuilder()
                        .withIdNiveau(idNiveau)
                        .withTitre(titre)
                        .withFiliereId(filiereId)
                        .build();
                classeTableView.getItems().add(niveau);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterClasse(MouseEvent mouseEvent) {
        System.out.println("ajouterNiveau");
        gestionClasseBoreder.setCenter(LoadInt.loadInt(path + "AjouterNiveau.fxml"));
    }

    public void modifierClasse(MouseEvent mouseEvent) {
        System.out.println("modifierNiveau");
        gestionClasseBoreder.setCenter(LoadInt.loadInt(path + "ModifierNiveau.fxml"));
    }

    public void supprimerClasse(MouseEvent mouseEvent) {

        System.out.println("supprimer niveau");
        int selectedIndex = classeTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Niveau niveau = classeTableView.getItems().get(selectedIndex);
            classeTableView.getItems().remove(selectedIndex);

            // Delete filiere from the database
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            try {
                String deleteQuery = "DELETE FROM niveau WHERE IDniveau = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, niveau.getIdNiveau());
                preparedStatement.executeUpdate();
                preparedStatement.close();

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        GestionNiveauController.niveau = niveau;
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
