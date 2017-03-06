package com.gluonapplication.views;

//import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;

public class Start_PagePresenter {

    @FXML
    public Button LogInStart;
    public Button CrtAcc;
    

    

    @FXML
    private void LogInClick(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        LogInStart.getScene().setRoot(pane);
    }
    @FXML
    private void createAcc(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("CreatAccount.fxml"));
        CrtAcc.getScene().setRoot(pane);
    }
}
