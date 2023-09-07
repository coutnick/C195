package com.example.c195.controller;

import com.example.c195.App;
import com.example.c195.dao.UserQuery;
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
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField usernameTF;

    @FXML
    public PasswordField passwordTF;

    @FXML
    public Button loginBut;

    @FXML
    public Label zoneIdLabel;


    Alert alert  = new Alert(Alert.AlertType.WARNING);

    public static int staticUserId;

    public void loginButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        staticUserId = UserQuery.validateLogin(usernameTF.getText(), passwordTF.getText());
        if(staticUserId == -1) {
            alert.showAndWait();
            return;
        }
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
        System.out.println(staticUserId);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ZoneId zoneId = ZoneId.systemDefault();

            zoneIdLabel.setText(String.valueOf(zoneId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}