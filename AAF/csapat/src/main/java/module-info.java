module com.example.csapat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.csapat to javafx.fxml;
    exports com.example.csapat;
}