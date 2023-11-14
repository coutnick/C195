package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class FirstLevelDivisionQuery {
    public static ObservableList<String> getFirstLevelDivisionData(int countryId) throws SQLException {
        Connection connection = JDBC.connection;
        ObservableList<String> firstLevelDivisionsObservableList = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                firstLevelDivisionsObservableList.add(rs.getString("Division"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstLevelDivisionsObservableList;
    }

    public static int getDivisionId(String name) throws SQLException {
        Connection connection = JDBC.connection;

        try {
            PreparedStatement ps = connection.prepareStatement("select Division_ID From first_level_divisions WHERE Division = \"" + name +"\"");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return Integer.parseInt(rs.getString("Division_ID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 1;
    }

    public static String getDivision(int divisionId) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Division_ID = " + divisionId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getString("Division");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getCountryId(String division) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Country_ID FROM first_level_divisions WHERE Division = \"" + division + "\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return Integer.parseInt(rs.getString("Country_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
