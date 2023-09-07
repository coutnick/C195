package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.ContactQuery;
import com.example.c195.dao.UserQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextArea descriptionTextArea;
    public TextField titleTf;
    public ComboBox<String> contactComboBox;
    public TextField locationTf;
    public TextField typeTf;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField userIdTf;
    public TextField customerIdTf;
    public Button backButton;
    public Button submitButton;
    public TextField startHourTf;
    public TextField startMinTf;
    public TextField endHourTf;
    public TextField endMinTf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> contactNames = ContactQuery.getContactNames();
            contactComboBox.setItems(contactNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("appointments.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void submitButtonClick(ActionEvent actionEvent) throws SQLException {
        String title = titleTf.getText();
        String description = descriptionTextArea.getText();
        String location = locationTf.getText();
        String type = typeTf.getText();
        LocalDate startDate = startDatePicker.getValue();
        int startHour = Integer.parseInt(startHourTf.getText());
        int startMin = Integer.parseInt(startMinTf.getText());
        LocalDateTime appointmentStartDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMin);
        LocalDate endDate = endDatePicker.getValue();
        int endHour = Integer.parseInt(endHourTf.getText());
        int endMin = Integer.parseInt(endMinTf.getText());
        LocalDateTime appointmentEndDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), startDate.getDayOfMonth(), endHour, endMin);
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = UserQuery.getUserName(LoginController.staticUserId);
        Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
        int customerId = Integer.parseInt(customerIdTf.getText());
        int userId = Integer.parseInt(userIdTf.getText());
        int contactId = ContactQuery.getContactId(contactComboBox.getValue());
        AppointmentQuery.addAppointment(title, description, location, type, appointmentStartDate, appointmentEndDate, createDate,
                                        createdBy, lastUpdated, createdBy, customerId, userId, contactId);
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("appointments.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
