package com.example.login.Controller.Module;

import com.example.login.DatabaseConnection;
import com.example.login.Model.entité.Niveau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AjouterModuleController {
    public TextField designationElementField;
    public Label designationMsg;
    public ComboBox classeList;
    public Label classeMsg;
    public ComboBox moduleList;
    public Label moduleMsg;
    public ComboBox professeurList;
    public Label professeurMsg;
    public Button ajouterElementBtn;
    public Label coefficientMsg;
    public TextField coefficientElementField;
    public TextField designationModuleField;
    public Label desigationModuleMsg;
    public ComboBox semestreList;
    public Button ajouterModuleBtn;

    public void moduleAction(ActionEvent actionEvent) {
    }

    public void classeAction(ActionEvent actionEvent) {
    }


    public void ajouterModule(ActionEvent actionEvent) {
    }
    @FXML
    void initialize() {
        populateClaseComboBox();
    }
    private void populateClaseComboBox() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM niveau");

            List<Niveau> niveaux = new ArrayList<>();
            while (resultSet.next()) {
                int iDniveau = resultSet.getInt("IDniveau");
                String nomniveau = resultSet.getString("nomniveau");

                Niveau niveau = new Niveau.NiveauBuilder()
                        .withIdNiveau(iDniveau)
                        .withTitre(nomniveau)
                        .build();

                niveaux.add(niveau);
            }

            classeList.getItems().addAll(niveaux);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
