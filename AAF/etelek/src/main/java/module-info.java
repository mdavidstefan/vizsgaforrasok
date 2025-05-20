module com.example.etelek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.etelek to javafx.fxml;
    exports com.example.etelek;
}