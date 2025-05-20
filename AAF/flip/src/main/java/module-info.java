module com.example.flip {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flip to javafx.fxml;
    exports com.example.flip;
}