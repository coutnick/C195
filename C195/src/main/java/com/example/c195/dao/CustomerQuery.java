package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The customerQuery class access the customer table in the database
 */
public abstract class CustomerQuery {
    /**
     * This query returns an observable list of all customers in the customers table of the database
     * @return ObservableList of customers
     * @throws SQLException
     */
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

    /**
     * This query adds a single customer to the customer table in the database
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     * @throws SQLException
     */
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

    /**
     * This query updates a single customer in the customer table of the database
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     * @throws SQLException
     */
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

    /**
     * This query gets a count of the number of customers per country
     * @param divisionId
     * @return number of customers per country
     */

    public static int customerCountryCount(int divisionId) throws SQLException {
        Connection connection = JDBC.connection;

        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS country_count " +
                "From customers c JOIN  first_level_divisions d ON c.division_id = d.division_id WHERE d.country_id = " + divisionId);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        if(rs.next()) {
            count = rs.getInt("country_count");
        }
        return count;
    }


    /**
     * This query deletes a single customer from the customer table in the database
     * @param customerId
     */
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

