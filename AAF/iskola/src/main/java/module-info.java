module com.example.iskola {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.iskola to javafx.fxml;
    exports com.example.iskola;
}