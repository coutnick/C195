module com.c195.c195nrains {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.c195.c195nrains to javafx.fxml;
    exports com.c195.c195nrains;
}