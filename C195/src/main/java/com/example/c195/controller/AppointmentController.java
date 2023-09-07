package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

        ObservableList<Appointments> appointmentsObservableList = AppointmentQuery.getAppointmentData();

        appointmentIdTc.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationTc.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeTc.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTc.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTc.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDateTc.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByTc.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateTimeTc.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByTc.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        customerIdTc.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        userIdTc.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdTc.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        appointmentsTable.setItems(appointmentsObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addButtonClick(ActionEvent actionEvent)  {
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
        try{
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


    public void deleteButtonClick(ActionEvent actionEvent) {
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


}

