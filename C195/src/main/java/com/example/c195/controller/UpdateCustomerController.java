package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.CountryQuery;
import com.example.c195.dao.CustomerQuery;
import com.example.c195.dao.FirstLevelDivisionQuery;
import com.example.c195.dao.UserQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Update customer controller for the scheduler GUI
 */
public class UpdateCustomerController implements Initializable {
    @FXML
    public TextField customerIdTf;
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

    /**
     * This is for initializing the update customer controller
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<String> countryNameObservableList = CountryQuery.getCountryData();
            countryCb.setItems(countryNameObservableList);
            customerIdTf.setText(Integer.valueOf(CustomerController.customer.getCustomerId()).toString());
            customerNameTf.setText(CustomerController.customer.getCustomerName());
            addressTf.setText(CustomerController.customer.getAddress());
            postalCodeTf.setText(CustomerController.customer.getPostalCode());
            phoneNumberTf.setText(CustomerController.customer.getPhone());
            firstLevDivCb.setValue(FirstLevelDivisionQuery.getDivision(CustomerController.customer.getDivisionId()));
            countryCb.setValue(CountryQuery
                            .getCountry(FirstLevelDivisionQuery.getCountryId(FirstLevelDivisionQuery
                            .getDivision(CustomerController.customer.getDivisionId())) ) );
            ObservableList<String> firstLevelDivisionObservableList = FirstLevelDivisionQuery
                    .getFirstLevelDivisionData(CountryQuery.getCountryId(countryCb.getSelectionModel().getSelectedItem()));
            firstLevDivCb.setItems(firstLevelDivisionObservableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This action even updates a customer
     * @param actionEvent submit button clicked
     * @throws SQLException
     */
    public void submitButClick(ActionEvent actionEvent) throws SQLException {
        int customerId = Integer.parseInt(customerIdTf.getText());
        String name = customerNameTf.getText();
        String address = addressTf.getText();
        String postalCode = postalCodeTf.getText();
        String phone = phoneNumberTf.getText();
        Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = UserQuery.getUserName(LoginController.staticUserId);
        String division = firstLevDivCb.getSelectionModel().getSelectedItem();
        int divisionId = FirstLevelDivisionQuery.getDivisionId(division);
        CustomerQuery.updateCustomer(customerId, name, address, postalCode, phone, lastUpdated, lastUpdatedBy, divisionId);
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("customer.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getCause();
        }
    }

    /**
     * This action event takes a user back to the customer screen
     * @param actionEvent back button
     */
    public void backButClick(ActionEvent actionEvent) {
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

    /**
     * Used to populate the division combo box
     * @param actionEvent countryCb is clicked
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


