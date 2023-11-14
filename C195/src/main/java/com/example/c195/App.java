package com.example.c195;

import com.example.c195.controller.AppointmentController;
import com.example.c195.controller.LoginController;
import com.example.c195.dao.AppointmentQuery;
import com.example.c195.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("resourcebundles.Nat", Locale.getDefault());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 381, 544);
        if(Locale.getDefault().getLanguage().equals("fr")) {
            stage.setTitle(rb.getString("login"));
        }
        else {
            stage.setTitle("Login");
        }
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        System.out.println();
        JDBC.closeConnection();

    }
}