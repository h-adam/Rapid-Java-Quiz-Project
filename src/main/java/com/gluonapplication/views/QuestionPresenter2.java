package com.gluonapplication.views;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class QuestionPresenter2 {

    @FXML
    public CheckBox CheckA;
    public CheckBox CheckB;
    public CheckBox CheckC;
    public CheckBox CheckD;
    public CheckBox CheckE;
    public TextArea qText;
    public Button submit;
    public TextArea hintText;
    public Button hintButton;
    public Button back;
    public Button result;
    static ToggleGroup group = new ToggleGroup();
    String answer;
    String h;

    @SuppressWarnings("empty-statement")
    public void initialize() throws SQLException {
        qText.setEditable(false);
        hintText.setVisible(false);
        hintText.setEditable(false);
        CheckA.wrapTextProperty().setValue(true);
        CheckB.wrapTextProperty().setValue(true);
        CheckC.wrapTextProperty().setValue(true);
        CheckD.wrapTextProperty().setValue(true);
        CheckE.wrapTextProperty().setValue(true);
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com:3306/Questions", "students", "students");
            System.out.println("Database connected");
            // Create a Statement
            System.out.println(Question_SelectionPresenter.questionSelected);
            PreparedStatement pstmt = connection.prepareStatement("select * from Questions.Questions where chapter = '"
                    + ChapterSelectionPresenter.chapterSelected + "' AND section = '" + SectionsPresenter.sectionSelected
                    + "' AND questionnum = '" + Question_SelectionPresenter.questionSelected + "'");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CheckA.setText(QuestionQuerry.atext);
                CheckB.setText(QuestionQuerry.btext);
                CheckC.setText(QuestionQuerry.ctext);
                CheckD.setText(QuestionQuerry.dtext);
                CheckE.setText(QuestionQuerry.etext);
                qText.setText(QuestionQuerry.qnum + ". " + QuestionQuerry.qtext);
                hintText.setText(QuestionQuerry.qhint);
                answer = QuestionQuerry.qans;
                h = QuestionQuerry.qhint;

            };
            if (h.equals("")) {
                hintButton.setVisible(false);
            } else {
                hintButton.setDisable(true);
            }

            if (CheckC.getText().equals("")) {
                CheckC.setVisible(false);
                CheckD.setVisible(false);
                CheckE.setVisible(false);
            }
            if (CheckD.getText().equals("")) {
                CheckD.setVisible(false);
                CheckE.setVisible(false);
            }
            if (CheckE.getText().equals("")) {
                CheckE.setVisible(false);
            }

            CheckA.setText("A. " + CheckA.getText());
            CheckB.setText("B. " + CheckB.getText());
            CheckC.setText("C. " + CheckC.getText());
            CheckD.setText("D. " + CheckD.getText());
            CheckE.setText("E. " + CheckE.getText());

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void hintAction(ActionEvent event) {
        hintText.clear();
        hintText.setText(h);
    }

    public void Submit(ActionEvent event) {
        hintButton.setDisable(false);
        hintText.setVisible(true);
        String userAnswer = "";
        if (CheckA.isSelected()) {
            userAnswer = userAnswer + "a ";
        }
        if (CheckB.isSelected()) {
            userAnswer = userAnswer + "b ";
        }
        if (CheckC.isSelected()) {
            userAnswer = userAnswer + "c ";
        }
        if (CheckD.isSelected()) {
            userAnswer = userAnswer + "d ";
        }
        if (CheckE.isSelected()) {
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
