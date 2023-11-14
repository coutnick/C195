package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.ContactQuery;
import com.example.c195.dao.UserQuery;
import com.example.c195.helper.TimeStuff;
import com.example.c195.model.Appointments;
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
import java.time.*;
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

    Alert alert = new Alert(Alert.AlertType.WARNING);

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
        ObservableList<Appointments> appointmentsObservableList = AppointmentQuery.getAppointmentData();
        String title = titleTf.getText();
        String description = descriptionTextArea.getText();
        String location = locationTf.getText();
        String type = typeTf.getText();
        LocalDate startDate = startDatePicker.getValue();
        int startHour = Integer.parseInt(startHourTf.getText());
        int startMin = Integer.parseInt(startMinTf.getText());
        LocalDateTime appointmentStartDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMin);
        LocalDateTime convertedAppointmentStartDate = TimeStuff.utcFormattedTime(appointmentStartDate);
        LocalDate endDate = endDatePicker.getValue();
        int endHour = Integer.parseInt(endHourTf.getText());
        int endMin = Integer.parseInt(endMinTf.getText());
        LocalDateTime appointmentEndDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), startDate.getDayOfMonth(), endHour, endMin);
        LocalDateTime convertedAppointmentEndDate = TimeStuff.utcFormattedTime(appointmentEndDate);
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime convertedCreateDate = TimeStuff.utcFormattedTime(createDate);
        String createdBy = UserQuery.getUserName(LoginController.staticUserId);
        Timestamp lastUpdated = Timestamp.valueOf(TimeStuff.utcFormattedTime(LocalDateTime.now()));
        int customerId = Integer.parseInt(customerIdTf.getText());
        int userId = Integer.parseInt(userIdTf.getText());
        int contactId = ContactQuery.getContactId(contactComboBox.getValue());
        int checkStartTime = TimeStuff.estFormattedTime(appointmentStartDate).getHour();
        int checkEndTime = TimeStuff.estFormattedTime(appointmentEndDate).getHour();
        if(checkStartTime < 8 || checkStartTime >= 22 || checkEndTime < 8 || checkEndTime >= 22) {
            alert.setContentText("This appointment is outside of business hours. Please check the time and try again!");
            alert.showAndWait();
            return;
        }
        if(TimeStuff.isWeekend(TimeStuff.estFormattedTime(appointmentStartDate)) || TimeStuff.isWeekend(TimeStuff.estFormattedTime(appointmentEndDate))) {
            alert.setContentText("This appointment is outside of business hours. Please check the date and try again!");
            alert.showAndWait();
            return;
        }
        for(Appointments appointment : appointmentsObservableList) {
            System.out.println(appointment.getAppointmentId());
            if(TimeStuff.isOverlapping(TimeStuff.utcFormattedTime(appointmentStartDate), TimeStuff.utcFormattedTime(appointmentEndDate), appointment.getStart(), appointment.getEnd())) {
                alert.setContentText("This appointment overlaps with another. Please check and try again!");
                alert.showAndWait();
                return;
            }
        }
        AppointmentQuery.addAppointment(title, description, location, type, convertedAppointmentStartDate, convertedAppointmentEndDate, convertedCreateDate,
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
