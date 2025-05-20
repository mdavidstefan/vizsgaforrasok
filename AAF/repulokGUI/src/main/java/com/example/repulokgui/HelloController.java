package com.example.repulokgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class HelloController {

    @FXML private ListView<String> lvGyartok;
    @FXML private ListView<String> lvTipusok;

    public class Repulo {
        String tipus;
        float hossz;
        int suly;
        int ferohely;
        int tank;

        public Repulo (String sor) {
            String [] s = sor.split(";");
            tipus = s[0];
            hossz = Float.parseFloat(s[1]);
            suly = Integer.parseInt(s[2]);
            ferohely = Integer.parseInt(s[3]);
            tank = Integer.parseInt(s[4]);
        }
    }

    private FileChooser fc = null;
    public ArrayList<Repulo> repulok = new ArrayList<>();

    @FXML private void loadGyartok() {
        TreeSet<String> gyartok = new TreeSet<>();
        for (Repulo r : repulok) {
            String [] hossz = r.tipus.split(" ");
            if (hossz.length > 1) gyartok.add(hossz[0]);
            else gyartok.add(r.tipus);
        }
        for (String gyarto : gyartok) lvGyartok.getItems().add(gyarto);
    }

    @FXML private void onClick() {
        int i = lvGyartok.getSelectionModel().getSelectedIndex();
        if (i != -1) {
            lvTipusok.getItems().clear();
            String gyarto = lvGyartok.getSelectionModel().getSelectedItem();
            for (Repulo r : repulok) if (r.tipus.contains(gyarto)) lvTipusok.getItems().add(r.tipus);
        }
    }

    @FXML
    private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatfájlok", "*.csv"));
        File fbe = fc.showOpenDialog(lvGyartok.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) repulok.add(new Repulo(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
        loadGyartok();
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
        info.setContentText("Repülők v1.0.0\n(C) Kandó");
        info.showAndWait();
    }
}