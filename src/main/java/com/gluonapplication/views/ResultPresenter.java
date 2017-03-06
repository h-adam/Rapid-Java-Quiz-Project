package com.gluonapplication.views;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public final class ResultPresenter {

    @FXML
    public Button button1;
    public Button button2;
    public Button back;
    public Button buttonclear;
    public PieChart piechart;
    public Statement pstmt;
    Connection connection;

    private final String ABCDE[] = new String[9];

    public ResultPresenter() throws SQLException {
        initializeJdbc();
        retrieveTotals("1", "1", "1");
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connect to the sample database
            connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com/Questions", "students", "students");
            System.out.println("Connected!");
            pstmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("HOUSTON WE HAVE A PROBLEM!");
            System.out.println(ex);
        }
    }

    @FXML
    private void handleButton1Action(ActionEvent event) throws SQLException {
        int total = (int) ((Double.parseDouble(ABCDE[3])) + (Double.parseDouble(ABCDE[4]))
                + (Double.parseDouble(ABCDE[5])) + (Double.parseDouble(ABCDE[6]))
                + (Double.parseDouble(ABCDE[7])));
        double aPercent = (100) * (Double.parseDouble(ABCDE[3]) / total);
        double bPercent = (100) * (Double.parseDouble(ABCDE[4]) / total);
        double cPercent = (100) * (Double.parseDouble(ABCDE[5]) / total);
        double dPercent = (100) * (Double.parseDouble(ABCDE[6]) / total);
        double ePercent = (100) * (Double.parseDouble(ABCDE[7]) / total);

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("A: " + (int)aPercent + "%", (Double.parseDouble(ABCDE[3]))),
                        new PieChart.Data("B: " + (int)bPercent + "%", (Double.parseDouble(ABCDE[4]))),
                        new PieChart.Data("C: " + (int)cPercent + "%", (Double.parseDouble(ABCDE[5]))),
                        new PieChart.Data("D: " + (int)dPercent + "%", (Double.parseDouble(ABCDE[6]))),
                        new PieChart.Data("E: " + (int)ePercent + "%", (Double.parseDouble(ABCDE[7]))));

        piechart.setTitle("Student Answer Record");
    }

    @FXML
    private void handleButton2Action(ActionEvent event) throws SQLException {
        double total = (Double.parseDouble(ABCDE[3])) + (Double.parseDouble(ABCDE[4]))
                + (Double.parseDouble(ABCDE[5])) + (Double.parseDouble(ABCDE[6]))
                + (Double.parseDouble(ABCDE[7]));
        double aPercent = (100) * (Double.parseDouble(ABCDE[3]) / total);
        double bPercent = (100) * (Double.parseDouble(ABCDE[4]) / total);
        double cPercent = (100) * (Double.parseDouble(ABCDE[5]) / total);
        double dPercent = (100) * (Double.parseDouble(ABCDE[6]) / total);
        double ePercent = (100) * (Double.parseDouble(ABCDE[7]) / total);

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("A: " + (int)aPercent + "%", (Double.parseDouble(ABCDE[3]))),
                        new PieChart.Data("B: " + (int)bPercent + "%", (Double.parseDouble(ABCDE[4]))),
                        new PieChart.Data("C: " + (int)cPercent + "%", (Double.parseDouble(ABCDE[5]))),
                        new PieChart.Data("D: " + (int)dPercent + "%", (Double.parseDouble(ABCDE[6]))),
                        new PieChart.Data("E: " + (int)ePercent + "%", (Double.parseDouble(ABCDE[7]))));

        piechart.setTitle("All Answer Record");
        piechart.setData(pieChartData);
    }

    @FXML
    private void handleButtonClearAction(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
    }

    public void retrieveTotals(String chapter, String section, String question) throws SQLException {
        try {
            String queryString = "select * from Questions.Totals where chapter = '"
                    + ChapterSelectionPresenter.chapterSelected + "' AND section = '" + SectionsPresenter.sectionSelected
                    + "' AND question = '" + Question_SelectionPresenter.questionSelected + "'";
            ResultSet resultSet = pstmt.executeQuery(queryString);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            System.out.println();
            while (resultSet.next()) {
                for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
                    System.out.println(resultSet.getString(i + 1) + " index is " + i);
                    ABCDE[i] = resultSet.getString(i + 1);
                }

            }

            System.out.println(ABCDE[5] + " outside of resultSet");
        } catch (NullPointerException ex) {
            System.out.println(ABCDE[0] + ABCDE[2] + " first thing in here");
            System.out.println(ex);
        }
    }

    @FXML
    public void goBackToQuestions() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("QuestionSelection.fxml"));
        back.getScene().setRoot(pane);
    }
}
