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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The add appointment Controller class is used for the add appointment view GUI
 */
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

    /**
     * Used to initialize the screen when the add appointment screen is started
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> contactNames = ContactQuery.getContactNames();
            contactComboBox.setItems(contactNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This action even takes a user back to the appointment page
     * @param actionEvent back button clicked
     */
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

    /**
     * This summitButtonClick action event adds an appointment to the appointment table. It checks to see if the appointment
     * is set outside business hours and converts the time to UTC. It then takes the user back to the appointments page.
     * @param actionEvent
     * @throws SQLException
     */
    public void submitButtonClick(ActionEvent actionEvent) throws SQLException {
        try {
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
            if (checkStartTime < 8 || checkStartTime >= 22 || checkEndTime < 8 || checkEndTime >= 22) {
                alert.setContentText("This appointment is outside of business hours. Please check the time and try again!");
                alert.showAndWait();
                return;
            }
            if (TimeStuff.isWeekend(TimeStuff.estFormattedTime(appointmentStartDate)) || TimeStuff.isWeekend(TimeStuff.estFormattedTime(appointmentEndDate))) {
                alert.setContentText("This appointment is outside of business hours. Please check the date and try again!");
                alert.showAndWait();
                return;
            }
            for (Appointments appointment : appointmentsObservableList) {
                System.out.println(appointment.getAppointmentId());
                if (TimeStuff.isOverlapping(TimeStuff.utcFormattedTime(appointmentStartDate), TimeStuff.utcFormattedTime(appointmentEndDate), appointment.getStart(), appointment.getEnd())) {
                    alert.setContentText("This appointment overlaps with another. Please check and try again!");
                    alert.showAndWait();
                    return;
                }
            }
            AppointmentQuery.addAppointment(title, description, location, type, convertedAppointmentStartDate, convertedAppointmentEndDate, convertedCreateDate,
                    createdBy, lastUpdated, createdBy, customerId, userId, contactId);
        } catch (NumberFormatException numberFormatException) {
            alert.setContentText("Please make sure that all fields are entered correctly");
            alert.showAndWait();
            return;
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            alert.setContentText("Please make sure that the customer and user ID exist and a contact is selected");
            alert.showAndWait();
            return;
        } catch (RuntimeException e) {
            alert.setContentText("Please make sure that the contact is selected");
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
            System.out.println(e);
             }
        }
    }
