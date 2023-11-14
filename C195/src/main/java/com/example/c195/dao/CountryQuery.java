package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This countryQuery class is used to access the country table in the database
 */
public abstract class CountryQuery {
    /**
     * This query returns an observable list of strings containing all countries names from the country table in
     * the database
     * @return ObservableList of strings containing the name of all countries in the countries table
     * @throws SQLException
     */
    public static ObservableList<String> getCountryData() throws SQLException {
        ObservableList<String>  countryNameObservableList = FXCollections.observableArrayList();
        try {
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM countries");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                countryNameObservableList.add(rs.getString("Country"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return countryNameObservableList;
    }

    /**
     * This query gets the countryId corresponding to the name of the country passed
     * @param countryName
     * @return the countryId of the searched name
     * @throws SQLException
     */
    public static int getCountryId(String countryName) throws SQLException {
        try {
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("SELECT Country_ID FROM countries WHERE country = \"" + countryName + "\"") ;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString("Country_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * This query returns a countries name given a countryId
     * @param countryId
     * @return the countries name that corresponds with the passed countryId
     * @throws SQLException
     */
    public static String getCountry(int countryId) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Country FROM countries WHERE Country_ID = "
                    + countryId  );
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("Country");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";

    }
}
