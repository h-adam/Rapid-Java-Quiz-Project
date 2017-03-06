package com.gluonapplication.views;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class QuestionPresenter {

    @FXML
    public RadioButton RadA;
    public RadioButton RadB;
    public RadioButton RadC;
    public RadioButton RadD;
    public RadioButton RadE;
    public TextArea qText;
    public Button submit;
    public TextArea hintText;
    public Button hintButton;
    public Button back;
    public Button result;
    static ToggleGroup group = new ToggleGroup();
    String answer;
    String h;

    public void initialize() throws SQLException, InterruptedException {
        qText.setEditable(false);
        //RadB.wrapTextProperty().setValue(true);
        hintText.setVisible(false);
        hintText.setEditable(false);
        RadA.setToggleGroup(group);
        RadB.setToggleGroup(group);
        RadC.setToggleGroup(group);
        RadD.setToggleGroup(group);
        RadE.setToggleGroup(group);
        RadA.wrapTextProperty().setValue(true);
        RadB.wrapTextProperty().setValue(true);
        RadC.wrapTextProperty().setValue(true);
        RadD.wrapTextProperty().setValue(true);
        RadE.wrapTextProperty().setValue(true);
        
        RadA.setText(QuestionQuerry.atext);
        RadB.setText(QuestionQuerry.btext);
        RadC.setText(QuestionQuerry.ctext);
        RadD.setText(QuestionQuerry.dtext);
        RadE.setText(QuestionQuerry.etext);
        qText.setText(QuestionQuerry.qnum + ". " + QuestionQuerry.qtext);
        hintText.setText(QuestionQuerry.qhint);
        answer = QuestionQuerry.qans;
        h = QuestionQuerry.qhint;
        //Check if hint for question
        if (h.equals("")) {
            hintButton.setVisible(false);
        } else {
            hintButton.setDisable(true);
        }
        if (RadC.getText().equals("")) {
            RadC.setVisible(false);
            RadD.setVisible(false);
            RadE.setVisible(false);
        }
        if (RadD.getText().equals("")) {
            RadD.setVisible(false);
            RadE.setVisible(false);
        }
        if (RadE.getText().equals("")) {
            RadE.setVisible(false);
        }
        RadA.setText("a. " + RadA.getText());
        RadB.setText("b. " + RadB.getText());
        RadC.setText("c. " + RadC.getText());
        RadD.setText("d. " + RadD.getText());
        RadE.setText("e. " + RadE.getText());
    }

    public void hintAction(ActionEvent event) {
        hintText.clear();
        hintText.setText(h);
    }

    public void Submit(ActionEvent event) {
        hintButton.setDisable(false);
        hintText.setVisible(true);
        String userAnswer = "";
        if (RadA.isSelected()) {
            userAnswer = userAnswer + "a ";
        }
        if (RadB.isSelected()) {
            userAnswer = userAnswer + "b ";
        }
        if (RadC.isSelected()) {
            userAnswer = userAnswer + "c ";
        }
        if (RadD.isSelected()) {
            userAnswer = userAnswer + "d ";
        }
        if (RadE.isSelected()) {
            userAnswer = userAnswer + "e ";
        }
        hintText.clear();
        
        System.out.println(userAnswer.trim());
        System.out.println(answer.trim());
        if (userAnswer.trim().equals(answer.trim())) {
            hintText.setStyle("-fx-background-color: lawngreen");
            hintText.setText("Correct \n" + hintText.getText());

        } else {
            hintText.setStyle("-fx-background-color: #ffbbbb; -fx-border-color: #f00;");
            hintText.setText("Incorrect \n" + "The Correct Answer is " + answer + "\n" + hintText.getText());
        }

    }

    public void goback(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("QuestionSelection.fxml"));
        back.getScene().setRoot(pane);
    }
    public void piechart(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("Results.fxml"));
        result.getScene().setRoot(pane);
    }
}
