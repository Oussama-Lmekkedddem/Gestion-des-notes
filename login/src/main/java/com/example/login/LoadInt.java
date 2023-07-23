package com.example.login;



import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoadInt {

    public Parent loadInt(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
