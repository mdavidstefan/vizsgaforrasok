module com.example.tizszam {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires com.google.gson;

    opens com.example.tizszam to javafx.fxml;
    exports com.example.tizszam;
}