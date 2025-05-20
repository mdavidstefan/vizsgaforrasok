package com.example.diafilmgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class HelloController {

    @FXML private ListView<String> lvList;
    @FXML private ComboBox<Integer> cmbxEv;
    @FXML private CheckBox chbxFeketeFeher;
    @FXML private CheckBox chbxSzines;
    @FXML private Label lbDarab;

    public class Diafilm {
        String cim;
        int ev;
        int kocka;
        String szines;

        public Diafilm(String sor) {
            String[] s = sor.split(";");
            cim = s[0];
            ev = Integer.parseInt(s[1]);
            kocka = Integer.parseInt(s[2]);
            szines = s[3];
        }
    }

    private FileChooser fc = null;
    private ArrayList<Diafilm> diafilmek = new ArrayList<>();
    TreeSet<Integer> evek = new TreeSet<>();

    @FXML private void onMegnyitas () {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV fájlok", "*.csv"));
        File fbe = fc.showOpenDialog(lvList.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) diafilmek.add(new Diafilm(be.nextLine()));
            for (Diafilm d : diafilmek) evek.add(d.ev);
            cmbxEv.getItems().addAll(evek);
            cmbxEv.getSelectionModel().select(0);
            for (Diafilm d : diafilmek) if  (d.ev == cmbxEv.getSelectionModel().getSelectedItem()) {
                if (d.szines.equals("I")) {
                    lvList.getItems().add(String.format("%s (%d, %d kocka, %s)", d.cim, d.ev, d.kocka, "színes"));
                } else {
                    lvList.getItems().add(String.format("%s (%d, %d kocka, %s)", d.cim, d.ev, d.kocka, "fekete-fehér"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    @FXML private void valtoztatEv() {
        lvList.getItems().clear();
        for (Diafilm d : diafilmek) if  (d.ev == cmbxEv.getSelectionModel().getSelectedItem()) {
            if (d.szines.equals("I")) {
                lvList.getItems().add(String.format("%s (%d, %d kocka, %s)", d.cim, d.ev, d.kocka, "színes"));
            } else {
                lvList.getItems().add(String.format("%s (%d, %d kocka, %s)", d.cim, d.ev, d.kocka, "fekete-fehér"));
            }
        }
    }

    @FXML private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Névjegy");
        info.setHeaderText(null);
        info.setContentText("Diafilm v1.0.0\n(C) Kandó");
        info.showAndWait();
    }

    @FXML private void onKilepes() {
        Platform.exit();
    }


}