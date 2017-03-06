package com.gluonapplication.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionQuerry {

    public static String atext;
    public static String btext;
    public static String ctext;
    public static String dtext;
    public static String etext;
    public static String qtext;
    public static String qnum;
    public static String qhint;
    public static String qans;

    public static void querry() throws SQLException, InterruptedException {
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
                atext = (rs.getString("atext").trim());
                btext = (rs.getString("btext").trim());
                ctext = (rs.getString("ctext").trim());
                dtext = (rs.getString("dtext").trim());
                etext = (rs.getString("etext").trim());
                qtext = (rs.getString("questiontxt"));
                qnum = (rs.getString("questionnum"));
                qhint = (rs.getString("hint"));
                qans = rs.getString("keys");
            }        
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }

}
