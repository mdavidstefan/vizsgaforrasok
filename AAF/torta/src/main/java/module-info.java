module com.example.torta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.torta to javafx.fxml;
    exports com.example.torta;
}