package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.helper.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This UserQuery class is used to access the user table in the database
 */
public abstract class UserQuery {

    /**
     * This query validates that a user is logging in and has correct credentials
     * @param userName valid user name
     * @param password valid password
     * @return a user id if valid. -1 if not valid
     * @throws SQLException
     */

    public static int validateLogin(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String un = rs.getString("User_Name");
            String pass = rs.getString("Password");
            if (un.equals(userName)) {
                if (pass.equals(password)) {
                    Log.accessLog(userName, 1);
                    return rs.getInt("User_ID");
                }
            }
        }
        Log.accessLog(userName, -1);
        return -1;
    }


    /**
     * This query gets a username from the passed userId
     * @param id
     * @return username that corresponds with the id given
     * @throws SQLException
     */
    public static String getUserName(int id) throws SQLException {
        Connection connection = JDBC.connection;
        PreparedStatement ps = connection.prepareStatement("SELECT User_Name From users WHERE User_ID =" + id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            return rs.getString("User_Name");
        }
        return "";
    }

    /**
     * This query returns an observablelist of all usernames in the user table of the database
     * @return ObservableList of strings
     * @throws SQLException
     */
    public static ObservableList<String> getUserNames() throws SQLException {
        Connection connection = JDBC.connection;
        ObservableList<String> customerNameObservableList = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT User_Name from users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String user = rs.getString("User_Name");
                customerNameObservableList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return customerNameObservableList;
    }

}
