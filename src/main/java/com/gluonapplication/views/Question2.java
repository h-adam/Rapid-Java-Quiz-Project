package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Question2 {

    private final String name;

    public Question2(String name) {
        this.name = name;
    }

    public View getView() {
        try {
            View view = FXMLLoader.load(LogIn.class.getResource("Question2.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
