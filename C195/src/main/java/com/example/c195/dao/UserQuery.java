package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.helper.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {



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




    public static String getUserName(int id) throws SQLException {
        Connection connection = JDBC.connection;
        PreparedStatement ps = connection.prepareStatement("SELECT User_Name From users WHERE User_ID =" + id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            return rs.getString("User_Name");
        }
        return "";
    }

    public static ObservableList<String> getCustomerNames() throws SQLException {
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
