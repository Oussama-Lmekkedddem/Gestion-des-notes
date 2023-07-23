package com.example.login.Controller.Filiere;
import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Departement;
import com.example.login.Model.entité.Professeur;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AjouterFiliereController {

    @FXML
    public TextField designationFiliereField;
    @FXML
    public Label designationFiliereMsg;
    public TextField noteEliminatoireFiliereField;
    public Label noteEliminatoireFiliereMsg;
    @FXML
    private ComboBox<Departement> departementList;

    @FXML
    private ComboBox<Professeur> professeurList;
    public Label professeurMsg;
    public Label departementMsg;
    public Button ajouterFiliereBtn;

    private StringProperty designationFiliere;
    private DoubleProperty noteEliminatoireFiliere;
    private IntegerProperty professeurId;
    private IntegerProperty departementId;
    private SimpleObjectProperty<Professeur> professeurSelected;
    private SimpleObjectProperty<Departement> departementSelected;

    @FXML
    void initialize() {
        populateDepartementComboBox();
        populateProfesseurComboBox();
    }

    public AjouterFiliereController() {

        designationFiliere = new SimpleStringProperty();
        noteEliminatoireFiliere = new SimpleDoubleProperty(-1);
        professeurId = new SimpleIntegerProperty();
        departementId = new SimpleIntegerProperty();
        professeurSelected = new SimpleObjectProperty<Professeur>();
        departementSelected = new SimpleObjectProperty<Departement>();
    }

    public void ajouterFiliere(ActionEvent actionEvent) {
        if (!noteEliminatoireFiliereField.getText().isEmpty()) {
            noteEliminatoireFiliere.bind(new DoubleBinding() {
                {
                    bind(noteEliminatoireFiliereField.textProperty());
                }

                @Override
                protected double computeValue() {
                    return Double.parseDouble(noteEliminatoireFiliereField.getText());
                }
            });
        }
        if (!departementList.getSelectionModel().isEmpty()) {
            departementId.bindBidirectional(departementList.getSelectionModel().getSelectedItem().idDepartementProperty());
        }
        if (!professeurList.getSelectionModel().isEmpty()) {
            professeurId.bindBidirectional(professeurList.getSelectionModel().getSelectedItem().idProfesseurProperty());
        }
        if (designationFiliereField.getText().isEmpty()) {
            designationFiliereMsg.setText("* Ce champ est obligatoire");
            designationFiliereMsg.setVisible(true);
        } else {
            designationFiliereMsg.setVisible(false);
        }
        if (noteEliminatoireFiliere.get() == -1) {
            noteEliminatoireFiliereMsg.setText("* Ce champ est obligatoire");
            noteEliminatoireFiliereMsg.setVisible(true);
        } else {
            noteEliminatoireFiliereMsg.setVisible(false);
        }
        if (noteEliminatoireFiliere.get() > 12 || noteEliminatoireFiliere.get() < 7) {
            noteEliminatoireFiliereMsg.setText("* Note Eliminatoire Valide [7,12]");
            noteEliminatoireFiliereMsg.setVisible(true);
        } else {
            noteEliminatoireFiliereMsg.setVisible(false);
        }

        if (professeurId.get() == 0) {
            professeurMsg.setText("* Ce champ est obligatoire");
            professeurMsg.setVisible(true);
        } else {
            professeurMsg.setVisible(false);
        }
        if (departementId.get() == 0) {
            departementMsg.setText("* Ce champ est obligatoire");
            departementMsg.setVisible(true);
        } else {
            departementMsg.setVisible(false);
        }

        if (!designationFiliereField.getText().isEmpty() && noteEliminatoireFiliere.get() >= 7
                && noteEliminatoireFiliere.get() <= 12 && professeurId.get() > 0 && departementId.get() > 0) {
            try {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getConnection();
                Statement statement = connection.createStatement();

                // Retrieve the last existing ID from the database table
                ResultSet resultSet = statement.executeQuery("SELECT MAX(idfiliere) FROM filiere");
                int lastId = 0;
                if (resultSet.next()) {
                    lastId = resultSet.getInt(1);
                }

                // Generate the new ID by incrementing the last ID
                int newId = lastId + 1;

                String query = "INSERT INTO filiere (idfiliere, nomfiliere, noteEliminatoire, departementID, professeurID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(query);
                statement1.setInt(1, newId);
                statement1.setString(2, designationFiliereField.getText());
                statement1.setDouble(3, noteEliminatoireFiliere.get());
                statement1.setInt(4, departementId.get());
                statement1.setInt(5, professeurId.get());
                statement1.executeUpdate();

                connection.close();

                // Show an alert message to inform the user that the filiere is added
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Filiere Added");
                alert.setHeaderText(null);
                alert.setContentText("The filiere has been added successfully!");
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
}