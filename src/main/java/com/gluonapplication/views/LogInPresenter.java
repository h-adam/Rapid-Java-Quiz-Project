package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonapplication.GluonApplication;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInPresenter {

    @FXML
    public TextField UserNameTxtFld;
    public PasswordField PassTxtFld;
    public Button back;
    public Button LogIn;
    public Label error;

    public void initialize() {
        UserNameTxtFld.setText("");
        PassTxtFld.setText("");

    }

    @FXML
    private void goToChap(ActionEvent event) throws IOException {
        if (UserNameTxtFld.getText().equals("") | UserNameTxtFld.getText() == null) {
            error.setText("");
            error.setText("Please enter a Username!");
            error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
            // Parent pane = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            //LogIn.getScene().setRoot(pane);
            //System.out.println("fdsafs");
            //GluonApplication.getInstance().showMessage("Please enter a Username!");
        } else if (PassTxtFld.getText().equals("") | PassTxtFld.getText() == null) {
            //Parent pane = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            //LogIn.getScene().setRoot(pane);
            error.setText("");
            error.setText("Please enter a Password!");
            error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
            MobileApplication.getInstance().showMessage("Please enter a Password!");
        } else if (checkUserCredentials() == true) {
            System.out.println("THIS USER EXISTS IN DATABASE!");
            Parent pane = FXMLLoader.load(getClass().getResource("ChapterSelectionView.fxml"));
            LogIn.getScene().setRoot(pane);
            //MobileApplication.getInstance().showMessage(UserNameTxtFld.getText() + ", welcome back! Have fun learning Java!");
        } else {
            error.setText("");
            error.setText("Sorry that account does not exist!");
            error.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
            MobileApplication.getInstance().showMessage("\t\t\t Sorry that account does not exist! \n \t\t\t Try creating an account!");
        }
        //System.out.println("THIS FAILED!");
    }

    @FXML
    private boolean checkUserCredentials() {
        try {
            return new User().isRegistered(UserNameTxtFld.getText(), PassTxtFld.getText());

        } catch (SQLException ex) {
            Logger.getLogger(LogInPresenter.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @FXML
    private void goBack() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("start_page.fxml"));
        back.getScene().setRoot(pane);
    }

}

/*
package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.GluonApplication;
//import com.gluonhq.charm.glisten.control.TextField;
import com.gluonapplication.GetMap;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInPresenter {
    //public GetMap hi = new GetMap();
    //@FXML
    //private View secondary;

    @FXML
    public TextField UserNameTxtFld;
    public PasswordField PassTxtFld;
    public Button goBack;

    public void initialize() {
    }

    @FXML
    private void goToChap(ActionEvent event) {
        if (UserNameTxtFld.getText().equals("") | UserNameTxtFld.getText() == null) {
            MobileApplication.getInstance().switchView(GluonApplication.SECONDARY_VIEW);
            MobileApplication.getInstance().showMessage("Please enter a Username!");
        }
        if (PassTxtFld.getText().equals("") | PassTxtFld.getText() == null) {
            MobileApplication.getInstance().switchView(GluonApplication.SECONDARY_VIEW);
            MobileApplication.getInstance().showMessage("Please enter a Password!");
        }
        if (checkUserCredentials() == true) {
            System.out.println("THIS USER EXISTS IN DATABASE!");
            MobileApplication.getInstance().switchView(GluonApplication.CHAPTER_VIEW);

            MobileApplication.getInstance().showMessage(UserNameTxtFld.getText() + ", welcome back! Have fun learning Java!");
        } else {
            MobileApplication.getInstance().showMessage("\t\t\t Sorry that account does not exist! \n \t\t\t Try creating an account!");
        }
        System.out.println("THIS FAILED!");
    }

    @FXML
    private boolean checkUserCredentials() {
        try {
            return new User().isRegistered(UserNameTxtFld.getText(), PassTxtFld.getText());

        } catch (SQLException ex) {
            Logger.getLogger(LogInPresenter.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @FXML
    private void goBack() {
        MobileApplication.getInstance().switchView(GluonApplication.START_PAGE_VIEW);
    }

}

 */
