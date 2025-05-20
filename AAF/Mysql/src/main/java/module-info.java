module com.example.mysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.mysql to javafx.fxml;
    exports com.example.mysql;
}