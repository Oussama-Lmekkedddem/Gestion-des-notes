package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.LoadInt;
import com.example.login.Model.entité.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestionElementController implements Initializable {
    public AnchorPane gestionElementPane;
    public BorderPane gestionElementBorder;
    public TableView<Element> elementTableView;
    public TableColumn<Object, Object> idElementColumn;
    public TableColumn<Object, Object> designationElementColumn;
    public TableColumn<Object, Object> coefficientElementColumn;
    public TableColumn dsActiveColumn;
    public TableColumn examActiveColumn;
    public TableColumn tpActiveColumn;
    public TableColumn projetActiveColumn;
    public TableColumn exposeActiveColumn;
    public TableColumn devoirLibreActiveColumn;
    public TableColumn professeurIdColumn;
    public TableColumn moduleIdColumn;
    public TableColumn coefficientAbsenceColumn;
    public TableColumn coefficientDevoirLibreColumn;
    public TableColumn coefficientExposeColumn;
    public TableColumn coefficientProjetColumn;
    public TableColumn coefficientTpColumn;
    public TableColumn coefficientExamColumn;
    public TableColumn absenceActiveColumn;
    public TableColumn coefficientDsColumn;

    public Button returnBtn;

    private static Element element ;


    private com.example.login.LoadInt LoadInt;
    private DatabaseConnection databaseConnection;

    private String path;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindPropertiesToColumns();
        elementTableView.setItems(getRows());
    }

    public void bindPropertiesToColumns() {
        idElementColumn.setCellValueFactory(new PropertyValueFactory<>("idMatiere"));
        designationElementColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        coefficientElementColumn.setCellValueFactory(new PropertyValueFactory<>("currentCoefficient"));
    }

    public ObservableList<Element> getRows() {
        ObservableList<Element> observableListElement = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM element";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Element element = new Element.ElementBuilder()
                        .withIdMatiere(resultSet.getLong("idElement"))
                        .withCode(resultSet.getString("code"))
                        .withCurrentCoefficient(resultSet.getLong("coefficient"))
                        .build();

                observableListElement.add(element);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return observableListElement;
    }


    public GestionElementController() {
        path = "/com/example/login/Element/";
        LoadInt = new LoadInt();
        databaseConnection = new DatabaseConnection();
    }


    public void modifierElement(MouseEvent mouseEvent) {
        System.out.println("modifier Element");
        element = elementTableView.getSelectionModel().getSelectedItem();
        gestionElementBorder.setCenter(LoadInt.loadInt(path + "ModifierElement.fxml"));
    }

    public void ajouterElement(MouseEvent mouseEvent) {
        System.out.println("ajouter Element");
        gestionElementBorder.setCenter(LoadInt.loadInt(path + "AjouterElement.fxml"));
    }

    public void supprimerElement(MouseEvent mouseEvent) {
    }

    public void returne() throws IOException {
        returnBtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/login/Homepage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
