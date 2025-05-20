module com.example.kemia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.kemia to javafx.fxml;
    exports com.example.kemia;
}