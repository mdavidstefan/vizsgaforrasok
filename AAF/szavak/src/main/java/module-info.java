module com.example.szavak {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires com.google.gson;

    opens com.example.szavak to javafx.fxml;
    exports com.example.szavak;
}