package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The FirstLevelDivisionQuery class access the firstLevelDivision table in the database
 */
public abstract class FirstLevelDivisionQuery {
    /**
     * This query access and returns all first level divisions in the first level divisions table of the database
     * @param countryId
     * @return Observable list of strings containing all first level divisions in the database
     * @throws SQLException
     */
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

    /**
     * This query returns a division Id that corresponds with the divisions name
     * @param name
     * @return a divisionid that corresponds with the name passed
     * @throws SQLException
     */
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

    /**
     * This query gets a divisions name that corresponds to the passed divisionId
     * @param divisionId
     * @return a string the division name that corresponds to the passed divisionId
     * @throws SQLException
     */
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

    /**
     * Gets a countryId from the database that corresponds to the name of the division passed
     * @param division
     * @return a countryId that corresponds to the name passed
     * @throws SQLException
     */
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
