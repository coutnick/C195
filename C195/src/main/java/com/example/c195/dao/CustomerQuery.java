package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class CustomerQuery {
    public static ObservableList<Customers> getCustomerData() throws SQLException {
        Connection connection = JDBC.connection;
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                customersObservableList.add(new Customers(Integer.parseInt(rs.getString("Customer_ID")),
                        rs.getString("Customer_Name"), rs.getString("Address"),
                        rs.getString("Postal_Code"), rs.getString("Phone"),
                        rs.getObject("Create_Date", LocalDateTime.class),
                        rs.getString("Created_By"), rs.getObject("Last_Update", Timestamp.class),
                        rs.getString("Last_Updated_By"), Integer.parseInt(rs.getString("Division_ID"))  ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customersObservableList;
    }

    public static void addCustomer(String name, String address, String postalCode, String phone,
                                        LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                                        String lastUpdatedBy, int divisionId) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customers (Customer_Name, Address," +
                    "Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                    "Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, Timestamp.valueOf(createDate));
            ps.setString(6, createdBy);
            ps.setTimestamp(7, lastUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, divisionId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Added customer");
            } else {
                System.out.println("Adding customer failed!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateCustomer(int customerId, String customerName, String address, String postalCode, String phone,
                                      Timestamp lastUpdated, String lastUpdatedBy, int divisionId) throws SQLException {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = " + customerId);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, lastUpdated);
            ps.setString(6, lastUpdatedBy);
            ps.setInt(7, divisionId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Added customer");
            } else {
                System.out.println("Adding customer failed!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deleteCustomer(int customerId) {
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM customers WHERE Customer_ID = " + customerId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Customer Deleted");
            } else {
                System.out.println("There was an error deleting");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

