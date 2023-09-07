package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {

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
