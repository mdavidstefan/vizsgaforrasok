module com.example.ketablak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ketablak to javafx.fxml;
    exports com.example.ketablak;
}