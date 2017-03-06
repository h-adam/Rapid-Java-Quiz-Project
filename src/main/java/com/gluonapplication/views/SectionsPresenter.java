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

public class SectionsPresenter {//implements Initializable {

    public static String sectionSelected;
    public static ObservableList<String> sec;
    @FXML
    public ListView sectionList;
    public Button back;
    
    public void initialize() throws SQLException {
        sec = FXCollections.observableArrayList();
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com:3306/Questions", "students", "students");
            System.out.println("Database connected");
            // Create a Statement
            System.out.println(ChapterSelectionPresenter.chapterSelected);
            PreparedStatement pstmt = connection.prepareStatement("select * from Questions where chapter = '" + ChapterSelectionPresenter.chapterSelected + "'ORDER BY LPAD(lower(section), 10,0) ASC;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String sectionTitle = rs.getString("sectiondes");
                String sectionNum = rs.getString("section");
                if (!sec.contains(sectionNum + ". " + sectionTitle)) {
                    sec.add(sectionNum + ". " + sectionTitle);
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        sectionList.setItems(sec);

    }

    public void touch(Event event) {
        String section = sectionList.getSelectionModel().getSelectedItems().get(0).toString();
        System.out.println(section);
        String[] splited = section.split(" ");
        sectionSelected = splited[0].substring(0, splited[0].length()-1);

        System.out.println(sectionSelected);
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("QuestionSelection.fxml"));
            sectionList.getScene().setRoot(pane);
        } catch (IOException ex) {
        }
    }

    public void goback(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("ChapterSelectionView.fxml"));
            back.getScene().setRoot(pane);
    }
}

