package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;

public class Result extends AnchorPane {

    public String Name;
    public Result() {
    }

    public Result(String Name) {
        this.Name = Name;
    }
    
    public View getView() {
        try {
            View view = FXMLLoader.load(LogIn.class.getResource("Results.fxml"));
            view.setName(Name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(Name);
        }
    }
}
