package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonapplication.GluonApplication;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

public class CreatAccountPresenter {
    @FXML
    public TextField EmailTxtFld;
    public PasswordField CreatePassTxtFld;
    public Button back;
    public Button create;
    public Label error;
    
    public void initialize() {
    }

    @FXML
    private void register(ActionEvent event) throws SQLException, IOException {
        System.out.println("click");
        System.out.println("Creating user account... ");
            User temporary = new User();
            boolean wasStored = temporary.storeUser(EmailTxtFld.getText(), CreatePassTxtFld.getText());
             if (EmailTxtFld.getText().equals("")){
                   error.setText("");
               error.setText("Please enter a valid email address!");
               error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
               
               }else if (CreatePassTxtFld.getText().equals("")){
                   error.setText("");
               error.setText("Please enter a Password!");
               error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
               }else{
            if (wasStored == true) {
               // MobileApplication.getInstance().switchView(GluonApplication.CHAPTER_VIEW);
                Parent pane = FXMLLoader.load(getClass().getResource("ChapterSelectionView.fxml"));
                create.getScene().setRoot(pane);
                MobileApplication.getInstance().showMessage("\t\tWelcome, " + EmailTxtFld.getText() + " you are now registered!"
                        + "\n \t\t\tHave fun learning Java!");
                System.out.print(" Is now registered in database "+EmailTxtFld.getText()+" " + CreatePassTxtFld.getText());
            } else if (wasStored == false) {
                //MobileApplication.getInstance().showMessage("Sorry that username already exists, choose another!");
                //MobileApplication.getInstance().switchView(GluonApplication.Third_VIEW);
              
                error.setText("");
               error.setText("Sorry that username already exists, choose another!");
               error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
               // Parent pane = FXMLLoader.load(getClass().getResource("CreatAccount.fxml"));
                //create.getScene().setRoot(pane);
            }
        } 
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("start_page.fxml"));
        back.getScene().setRoot(pane);
    }

}
