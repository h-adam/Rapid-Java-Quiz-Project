package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Start_PageView {

    private final String name;

    public Start_PageView(String name) {
        this.name = name;
    }

    public View getView() {
        try {
            View view = FXMLLoader.load(Start_PageView.class.getResource("start_page.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }

}
