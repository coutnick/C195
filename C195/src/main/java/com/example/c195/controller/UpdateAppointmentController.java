package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.ContactQuery;
import com.example.c195.dao.UserQuery;
import com.example.c195.helper.TimeStuff;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This is the controller for updating an appointment
 */
public class UpdateAppointmentController implements Initializable {

    public TextField appointmentIdTf;
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
    public TextArea descriptionTextArea;
    public TextField startHourTf;
    public TextField endHourTf;
    public TextField startMinTf;
    public TextField endMinTf;

    Alert alert = new Alert(Alert.AlertType.WARNING);

    /**
     * Initializes the update appointment GUI and sets all the text fields
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Format startMinFormat = new SimpleDateFormat("mm");
            Format endMinFormat = new SimpleDateFormat("mm");
            String startMin = startMinFormat.format(AppointmentController.appointment.getStart().getMinute());
            String endMin = endMinFormat.format(AppointmentController.appointment.getEnd().getMinute());
            ObservableList<String> contactNames = ContactQuery.getContactNames();
            appointmentIdTf.setText(Integer.valueOf(AppointmentController.appointment.getAppointmentId()).toString());
            titleTf.setText(AppointmentController.appointment.getTitle());
            userIdTf.setText(Integer.valueOf(AppointmentController.appointment.getUserId()).toString());
            customerIdTf.setText(Integer.valueOf(AppointmentController.appointment.getCustomerId()).toString());
            contactComboBox.setItems(contactNames);
            typeTf.setText(AppointmentController.appointment.getType());
            locationTf.setText(AppointmentController.appointment.getLocation());
            startDatePicker.setValue(AppointmentController.appointment.getStart().toLocalDate());
            startHourTf.setText(Integer.valueOf(AppointmentController.appointment.getStart().getHour()).toString());
            startMinTf.setText(String.valueOf(AppointmentController.appointment.getStart().getMinute()));
            endDatePicker.setValue(AppointmentController.appointment.getEnd().toLocalDate());
            endHourTf.setText(Integer.valueOf(AppointmentController.appointment.getEnd().getHour()).toString());
            endMinTf.setText(String.valueOf(AppointmentController.appointment.getEnd().getMinute()));
            descriptionTextArea.setText(AppointmentController.appointment.getDescription());
            contactComboBox.setValue(ContactQuery.getContactName(AppointmentController.appointment.getContactId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When the back button is clicked the user is sent back to the appointments page
     * @param actionEvent back button is clicked
     */
    public void backButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("appointments.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    /**
     * Updates an appointment when the submit button is clicked. Changes the time to UTC and makes sure there are no appointments set
     * outside of business ours or overlapping with the updated time.
     * @param actionEvent submit button clicked
     * @throws SQLException
     */
    public void submitButtonClick(ActionEvent actionEvent) throws SQLException {
        try {
            int appointmentId = Integer.parseInt(appointmentIdTf.getText());
            String title = titleTf.getText();
            String description = descriptionTextArea.getText();
            String location = locationTf.getText();
            String type = typeTf.getText();
            LocalDate startDate = startDatePicker.getValue();
            int startHour = Integer.parseInt(startHourTf.getText());
            int startMin = Integer.parseInt(startMinTf.getText());
            LocalDateTime appointmentStartDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMin);
            LocalDateTime convertedStartDate = TimeStuff.utcFormattedTime(appointmentStartDate);
            LocalDate endDate = endDatePicker.getValue();
            int endHour = Integer.parseInt(endHourTf.getText());
            int endMin = Integer.parseInt(endMinTf.getText());
            LocalDateTime appointmentEndDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), startDate.getDayOfMonth(), endHour, endMin);
            LocalDateTime convertedEndDate = TimeStuff.utcFormattedTime(appointmentEndDate);
            String updatedBy = UserQuery.getUserName(LoginController.staticUserId);
            Timestamp lastUpdated = Timestamp.valueOf(TimeStuff.utcFormattedTime(LocalDateTime.now()));
            int customerId = Integer.parseInt(customerIdTf.getText());
            int userId = Integer.parseInt(userIdTf.getText());
            int contactId = ContactQuery.getContactId(contactComboBox.getValue());
            if (endHour < 8 || endHour >= 22 || startHour < 8 || startHour >= 22) {
                alert.setContentText("This appointment is outside of business hours. Please check the time and try again!");
                alert.showAndWait();
                return;
            }
            if (TimeStuff.isWeekend(appointmentStartDate) || TimeStuff.isWeekend(appointmentEndDate)) {
                alert.setContentText("This appointment is outside of business hours. Please check the date and try again!");
                alert.showAndWait();
                return;
            }
            AppointmentQuery.updateAppointment(appointmentId, title, description, location, type, convertedStartDate, convertedEndDate, lastUpdated, updatedBy, customerId, userId, contactId);
        } catch (NumberFormatException numberFormatException) {
            alert.setContentText("Please make sure that all fields are entered correctly");
            alert.showAndWait();
            return;
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            alert.setContentText("Please make sure that the customer and user ID exist and a contact is selected");
            alert.showAndWait();
            return;
        } catch (RuntimeException e) {
            alert.setContentText("Please make sure that the contact is selected and that the customer and user ID exist.");
            alert.showAndWait();
            return;
        }

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("appointments.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }



}
