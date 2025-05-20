module com.example.nevnap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nevnap to javafx.fxml;
    exports com.example.nevnap;
}