package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.CountryQuery;
import com.example.c195.dao.CustomerQuery;
import com.example.c195.dao.FirstLevelDivisionQuery;
import com.example.c195.dao.UserQuery;
import com.example.c195.helper.TimeStuff;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This add customer controller is used for the add customer page of the gui
 */
public class AddCustomerController implements Initializable {

    @FXML
    public TextField customerNameTf;
    @FXML
    public TextField addressTf;
    @FXML
    public TextField postalCodeTf;
    @FXML
    public Button submitBut;
    @FXML
    public Button backBut;
    @FXML
    public TextField phoneNumberTf;
    @FXML
    public Label addUpdateLabel;
    @FXML
    public ComboBox<String> firstLevDivCb;
    @FXML
    public ComboBox<String> countryCb;

    Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * Sets all countires in the country combobox when the page first loads
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<String> countryNameObservableList = CountryQuery.getCountryData();
            countryCb.setItems(countryNameObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Adds a customer to the customer table in the database. It converts all LocalDateTime and Timestamps to UTC time.
     * It then takes the user back to the customer page
     * @param actionEvent submit button clicked
     * @throws SQLException
     */
    public void submitButClick(ActionEvent actionEvent) throws SQLException {
        try {
            String name = customerNameTf.getText();
            if (name.isEmpty()) {
                alert.setContentText("Please enter a name");
                alert.showAndWait();
                return;
            }
            String address = addressTf.getText();
            if (address.isEmpty()) {
                alert.setContentText("Please enter a address");
                alert.showAndWait();
                return;
            }
            String zipCode = postalCodeTf.getText();
            if (zipCode.isEmpty()) {
                alert.setContentText("Please enter a zip code");
                alert.showAndWait();
                return;
            }
            String phoneNumber = phoneNumberTf.getText();
            if (phoneNumber.isEmpty()) {
                alert.setContentText("Please enter a phone number");
                alert.showAndWait();
                return;
            }
            LocalDateTime createDate = TimeStuff.utcFormattedTime(LocalDateTime.now());
            String createdBy = UserQuery.getUserName(LoginController.staticUserId);
            Timestamp lastUpdated = Timestamp.valueOf(TimeStuff.utcFormattedTime(LocalDateTime.now()));
            String lastUpdatedBy = UserQuery.getUserName(LoginController.staticUserId);
            String division = firstLevDivCb.getSelectionModel().getSelectedItem();
            int divisionId = FirstLevelDivisionQuery.getDivisionId(division);
            CustomerQuery.addCustomer(name, address, zipCode, phoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
        } catch (NullPointerException nullPointerException) {
            alert.setContentText("Please select a country and division");
            alert.showAndWait();
            return;
        }
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("customer.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.getCause();
        }
    }

    /**
     * Takes the user back to the customer page
     * @param actionEvent back button clicked
     */
    public void backButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("customer.fxml")));
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
     * Sets the state or province combo box when a country is selected
     * @param actionEvent country combo box is changed
     * @throws SQLException
     */
    public void countrySelected(ActionEvent actionEvent) throws SQLException {
        try {
            String name = countryCb.getSelectionModel().getSelectedItem();
            int id = CountryQuery.getCountryId(name);
            ObservableList<String> firstLevelDivisionObservableList = FirstLevelDivisionQuery.getFirstLevelDivisionData(id);
            firstLevDivCb.setItems(firstLevelDivisionObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
