package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.CustomerQuery;
import com.example.c195.model.Customers;
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

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

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
    public TableColumn<Customers, Integer> divisionIdTc;
    public Button addBut;
    public Button updateBut;
    public Button deleteBut;

    public static Customers customer;

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
            divisionIdTc.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

            customerTableView.setItems(customersObservableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void backButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("mainMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void addButClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("addCustomer.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void updateButClick(ActionEvent actionEvent) {
        try {
            customer = customerTableView.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("updateCustomer.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("caught error " + e);
            e.printStackTrace();
        }
    }

    public void deleteButClick(ActionEvent actionEvent) {
    }
}
