package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class ChapterSelectionView {

    private final String name;

    public ChapterSelectionView(String name) {
        this.name = name;
    }

    public View getView() {
        try {
            View view = FXMLLoader.load(ChapterSelectionView.class.getResource("ChapterSelectionView.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
