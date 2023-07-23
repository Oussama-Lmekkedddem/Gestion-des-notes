package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.LoadInt;
import com.example.login.Model.entité.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestionProfesseurController implements Initializable {
    private DatabaseConnection databaseConnection;

    private static Professeur professeur ;

    public BorderPane gestionProfesseurBorder;
    public TableView professeurTableView;
    public TableColumn idProfesseurColumn;
    public TableColumn nomProfesseurColumn;
    public TableColumn prenomProfesseurColumn;
    public TableColumn emailProfesseurColumn;
    public TableColumn departementIdColumn;

    private com.example.login.LoadInt LoadInt;
    private String path;
    public Button returnBtn;



    public GestionProfesseurController() {
        path = "/com/example/login/Professeur/";
        LoadInt = new LoadInt();
        databaseConnection = new DatabaseConnection();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bindPropertiesToColumns();
        professeurTableView.setItems(getRows());
    }

    public void bindPropertiesToColumns() {
        idProfesseurColumn.setCellValueFactory(new PropertyValueFactory<>("idProfesseur"));
        nomProfesseurColumn.setCellValueFactory(new PropertyValueFactory<>("nomProfesseur"));
        prenomProfesseurColumn.setCellValueFactory(new PropertyValueFactory<>("prenomProfesseur"));
        emailProfesseurColumn.setCellValueFactory(new PropertyValueFactory<>("emailProfesseur"));
        departementIdColumn.setCellValueFactory(new PropertyValueFactory<>("departementId"));
    }

    public ObservableList<Professeur> getRows() {
        ObservableList<Professeur> observableListProfesseur = FXCollections.observableArrayList();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM professeur";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idProfesseur = resultSet.getInt("IDprof");
                String nomProfesseur = resultSet.getString("firstname");
                String prenomProfesseur = resultSet.getString("lastname");
                int departementId = resultSet.getInt("departementId");

                Professeur professeur = new Professeur.ProfesseurBuilder()
                        .setIdProfesseur(idProfesseur)
                        .setNomProfesseur(nomProfesseur)
                        .setPrenomProfesseur(prenomProfesseur)
                        .setDepartementId(departementId)
                        .build();

                observableListProfesseur.add(professeur);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return observableListProfesseur;
    }
    public void ajouterProfesseur(MouseEvent mouseEvent) {
        System.out.println("ajouterFiliere");
        gestionProfesseurBorder.setCenter(LoadInt.loadInt(path + "AjouterProfesseur.fxml"));
    }

    public void modifierProfesseur(MouseEvent mouseEvent) {
        System.out.println("modifier Filiere");
        professeur = (Professeur) professeurTableView.getSelectionModel().getSelectedItem();
        gestionProfesseurBorder.setCenter(LoadInt.loadInt(path + "ModifierProfesseur.fxml"));
    }

    public void supprimerProfesseur(MouseEvent mouseEvent) {
    }
    public void returne(ActionEvent actionEvent) throws IOException {
        returnBtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/login/Homepage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}
