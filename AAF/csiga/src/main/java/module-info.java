module com.example.csiga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.csiga to javafx.fxml;
    exports com.example.csiga;
}