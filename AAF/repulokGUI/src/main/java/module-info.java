module com.example.repulokgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.repulokgui to javafx.fxml;
    exports com.example.repulokgui;
}