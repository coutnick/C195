package com.example.c195.dao;

import com.example.c195.helper.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {



    public static int validateLogin(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String un = rs.getString("User_Name");
            String pass = rs.getString("Password");
            if(un.equals(userName)) {
                if(pass.equals(password)) {
                    return rs.getInt("User_ID");
                } else {
                    System.out.println("Password is incorrect");
                }
            }
        }

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



}
