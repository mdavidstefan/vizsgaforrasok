module com.example.csokik {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.csokik to javafx.fxml;
    exports com.example.csokik;
}