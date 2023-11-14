package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.helper.TimeStuff;
import com.example.c195.model.Appointments;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    public RadioButton viewByMonthRB;
    @FXML
    public RadioButton viewByWeekRB;
    @FXML
    public RadioButton viewAllRb;
    @FXML
    private TableView<Appointments> appointmentsTable;

    @FXML
    public TableColumn<Appointments, Integer> appointmentIdTc;

    @FXML
    public TableColumn<Appointments, String> titleTc;

    @FXML
    public TableColumn<Appointments, String> descriptionTc;

    @FXML
    public TableColumn<Appointments, String> locationTc;

    @FXML
    public TableColumn<Appointments, String> typeTc;

    @FXML
    public TableColumn<Appointments, LocalDateTime> startTc;

    @FXML
    public TableColumn<Appointments, LocalDateTime> endTc;

    @FXML
    public TableColumn<Appointments, LocalDateTime> createDateTc;

    @FXML
    public TableColumn<Appointments, String> createdByTc;

    @FXML
    public TableColumn<Appointments, Timestamp> lastUpdateTimeTc;

    @FXML
    public TableColumn<Appointments, String> lastUpdatedByTc;

    @FXML
    public TableColumn<Appointments, Integer> customerIdTc;

    @FXML
    public TableColumn<Appointments, Integer> userIdTc;

    @FXML
    public TableColumn<Appointments, Integer> contactIdTc;

    @FXML
    public Button updateButton;

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button backBut;

    public static Appointments appointment;

    private FilteredList<Appointments> filteredAppointments;

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Alert alertTwo = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {


            ObservableList<Appointments> appointmentsObservableList = AppointmentQuery.getAppointmentData();
            filteredAppointments = new FilteredList<>(appointmentsObservableList);
            ToggleGroup toggleGroup = new ToggleGroup();
            viewByWeekRB.setToggleGroup(toggleGroup);
            viewByMonthRB.setToggleGroup(toggleGroup);
            viewAllRb.setToggleGroup(toggleGroup);
            viewAllRb.setSelected(true);
            appointmentIdTc.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationTc.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeTc.setCellValueFactory(new PropertyValueFactory<>("type"));
            startTc.setCellValueFactory(cellData -> {
                LocalDateTime utcTime = cellData.getValue().getStart();
                return new SimpleObjectProperty<>(TimeStuff.localFormattedTime(utcTime));
            });
            endTc.setCellValueFactory(cellData -> {
                LocalDateTime endTimeUTC = cellData.getValue().getEnd();
                return new SimpleObjectProperty<>(TimeStuff.localFormattedTime(endTimeUTC));
            });
            createDateTc.setCellValueFactory(cellData -> {
                LocalDateTime createdUTC = cellData.getValue().getCreateDate();
                return new SimpleObjectProperty<>(TimeStuff.localFormattedTime(createdUTC));
            });

            createdByTc.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            lastUpdateTimeTc.setCellValueFactory(cellData -> {
                LocalDateTime lastUpdatedUTC = cellData.getValue().getLastUpdate().toLocalDateTime();
                return new SimpleObjectProperty<>(Timestamp.valueOf(TimeStuff.localFormattedTime(lastUpdatedUTC)));
            });
            lastUpdatedByTc.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
            customerIdTc.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            userIdTc.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdTc.setCellValueFactory(new PropertyValueFactory<>("contactId"));

            appointmentsTable.setItems(filteredAppointments);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("addAppointment.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void updateButtonClick(ActionEvent actionEvent) throws IOException {
        appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("updateAppointment.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }


    public void deleteButtonClick(ActionEvent actionEvent) throws SQLException {
        appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        alert.setContentText("Are you sure you want to delete this appointment");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    AppointmentQuery.deleteAppointment(appointment.getAppointmentId());
                    alertTwo.setContentText("Appointment:" + appointment.getAppointmentId() +
                            " Type: " + appointment.getType() + " has been deleted!");
                    alertTwo.showAndWait();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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

    public void backButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("mainMenu.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void viewByWeekSelected(ActionEvent actionEvent) {
        filteredAppointments.setPredicate(appointment -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneWeekFromNow = now.plusWeeks(1);
            LocalDateTime appointmentStart = appointment.getStart();
            return appointmentStart.isAfter(now) && appointmentStart.isBefore(oneWeekFromNow);
        });

    }


    public void viewByMonthSelected(ActionEvent actionEvent) throws SQLException {
        filteredAppointments.setPredicate(appointment -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneMonthFromNow = now.plusMonths(1);
            LocalDateTime appointmentStart = appointment.getStart();
            return appointmentStart.isAfter(now) && appointmentStart.isBefore(oneMonthFromNow);
        });


    }

    public void viewAllRbSelected(ActionEvent actionEvent) {
        filteredAppointments.setPredicate(null);
    }
}

