module com.example.autok {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires com.google.gson;

    opens com.example.autok to javafx.fxml;
    exports com.example.autok;
}