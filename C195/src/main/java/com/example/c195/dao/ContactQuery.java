package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactQuery {

    public static ObservableList<String> getContactNames() throws SQLException {
        ObservableList<String> contactNamesObservableList = FXCollections.observableArrayList();

        try{
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("SELECT Contact_Name FROM contacts");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                contactNamesObservableList.add(rs.getString("Contact_Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactNamesObservableList;
    }

    public static String getContactName(int contactId) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Contact_Name FROM contacts WHERE Contact_Id = "
                    + contactId  );
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("Contact_Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getContactId(String contactName) throws SQLException {
        try {
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name = \"" + contactName + "\"");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("Contact_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
