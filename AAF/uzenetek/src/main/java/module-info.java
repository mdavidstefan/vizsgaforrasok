module com.example.uzenetek {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires com.google.gson;

    opens com.example.uzenetek to javafx.fxml;
    exports com.example.uzenetek;
}