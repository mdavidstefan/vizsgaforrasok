module com.example.autok_frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.autok_frontend to javafx.fxml;
    exports com.example.autok_frontend;
}