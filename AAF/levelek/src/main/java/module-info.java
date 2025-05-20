module com.example.levelek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.levelek to javafx.fxml;
    exports com.example.levelek;
}