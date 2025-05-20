module com.example.mozi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.mozi to javafx.fxml;
    exports com.example.mozi;
}