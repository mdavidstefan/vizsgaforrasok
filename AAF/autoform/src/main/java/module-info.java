module com.example.autoform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autoform to javafx.fxml;
    exports com.example.autoform;
}