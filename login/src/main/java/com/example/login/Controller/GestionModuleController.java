package com.example.login.Controller;

import com.example.login.DatabaseConnection;
import com.example.login.LoadInt;
import com.example.login.Model.entité.Module;
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

public class GestionModuleController implements Initializable {
    public AnchorPane gestionModulePane;
    public BorderPane gestionModuleBorder;
    public TableColumn<Module, Long> idModuleColumn;
    public TableColumn<Module, String> designationModuleColumn;
    public TableColumn<Module, Long> semesterIdColumn;
    public TableView<Module> moduleTableView;

    public Button returnBtn;
    private static Module module ;


    private com.example.login.LoadInt LoadInt;
    private DatabaseConnection databaseConnection;

    private String path;

    public GestionModuleController() {
        path = "/com/example/login/Module/";
        LoadInt = new LoadInt();
        databaseConnection = new DatabaseConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindPropertiesToColumns();
        moduleTableView.setItems(getRows());
    }

    public void bindPropertiesToColumns() {
        idModuleColumn.setCellValueFactory(new PropertyValueFactory<>("idModule"));
        designationModuleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        semesterIdColumn.setCellValueFactory(new PropertyValueFactory<>("semesterID"));
    }

    public ObservableList<Module> getRows() {
        ObservableList<Module> observableListModule = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM module";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Module module = new Module.Builder()
                        .withIdModule(resultSet.getLong("idModule"))
                        .withCode(resultSet.getString("code"))
                        .withSemesterID(resultSet.getLong("semesterID"))
                        .build();

                observableListModule.add(module);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return observableListModule;
    }


    public void modifierModule(MouseEvent mouseEvent) {
        System.out.println("modifier Module");
        module = moduleTableView.getSelectionModel().getSelectedItem();
        gestionModuleBorder.setCenter(LoadInt.loadInt(path + "ModifierModule.fxml"));
    }

    public void supprimerModule(MouseEvent mouseEvent) {
    }

    public void ajouterModule(MouseEvent mouseEvent) {
        System.out.println("ajouter Module");
        gestionModuleBorder.setCenter(LoadInt.loadInt(path + "AjouterModule.fxml"));
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
