package com.example.utazokgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class HelloController {
    @FXML private ListView<String> lvVarosok;
    @FXML private ListView<String> lvUtazasok;

    public class Utazas {
        String nev;
        String varos;
        String u_datum;
        String u_honap;
        String u_nap;
        String indulas;
        int i_ora;
        int i_perc;

        public Utazas(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            varos = s[1];
            u_datum = s[2];
            u_honap = s[2].split("\\.")[0];
            u_nap = s[2].split("\\.")[1];
            indulas = s[3];
            i_ora = Integer.parseInt(s[3].split(":")[0]);
            i_perc = Integer.parseInt(s[3].split(":")[1]);
        }
    }

    private ArrayList<Utazas> utazasok = new ArrayList<>();
    private FileChooser fc = null;
    private TreeSet<String> varosok = new TreeSet<>();

    @FXML private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".CSV fájlok", "*.csv"));
        File inputFile = fc.showOpenDialog(lvVarosok.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(inputFile, "utf-8");
            while (be.hasNextLine()) utazasok.add(new Utazas(be.nextLine()));
            for (Utazas u : utazasok) varosok.add(u.varos);
            for (String varos : varosok) lvVarosok.getItems().add(varos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    @FXML private void showUtazasokOnClick() {
        int i = lvVarosok.getSelectionModel().getSelectedIndex();
        lvUtazasok.getItems().clear();
        if (i != -1) {
            String varos = lvVarosok.getSelectionModel().getSelectedItem();
            for (Utazas u: utazasok) if (u.varos.equals(varos)) lvUtazasok.getItems().add(String.format("%s (%s %s)", u.nev, u.u_datum, u.indulas));
        }
    }

    @FXML private void onKilepes() {
        Platform.exit();
    }
    @FXML private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Névjegy");
        info.setHeaderText(null);
        info.setContentText("Utazók v1.0.0\n(C) Kandó");
        info.showAndWait();
    }
}