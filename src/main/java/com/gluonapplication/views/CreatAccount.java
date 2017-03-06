package com.gluonapplication.views;

import com.gluonapplication.views.LogIn;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class CreatAccount {
@FXML private TextField EmailTxtFld;
@FXML private TextField CreatePassTxtFld;
    private String name;
    
    public CreatAccount() {
        
    }
    public CreatAccount(String name){
        this.name = name;
    }
    
    public View getView() {
        try {
            View view = FXMLLoader.load(LogIn.class.getResource("CreatAccount.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
