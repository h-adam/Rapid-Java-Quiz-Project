//package com.gluonapplication.views;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author Mekel_000
// */
//public final class StoreUserResult {
//
//    private String Email;
//    private String Password;
//    private PreparedStatement pstmt;
//    private PreparedStatement update;
//
//    public StoreUserResult() {
//        initializeJdbc();
//    }
//
//    public StoreUserResult(String id, String chapter, String question, String a, String b, String c, String d, String e) {
//        try {
////            this.Email = mail;
////            this.Password = word;
//            storeUser(this.Email, this.Password);
//        } catch (SQLException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Failed to insert into database");
//        }
//    }
//    // Use a prepared statement to store a student into the database
//
//    /**
//     * Initialize database connection
//     */
//    private void initializeJdbc() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            // Connect to the sample database
//            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com/Users", "students", "students");
//            System.out.println("Connected!");
//            // Create a Statement
//            //pstmt = connection.prepareStatement("insert into CreateAccount(Email, Password) values ('Tippy', 'Turner')"); //this works bitch
//            pstmt = connection.prepareStatement("insert into CreateAccount(Email, Password) values (?,?)"); //this works bitch
//            System.out.println("ADDING TIMMY TURNER TO DATABASE");
//            //update = connection.prepareStatement("update CreateAccount set Email=?, Password=? where Email=?");
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("HOUSTON WE HAVE A PROBLEM!");
//            System.out.println(ex);
//        }
//    }
//
//    /**
//     * Store a student record to the database
//     * @param Email
//     * @param Password
//     * @throws java.sql.SQLException
//     */
//    public void storeUser(String Email, String Password) throws SQLException {
//
//        System.out.println("WE ARE INSIDE STORE USER!" + Email + " " + Password);
//        
//        pstmt.setString(1, Email);
//        System.out.println("2");
//        pstmt.setString(2, Password);
//        System.out.println("3!");
//        pstmt.executeUpdate();
//         System.out.println("WE HAVE INSERTED INTO DATABSE BRUH!");
//
//    }
//    public boolean isRegistered(String email, String password) throws SQLException {
//                String result[] = new String[3];
//        try {
//            String queryString = "select * from CreateAccount where Email = '" + email+"'";/* + "' AND Password = '" + password+"'"*/;
//
//            //returns object retrieved by sql statement
//            ResultSet resultSet = pstmt.executeQuery(queryString);
//            ResultSetMetaData rsMetaData = resultSet.getMetaData();
//            boolean flag = true;
//            while (flag) {
//                resultSet.next();
//                flag = false;
//                for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
//                    //System.out.println(resultSet.getString(i+1));
//                    result[i] = resultSet.getString(i+1);
//                    //System.out.println(result[i]);
//                }
//                System.out.println(result[1]);
//                
//            }
//        } catch (NullPointerException ex) {
//            System.out.println(ex);
//        }
//        if(result[1].equals(email)) {
//            if(result[2].equals(password)) {
//                System.out.println("Is a registered User go to next page!");
//                return true;
//            }
//        }
//        return false;
//    }
//    public void updateStaff(String Email, String Password) throws SQLException {
//
//        update.setString(1, Email);
//        update.setString(2, Password);
//        update.executeUpdate();
//    }
//
//    public PreparedStatement getSQLInsert() {
//        return this.pstmt;
//    }
//
//    public PreparedStatement getSQLUpdate() {
//        return this.update;
//    }
//
//}
