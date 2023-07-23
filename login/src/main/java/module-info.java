module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example.login to javafx.fxml;
    exports com.example.login;
    exports com.example.login.Controller;
    exports com.example.login.Controller.Departement;
    opens com.example.login.Controller to javafx.fxml;
    exports com.example.login.Controller.Filiere;
    opens com.example.login.Controller.Filiere to javafx.fxml;
    exports com.example.login.Model.entité;
    opens com.example.login.Model.entité to javafx.fxml;
    opens com.example.login.Controller.Departement to javafx.fxml;
    opens com.example.login.Controller.Niveau to javafx.fxml;
    opens com.example.login.Controller.Element to javafx.fxml;



    exports com.example.login.Controller.Niveau;
    opens com.example.login.Controller.Module to javafx.fxml;
    exports com.example.login.Controller.Proffeseur;
    opens com.example.login.Controller.Proffeseur;



}



