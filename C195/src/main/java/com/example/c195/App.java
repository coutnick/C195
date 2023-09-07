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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 381, 544);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("resourcebundles.Nat", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr") ) {
            System.out.println(rb.getString("login"));
        }

        if(Locale.getDefault().getLanguage().equals("en") ) {
            System.out.println("Still english");
        }

        JDBC.openConnection();
        launch(args);
        System.out.println();
        JDBC.closeConnection();

    }
}