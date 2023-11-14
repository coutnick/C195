package com.example.c195.dao;

import com.example.c195.helper.JDBC;
import com.example.c195.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;

public abstract class AppointmentQuery {

    public static ObservableList<Appointments> getAppointmentData() throws SQLException {

        Connection connection = JDBC.connection;
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * from appointments");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                appointmentsObservableList.add(new Appointments(Integer.parseInt(rs.getString("Appointment_ID")), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        rs.getObject("Start", LocalDateTime.class), rs.getObject("End", LocalDateTime.class), rs.getObject("Create_Date", LocalDateTime.class),
                        rs.getString("Created_By"), rs.getObject("Last_Update", Timestamp.class), rs.getString("Last_Updated_By"),
                        Integer.parseInt(rs.getString("Customer_ID")), Integer.parseInt(rs.getString("User_ID")),
                        Integer.parseInt(rs.getString("Contact_ID"))    )   );
            }
            System.out.println("List size is: " + appointmentsObservableList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentsObservableList;
    }

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start,
                                      LocalDateTime end, LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                                      String lastUpdatedBy, int customerId, int userId, int contactId) {
        try{
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("INSERT INTO appointments (Title, Description, Location," +
                    "Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_Id, Contact_ID )" +
                    "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(createDate));
            ps.setString(8, createdBy);
            ps.setTimestamp(9, lastUpdate);
            ps.setString(10, lastUpdatedBy);
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Added appointment");
            } else {
                System.out.println("Adding appointment failed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ObservableList<String> getAppointmentTypes() {
        ObservableList<String> types = FXCollections.observableArrayList();
        Connection connection = JDBC.connection;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Type FROM appointments;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String type = rs.getString("Type");
                types.add(type);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return types;
    }

    public static ObservableList<Appointments> getAppointmentsByType(String type) throws SQLException {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();
        try {
            Connection connection = JDBC.connection;

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointments WHERE Type = \"" + type + "\"");
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);

            while (rs.next()) {

                appointmentsObservableList.add(new Appointments(Integer.parseInt(rs.getString("Appointment_ID")), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        rs.getObject("Start", LocalDateTime.class), rs.getObject("End", LocalDateTime.class), rs.getObject("Create_Date", LocalDateTime.class),
                        rs.getString("Created_By"), rs.getObject("Last_Update", Timestamp.class), rs.getString("Last_Updated_By"),
                        Integer.parseInt(rs.getString("Customer_ID")), Integer.parseInt(rs.getString("User_ID")),
                        Integer.parseInt(rs.getString("Contact_ID"))));

            }
            System.out.println("Types list size = " + appointmentsObservableList.size());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return appointmentsObservableList;
    }

    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start,
                                         LocalDateTime end, Timestamp lastUpdate, String lastUpdatedBy, int customerId,
                                         int userId, int contactId) {
        try{
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("UPDATE appointments SET Title = ?, Description = ?, " +
                                                                "Location = ?, Type = ?, Start = ?, End = ?, " +
                                                                "Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = " + appointmentId);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, lastUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Updated appointment");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {
        try {
            Connection connection = JDBC.connection;
            PreparedStatement ps = connection.prepareStatement("DELETE FROM appointments WHERE Appointment_ID = " + appointmentId);
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0) {
                System.out.println("deleted appoinment");
            } else {
                System.out.println("Could not delete the appointment");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
