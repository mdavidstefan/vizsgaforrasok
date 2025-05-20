module com.example.mosoly {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires com.google.gson;

    opens com.example.mosoly to javafx.fxml;
    exports com.example.mosoly;
}