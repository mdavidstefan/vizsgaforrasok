module com.example.backend {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.backend to javafx.fxml;
    exports com.example.backend;
}