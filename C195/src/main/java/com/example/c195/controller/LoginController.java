package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.dao.UserQuery;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class LoginController implements Initializable {
    @FXML
    public TextField usernameTF;

    @FXML
    public PasswordField passwordTF;

    @FXML
    public Button loginBut;

    @FXML
    public Label zoneIdLabel;
    @FXML
    public Button exitBut;
    ResourceBundle rb = ResourceBundle.getBundle("resourcebundles.Nat", Locale.getDefault());

    Alert alert  = new Alert(Alert.AlertType.ERROR);

    public static int staticUserId;

    public void loginButtonClick(ActionEvent actionEvent) throws SQLException, IOException {

        staticUserId = UserQuery.validateLogin(usernameTF.getText(), passwordTF.getText());
        if(staticUserId == -1) {
            if(Locale.getDefault().getLanguage().equals("fr")) {
                alert.setContentText(rb.getString("error"));
                alert.showAndWait();
                return;
            }
            alert.setContentText("Login Credentials Are Incorrect Please Try Again");
            alert.showAndWait();

            return;
        }
        ObservableList<Appointments> appointmentsObservableList = AppointmentQuery.getAppointmentData();
        for (Appointments appointment : appointmentsObservableList) {
            if (TimeStuff.withinFifteen(appointment.getStart())) {
                alert.setContentText("Appointment: " + appointment.getAppointmentId() + " Date: " + TimeStuff.localFormattedTime(appointment.getStart())
                        + " is coming up within the next 15 minutes!");
                alert.showAndWait();
            }
        }
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
        System.out.println(staticUserId);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("resourcebundles.Nat", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr") ) {

            usernameTF.setPromptText(rb.getString("username"));
            passwordTF.setPromptText(rb.getString("password"));
            loginBut.setText(rb.getString("login"));
            exitBut.setText(rb.getString("exit"));
        }
        try{
            ZoneId zoneId = ZoneId.systemDefault();
            zoneIdLabel.setText(String.valueOf(zoneId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitClick(ActionEvent actionEvent) {
        exit();
    }
}