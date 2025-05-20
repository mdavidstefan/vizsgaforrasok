package com.example.fovaros;

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
    @FXML private ListView<String> lvList;
    @FXML private TextField txFovaros;
    @FXML private TextField txFovaroslak;

    public class Adat {
        String orszag;
        String rovidites;
        int o_lakossag;
        String fovaros;
        int f_lakossag;

        public Adat (String sor) {
            String [] s = sor.split(";");
            orszag = s[0];
            rovidites = s[1];
            o_lakossag = Integer.parseInt(s[2]);
            fovaros = s[3];
            f_lakossag = Integer.parseInt(s[4]);
        }
    }

    private FileChooser fc = null;
    public ArrayList<Adat> adatok = new ArrayList<>();

    @FXML
    private void onMegnyitas() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatfájlok", "*.csv"));
        File fbe = fc.showOpenDialog(lvList.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) adatok.add(new Adat(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
        for(Adat a: adatok) lvList.getItems().add(String.format("%s (%,d fő): %s", a.orszag, a.o_lakossag, a.rovidites));
    }

    @FXML private void onClick() {
        int i = lvList.getSelectionModel().getSelectedIndex();
        if (i != -1) {
            txFovaros.setText(adatok.get(i).fovaros);
            txFovaroslak.setText(adatok.get(i).f_lakossag+"");
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
        info.setContentText("Fővárosok v1.0.0\n(C) Kandó");
        info.showAndWait();
    }
}