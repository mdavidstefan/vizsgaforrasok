package com.example.leltargui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class HelloController {
    @FXML private ListView<String> lvBal;
    @FXML private ListView<String> lvJobb;
    @FXML private ComboBox<String> cmbxEv;
    @FXML private Label lbAlso;

    public class Adat {
        String megnevezes;
        int beszerzes;
        int db;
        int egysegar;

        public Adat (String sor) {
            String [] s = sor.split(";");
            megnevezes = s[0];
            beszerzes = Integer.parseInt(s[1]);
            db = Integer.parseInt(s[2]);
            egysegar = Integer.parseInt(s[3]);
        }
    }

    private FileChooser fc = null;
    private ArrayList<Adat> adatok = new ArrayList<>();
    private TreeSet<Integer> evek = new TreeSet<>();

    @FXML private void changeEv() {
        lvJobb.getItems().clear();
        int jobb = 0;
        int ev = Integer.parseInt(cmbxEv.getSelectionModel().getSelectedItem().split(" ")[0]);
        for (Adat a : adatok) if (a.beszerzes == ev) {
            lvJobb.getItems().add(String.format("%d x %s = %,d,-Ft", a.db, a.megnevezes, (a.egysegar * a.db)));
            jobb += (a.egysegar * a.db);
        }
        lbAlso.setText(String.format("%d darab / %,d,-Ft", lvJobb.getItems().size(), jobb));


    }

    @FXML private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatállományok", "*.csv"));
        File fbe = fc.showOpenDialog(lvBal.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) adatok.add(new Adat(be.nextLine()));
            for (Adat a: adatok) {
                evek.add(a.beszerzes);
                lvBal.getItems().add(String.format("%d: %s (%d x %,d,-Ft)", a.beszerzes, a.megnevezes, a.db, a.egysegar));
            }
            for (Integer ev : evek) cmbxEv.getItems().add(String.format("%d adatai:", ev));
            cmbxEv.getSelectionModel().select(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
        changeEv();
    }

    @FXML private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText(null);
        info.setContentText("Leltár v1.0.0\n(C) Kandó");
        info.setTitle("Névjegy");
        info.showAndWait();
    }

    @FXML private void onKilepes() {
        Platform.exit();
    }
}