package com.example.nevnap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class HelloController {
    public ArrayList<Nev> nevek = new ArrayList<>();
    String[] honapok = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};
    @FXML
    private ListView<String> lvList;
    @FXML
    private ComboBox<String> cmbxHonapok;
    private FileChooser fc = null;

    @FXML
    private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatfájlok", "*.csv"));
        File fbe = fc.showOpenDialog(lvList.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe);
            while (be.hasNextLine()) lvList.getItems().add(be.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    public void initialize() {
        for (String honap : honapok) cmbxHonapok.getItems().add(honap);
        cmbxHonapok.getSelectionModel().select(0);
        readFile("nevnap.csv");
        loadList();
    }

    @FXML
    private void readFile(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev));
            while (be.hasNextLine()) nevek.add(new Nev(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    @FXML
    private void loadList() {
        TreeMap<String, String> stat = new TreeMap<>();
        for (Nev n : nevek) {
            for (String nap : n.napok) {
                if (!stat.containsKey(nap)) stat.put(nap, n.nev);
                else stat.put(stat.get(nap), n.nev);
            }
        }
    }

    @FXML
    private void onKilepes() {
        Platform.exit();
    }

    @FXML
    private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Névjegy");
        info.setHeaderText(null);
        info.setContentText("Névnap v1.0.0\n(C) Kandó");
        info.showAndWait();
    }

    public class Nev {
        public String nev;
        public ArrayList<String> napok = new ArrayList<>();

        public Nev(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            for (int i = 1; i < s.length; i++) {
                napok.add(s[i]);
            }
        }
    }

}