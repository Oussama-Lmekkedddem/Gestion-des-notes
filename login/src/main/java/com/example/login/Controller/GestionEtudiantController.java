package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.ExcelReader;
import com.example.login.Model.entité.Etudiant;
import com.example.login.Model.entité.EtudiantDAO;
import com.example.login.Model.entité.EtudiantData;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class GestionEtudiantController implements Initializable {
    public BorderPane gestionEtudiantBorder;
    public AnchorPane gestionEtudiantPane;
    public TableView etudiantTableView;
    public TableColumn idEtudiantColumn;
    public TableColumn cneEtudiantColumn;
    public TableColumn cinEtudiantColumn;
    public TableColumn nomEtudiantColumn;
    public TableColumn prenomEtudiantColumn;
    public TableColumn classeIdColumn;
    public Button returnButton;
    private ExcelReader excelReader;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bindPropertiesToColumns();
        etudiantTableView.setItems(getRows());
    }

    public void bindPropertiesToColumns() {
        idEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>(EtudiantData.idEtudiant.getValue()));
        cneEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>(EtudiantData.cneEtudiant.getValue()));
        cinEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>(EtudiantData.cinEtudiant.getValue()));
        nomEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>(EtudiantData.nomEtudiant.getValue()));
        prenomEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>(EtudiantData.prenomEtudiant.getValue()));
        classeIdColumn.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>(EtudiantData.classeId.getValue()));
    }

    public ObservableList<Etudiant> getRows() {
        ObservableList<Etudiant> observableListEtudiant = FXCollections.observableArrayList();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM etudiants";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idEtudiant = resultSet.getInt("IDetudiant");
                String cneEtudiant = resultSet.getString("cne");
                String cinEtudiant = resultSet.getString("cni");
                String nomEtudiant = resultSet.getString("firstname");
                String prenomEtudiant = resultSet.getString("lastname");
                int niveauId = resultSet.getInt("niveauId");
                int inscriptionAnnuelleId = resultSet.getInt("inscriptionAnnuelleId");

                Etudiant etudiant = new Etudiant.EtudiantBuilder()
                        .setIdEtudiant(idEtudiant)
                        .setCneEtudiant(cneEtudiant)
                        .setCinEtudiant(cinEtudiant)
                        .setNomEtudiant(nomEtudiant)
                        .setPrenomEtudiant(prenomEtudiant)
                        .setniveauId(niveauId)
                        .setInscriptionAnnuelleId(inscriptionAnnuelleId)
                        .build();

                observableListEtudiant.add(etudiant);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return observableListEtudiant;
    }

    public void ajouterEtudiant(MouseEvent mouseEvent) {
    }

    public void modifierEtudiant(MouseEvent mouseEvent) {
    }

    public void supprimerEtudiant(MouseEvent mouseEvent) {
    }

    public void inscriptionReinscription(MouseEvent mouseEvent) {

        excelReader = new ExcelReader();

        // Créer un sélecteur de fichiers
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Excel", "*.xlsx"));

        // Afficher la boîte de dialogue pour sélectionner le fichier
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Charger les données à partir du fichier Excel
            etudiantTableView.getItems().clear();
            etudiantTableView.getItems().addAll(excelReader.readExcel(selectedFile));
            // Charger les données à partir du fichier Excel
            List<Etudiant> etudiants = excelReader.readExcel(selectedFile);

            // Ajouter les étudiants à la base de données
            EtudiantDAO etudiantDAO = new EtudiantDAO(); // Instantiate the EtudiantDAO object

            try {
                for (Etudiant etudiant : etudiants) {
                    etudiantDAO.ajouterEtudiant(etudiant);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                etudiantDAO.closeConnection();
            }


            // Rafraîchir la TableView avec les étudiants ajoutés
            etudiantTableView.getItems().clear();
            etudiantTableView.getItems().addAll(etudiants);
        } else {
            System.out.println("Le fichier sélectionné n'est pas valide.");
        }

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
