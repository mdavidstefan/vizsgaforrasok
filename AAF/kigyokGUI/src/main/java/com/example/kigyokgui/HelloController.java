package com.example.kigyokgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloController {
    @FXML private ListView<String> lsBal;
    @FXML private ListView<String> lsJobb;
    @FXML private TextField txSzuro;

    public class Kigyo {
        String fajta;
        int hossz;
        String elofordulas;
        String merges;

        public Kigyo(String sor) {
            String[] s = sor.split(";");
            fajta = s[0];
            hossz = Integer.parseInt(s[1]);
            elofordulas = s[2];
            merges = s[3];
        }
    }

    private ArrayList<Kigyo> kigyok = new ArrayList<>();
    private FileChooser fc = null;

    @FXML private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV fájlok", "*.csv"));
        File inputFile = fc.showOpenDialog(lsBal.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(inputFile, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) kigyok.add(new Kigyo(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
        for (Kigyo k : kigyok ) lsBal.getItems().add(String.format("%s (%dcm), %s", k.fajta, k.hossz, k.elofordulas));
    }

    @FXML private void onSzures() {
        lsJobb.getItems().clear();
        if (!txSzuro.getText().equals("")) {
            String szuro = txSzuro.getText().toLowerCase();
            for (Kigyo k : kigyok) if (k.fajta.toLowerCase().contains(szuro)) lsJobb.getItems().add(k.fajta);
        } else {
            return;
        }
    }

    @FXML private void onKilepes() {
        Platform.exit();
    }
    @FXML private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText(null);
        info.setTitle("Névjegy");
        info.setContentText("Kígyók v1.0.0\n(C) Kandó");
        info.showAndWait();
    }
}