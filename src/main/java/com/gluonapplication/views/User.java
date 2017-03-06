package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mekel_000
 */
public final class User {

    private String Email;
    private String Password;
    private PreparedStatement pstmt;
    private PreparedStatement update;

    public User() {
        initializeJdbc();
    }

    public User(String mail, String word) {
        try {
            this.Email = mail;
            this.Password = word;
            storeUser(this.Email, this.Password);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to insert into database");
        }
    }
    // Use a prepared statement to store a student into the database

    /**
     * Initialize database connection
     */
    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com/Users", "students", "students");
            //System.out.println("Connected!");
            pstmt = connection.prepareStatement("insert into CreateAccount(Email, Password) values (?,?)"); //this works bitch
            update = connection.prepareStatement("update CreateAccount set Email=?, Password=? where Email=?");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Store a student record to the database
     *
     * @param Email
     * @param Password
     * @return
     * @throws java.sql.SQLException
     */
    public boolean storeUser(String Email, String Password) throws SQLException {

        //System.out.println("Have we registered "+ Email +" already? " + isRegistered(Email, Password));
        if (isRegistered(Email, Password) == false) {
            pstmt.setString(1, Email);
            pstmt.setString(2, Password);
            pstmt.executeUpdate();
            System.out.println("WE HAVE INSERTED INTO DATABSE BRUH!");
            return true;
        } else if (isRegistered(Email, Password) == true) {
            System.out.println("Store user failed!");
        }
        return false;
    }

    public boolean isRegistered(String email, String password) throws SQLException {
        String result[] = new String[3];
        try {
            String queryString = "select * from CreateAccount where Email = '" + email + "'";

            //returns object retrieved by sql statement
            ResultSet resultSet = pstmt.executeQuery(queryString);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
                    //System.out.println(resultSet.getString(i+1));
                    result[i] = resultSet.getString(i + 1);
                    //System.out.println(result[i]);
                }
                //System.out.println(result[1]);

            }
            if (result[1].equals(email) || (result[1].equals(email) && result[2].equals(password))) {
                System.out.println("Username already in database");

                return true;
            }

        } catch (NullPointerException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public void updateStaff(String Email, String Password) throws SQLException {

        update.setString(1, Email);
        update.setString(2, Password);
        update.executeUpdate();
    }

    public PreparedStatement getSQLInsert() {
        return this.pstmt;
    }

    public PreparedStatement getSQLUpdate() {
        return this.update;
    }

}
