package com.gluonapplication.views;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class Question_SelectionPresenter {

    public static String questionSelected;

    @FXML
    public ListView qList;
    public Button back;
    
    public void initialize() throws SQLException {
        ObservableList<String> qst = FXCollections.observableArrayList();
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com:3306/Questions", "students", "students");
            System.out.println("Database connected");
            // Create a Statement
            System.out.println(SectionsPresenter.sectionSelected);
            PreparedStatement pstmt = connection.prepareStatement("select * from Questions.Questions where (chapter = '" + ChapterSelectionPresenter.chapterSelected + "') AND (section = '"
                    + SectionsPresenter.sectionSelected + "')");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String sectionTitle = rs.getString("sectiondes");
                String sectionNum = rs.getString("section");
                String questionNum = rs.getString("questionnum");
                if (!qst.contains(sectionNum + ". " + sectionTitle + ": \n\t\t Question: " + questionNum)) {
                    qst.add(sectionNum + ". " + sectionTitle + ": \n\t\t Question: " + questionNum);
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        qList.setItems(qst);
    }

    public void touch(Event event) throws SQLException, InterruptedException {
        try {
            questionSelected = Integer.toString(qList.getSelectionModel().getSelectedIndex() + 1);
            QuestionQuerry.querry();
            
            if (QuestionQuerry.qans.trim().length() == 1) {              
                Parent pane = FXMLLoader.load(getClass().getResource("Question.fxml"));
                qList.getScene().setRoot(pane);
            }else{
                Parent pane = FXMLLoader.load(getClass().getResource("Question2.fxml"));
                qList.getScene().setRoot(pane);
            }          
        } catch (IOException ex) {
        }
    }

    public void goback(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("Sections.fxml"));
            back.getScene().setRoot(pane);
    }
}
