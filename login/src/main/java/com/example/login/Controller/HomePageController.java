package com.example.login.Controller;

import com.example.login.LoadInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class HomePageController {
    public HomePageController() {

        path = "";
        loadInt = new LoadInt();
    }
    private LoadInt loadInt;
    private String path;
    public AnchorPane administrateurDashboard;
    public Button administrateurBtn;
    public Button gestionFiliereBtn;
    public Button gestionDepartementBtn;
    public Button gestionEtudiantBtn;
    public Button gestionClasseBtn;
    public Button gestionElementBtn;
    public Button gestionProfesseurBtn;
    public Button gestionModuleBtn;
    public Button gestionUtilisateurBtn;
    public BorderPane borderPaneAdministrateur;





    public void gestionProfesseur(ActionEvent actionEvent) {
        System.out.println("gestionProfesseur");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionProfesseur.fxml"));

    }

    public void gestionModule(ActionEvent actionEvent) {
        System.out.println("gestionModule");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionModule.fxml"));
    }

    public void gestionElement(ActionEvent actionEvent) {
        System.out.println("gestionElement");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionElement.fxml"));
    }

    public void gestionClasse(ActionEvent actionEvent) {
        System.out.println("gestionClasse");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionNiveau.fxml"));

    }

    public void gestionEtudiant(ActionEvent actionEvent) {
        System.out.println("gestionEtudiant");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionEtudiant.fxml"));
    }

    public void gestionDepartement(ActionEvent actionEvent) {
        System.out.println("GestionDepartement");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionDepartement.fxml"));
    }

    public void gestionFiliere(ActionEvent actionEvent) {
        System.out.println("gestionFiliere");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionFiliere.fxml"));
    }



    public void gestionNotes(ActionEvent actionEvent) {
        System.out.println("gestionNotes");
        borderPaneAdministrateur.setCenter(loadInt.loadInt(path + "GestionNotes.fxml"));

    }

    public void administrateur(ActionEvent actionEvent) {
    }
}
