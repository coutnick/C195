module com.c195.c195nrains {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.c195.c195nrains to javafx.fxml;
    exports com.c195.c195nrains;
    exports com.c195.c195nrains.controller;
    opens com.c195.c195nrains.controller to javafx.fxml;
}