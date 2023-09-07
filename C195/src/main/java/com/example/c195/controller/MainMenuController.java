package com.example.c195.controller;

import com.example.c195.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
    @FXML
    public Button viewCustomerBut;

    @FXML
    public Button viewAppointmentsBut;

    @FXML
    public Button exitButton;

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

    public void exitButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("login.fxml"));
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
