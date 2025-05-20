module com.example.fovaros {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fovaros to javafx.fxml;
    exports com.example.fovaros;
}