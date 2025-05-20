package com.example.ketablak;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class masikController {

    @FXML private Button btBezar;

    @FXML private void onBezarClick() {
        btBezar.getScene().getWindow().hide();
    }
}
