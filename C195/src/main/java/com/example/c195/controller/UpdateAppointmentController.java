package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.ContactQuery;
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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;

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
            startMinTf.setText(startMin);
            endDatePicker.setValue(AppointmentController.appointment.getEnd().toLocalDate());
            endHourTf.setText(Integer.valueOf(AppointmentController.appointment.getEnd().getHour()).toString());
            endMinTf.setText(endMin);
            descriptionTextArea.setText(AppointmentController.appointment.getDescription());
            contactComboBox.setValue(ContactQuery.getContactName(AppointmentController.appointment.getContactId()));
        } catch (Exception e) {
            e.printStackTrace();
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

    public void submitButtonClick(ActionEvent actionEvent) {
    }


}
