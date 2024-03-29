package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.*;
import com.example.c195.helper.TimeStuff;
import com.example.c195.model.Appointments;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Controller for the report views GUI
 */

public class ReportController implements Initializable {
    public ComboBox<String> reportCb;
    public TableColumn<Appointments, String> appointmentIdTf;
    public TableColumn<Appointments, String> titleTf;
    public TableColumn<Appointments, String> typeTf;
    public TableColumn<Appointments, String> descriptionTf;
    public TableColumn<Appointments, LocalDateTime> startDateTf;
    public TableColumn<Appointments, LocalDateTime> endDateTf;
    public TableColumn<Appointments, Integer> customerTf;

    private final ObservableList<String> comboContentObservableList = FXCollections.observableArrayList();
    private final ObservableList<String> monthObservableList = FXCollections.observableArrayList();


    public Button backButton;
    public ComboBox<String> optionCb;
    public TableView<Appointments> appointmentTv;
    public Label infoLabel;

    /**
     * Initializes the comboContentObservableList and the Month Observable list
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboContentObservableList.add("Type");
        comboContentObservableList.add("Month");
        comboContentObservableList.add("Contact");
        comboContentObservableList.add("User");
        comboContentObservableList.add("Country");
        reportCb.setItems(comboContentObservableList);

        monthObservableList.add("JANUARY");
        monthObservableList.add("FEBRUARY");
        monthObservableList.add("MARCH");
        monthObservableList.add("APRIL");
        monthObservableList.add("MAY");
        monthObservableList.add("JUNE");
        monthObservableList.add("JULY");
        monthObservableList.add("AUGUST");
        monthObservableList.add("SEPTEMBER");
        monthObservableList.add("OCTOBER");
        monthObservableList.add("NOVEMBER");
        monthObservableList.add("DECEMBER");

    }

    /**
     * This enables the option combo box corresponding to the report chosen
     *
     * @param actionEvent report combo box clicked
     * @throws SQLException
     */
    public void reportCbClicked(ActionEvent actionEvent) throws SQLException {

        String option = reportCb.getValue();
        try {
            if (option.equals("Type")) {
                optionCb.setDisable(false);
                optionCb.setItems(AppointmentQuery.getAppointmentTypes());
            } else if (option.equals("Month")) {
                optionCb.setDisable(false);
                optionCb.setItems(monthObservableList);
            } else if (option.equals("Contact")) {
                optionCb.setDisable(false);
                optionCb.setItems(ContactQuery.getContactNames());
            } else if (option.equals("User")) {
                optionCb.setDisable(false);
                optionCb.setItems(UserQuery.getUserNames());
            } else if (option.equals("Country")) {
                optionCb.setDisable(false);
                optionCb.setItems(CountryQuery.getCountryData());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("mainMenu.fxml"));
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
     * @param actionEvent
     * @throws SQLException
     * @lambda The lambda is this fucntion is used to set the time to the local time for every appointment in the list. The lambda
     * justification is that it can go through a stream of all times and change the date.
     */
    public void optionCbClicked(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> primaryAppointmentsObservableList = AppointmentQuery.getAppointmentData();
        String report = reportCb.getValue();
        appointmentIdTf.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleTf.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeTf.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionTf.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateTf.setCellValueFactory(cellData -> {
            LocalDateTime utcTime = cellData.getValue().getStart();
            return new SimpleObjectProperty<>(TimeStuff.localFormattedTime(utcTime));
        });
        endDateTf.setCellValueFactory(cellData -> {
            LocalDateTime utcTime = cellData.getValue().getEnd();
            return new SimpleObjectProperty<>(TimeStuff.localFormattedTime(utcTime));
        });
        customerTf.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        switch (report) {
            case "Type":
                appointmentTv.getItems().clear();
                infoLabel.setText("Information: ");
                if (optionCb.getValue() != null) {
                    ObservableList<Appointments> appointmentsObservableList = AppointmentQuery.getAppointmentsByType(optionCb.getValue());
                    appointmentTv.setItems(appointmentsObservableList);
                    infoLabel.setText("Information: " + optionCb.getValue() + " has " + appointmentsObservableList.size() + " entries.");
                }
                break;
            case "Month":
                appointmentTv.getItems().clear();
                infoLabel.setText("Information");
                if (optionCb.getValue() != null) {
                    ObservableList<Appointments> appointmentsObservableListByMonth = FXCollections.observableArrayList();
                    Month month = Month.valueOf(optionCb.getValue());

                    try {
                        for (Appointments appointment : primaryAppointmentsObservableList) {
                            if (appointment.getStart().getMonth() == month) {
                                appointmentsObservableListByMonth.add(appointment);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }
                    appointmentTv.setItems(appointmentsObservableListByMonth);
                    infoLabel.setText("Information: " + month + " has " + appointmentsObservableListByMonth.size() + " appointments.");
                }
                break;
            case "Contact":
                infoLabel.setText("Information");
                appointmentTv.getItems().clear();
                if (optionCb.getValue() != null) {
                    String name = optionCb.getValue();
                    ObservableList<Appointments> appointmentsObservableListByContact = FXCollections.observableArrayList();
                    try {
                        for (Appointments appointment : primaryAppointmentsObservableList) {
                            if (appointment.getContactId() == ContactQuery.getContactId(name)) {
                                appointmentsObservableListByContact.add(appointment);
                            }
                        }
                        appointmentTv.setItems(appointmentsObservableListByContact);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
            case "User":
                appointmentTv.getItems().clear();
                if (optionCb.getValue() != null) {
                    String user = optionCb.getValue();
                    ObservableList<Appointments> appointmentsObservableListByCountry = FXCollections.observableArrayList();
                    try {
                        for (Appointments appointment : primaryAppointmentsObservableList) {
                            if (UserQuery.getUserName(appointment.getUserId()).equals(user)) {
                                appointmentsObservableListByCountry.add(appointment);
                            }
                        }
                        appointmentTv.setItems(appointmentsObservableListByCountry);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
            case "Country":
                appointmentTv.getItems().clear();
                if (optionCb.getValue() != null) {
                    String countryName = optionCb.getValue();
                    int id = CountryQuery.getCountryId(countryName);
                    int count = CustomerQuery.customerCountryCount(id);
                    if (count == 1) {
                        infoLabel.setText("There is 1 customer in " + countryName);
                        break;
                    } else {
                        infoLabel.setText("There are " + count + " customers in " + countryName);
                    }
                    break;
                }
        }

    }
}


