module com.example.allatok {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.allatok to javafx.fxml;
    exports com.example.allatok;
}