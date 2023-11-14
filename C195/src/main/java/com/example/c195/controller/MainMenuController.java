package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.helper.TimeStuff;
import com.example.c195.model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class MainMenuController {
    @FXML
    public Button viewCustomerBut;

    @FXML
    public Button viewAppointmentsBut;

    @FXML
    public Button exitButton;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);



    public void viewCustomerOnClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("customer.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void viewAppointmentsClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("appointments.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void exitButtonClick(ActionEvent actionEvent) {exit();}

    public void reportButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("reports.fxml"));
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

