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

import static javafx.application.Platform.exit;

/**
 * Main menu controller for the scheduler GUI
 */
public class MainMenuController {
    @FXML
    public Button viewCustomerBut;

    @FXML
    public Button viewAppointmentsBut;

    @FXML
    public Button exitButton;



    /**
     * Takes user to the customer page
     * @param actionEvent viewCustomer clicked
     */
    public void viewCustomerOnClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("customer.fxml"));
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
     * Takes the user to the appointment screen
     * @param actionEvent
     */
    public void viewAppointmentsClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("appointments.fxml"));
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
     * exits the application
     * @param actionEvent
     */
    public void exitButtonClick(ActionEvent actionEvent) {exit();}

    /**
     * Takes the user to various reports
     * @param actionEvent
     */
    public void reportButtonClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("reports.fxml"));
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

}

