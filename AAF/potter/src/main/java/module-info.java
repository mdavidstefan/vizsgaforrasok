module com.example.potter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.potter to javafx.fxml;
    exports com.example.potter;
}