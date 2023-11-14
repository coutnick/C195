package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.CustomerQuery;
import com.example.c195.dao.FirstLevelDivisionQuery;
import com.example.c195.dao.UserQuery;
import com.example.c195.model.Appointments;
import com.example.c195.model.Customers;
import com.example.c195.model.FirstLevelDivisions;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Customer controller for the Scheduler Gui
 */
public class CustomerController implements Initializable {
    @FXML
    public Button backBut;

    @FXML
    public TableView<Customers> customerTableView;

    @FXML
    public TableColumn<Customers, Integer> customerIdTc;

    @FXML
    public TableColumn<Customers, String> customerNameTc;

    @FXML
    public TableColumn<Customers, String> addressTc;

    @FXML
    public TableColumn<Customers, String> postalCodeTc;

    @FXML
    public TableColumn<Customers, String> phoneTc;

    @FXML
    public TableColumn<Customers, LocalDateTime> createDateTc;

    @FXML
    public TableColumn<Customers, String> createdByTc;

    @FXML
    public TableColumn<Customers, Timestamp> lastUpdateTc;

    @FXML
    public TableColumn<Customers, String> lastUpdatedByTc;

    @FXML
    public TableColumn<Customers, String> divisionIdTc;
    public Button addBut;
    public Button updateBut;
    public Button deleteBut;

    public static Customers customer;

    public static ObservableList<Appointments> appointments;
    Alert alert  = new Alert(Alert.AlertType.ERROR);
    Alert alertTwo = new Alert(Alert.AlertType.INFORMATION);

    /**
     * initializes the customer controller in the GUI with all customers in the customer table of the database
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ObservableList<Customers> customersObservableList = CustomerQuery.getCustomerData();
            customerIdTc.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameTc.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressTc.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeTc.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneTc.setCellValueFactory(new PropertyValueFactory<>("phone"));
            createDateTc.setCellValueFactory(new PropertyValueFactory<>("createDate"));
            createdByTc.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            lastUpdateTc.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
            lastUpdatedByTc.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
            divisionIdTc.setCellValueFactory(cellData -> {
                int divisionId = cellData.getValue().getDivisionId();
                String divisionName;
                try {
                    divisionName = FirstLevelDivisionQuery.getDivision(divisionId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return new SimpleStringProperty(divisionName);

            });

            customerTableView.setItems(customersObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This action event takes the user back to the main-menu
     * @param actionEvent back button clicked
     */

    public void backButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("mainMenu.fxml")));
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
     * Takes the user to the add customer page
     * @param actionEvent add button clicked
     */
    public void addButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("addCustomer.fxml")));
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
     * Takes the user to the update page after they select a customer
     * @param actionEvent update button is clicked after a customer is selected
     */
    public void updateButClick(ActionEvent actionEvent) {
        try {
            customer = customerTableView.getSelectionModel().getSelectedItem();
            if(customer == null) {
                alert.setContentText("Please select a customer before trying to update!");
                alert.showAndWait();
                return;
            }
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("updateCustomer.fxml")));
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
     * This action event deletes the selected customer.
     * @lambda The lambda is this functions justification is to go through the stream and check to see if the customer that is trying to be deleted
     * has any other appointments. Due to database constraints the customer should not be deleted if they have appointments.
     * It goes through the stream and if ANY appointments customer ID matches any customer ID it shows an alert and the user is not deleted.
     * @param actionEvent customer is selected and delete button is clicked
     * @throws SQLException
     */
    public void deleteButClick(ActionEvent actionEvent) throws SQLException {
        customer = customerTableView.getSelectionModel().getSelectedItem();
        if(customer == null) {
            alert.setContentText("Please select a customer to delete");
            alert.showAndWait();
            return;
        }
        appointments = AppointmentQuery.getAppointmentData();
        if(appointments.stream().anyMatch(appointment -> appointment.getCustomerId() == customer.getCustomerId())) {
               alert.setContentText("Can not delete this customer do to them having an appointment. Please " +
                       "make sure the customer does not have an appointment before delete!");
               alert.showAndWait();
               return;
           }
        CustomerQuery.deleteCustomer(customer.getCustomerId());
        alertTwo.setContentText("Customer: " + customer.getCustomerName() + " has been deleted!");
        alertTwo.showAndWait();

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
}
