package com.example.autoform;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class HelloController {

    @FXML private TableView<Auto> tvAutok;
    @FXML private TableColumn<Auto, Integer> tcId;
    @FXML private TableColumn<Auto, String> tcMarka;
    @FXML private TableColumn<Auto, String> tcModell;
    @FXML private TableColumn<Auto, Integer> tcEv;
    @FXML private TableColumn<Auto, String> tcSzin;
    @FXML private TableColumn<Auto, Integer> tcDarab;
    @FXML private TableColumn<Auto, Integer> tcAtlagar;
    @FXML private ListView<String> lvAutok;
    @FXML private TextField txEv;

    public class Auto {
        int sorszam;
        String marka;
        String modell;
        int gyEv;
        String szin;
        int eladottDb;
        int eladasAr;

        public Auto (String sor) {
            String [] s = sor.split(";");
            sorszam = Integer.parseInt(s[0]);
            marka = s[1];
            modell = s[2];
            gyEv = Integer.parseInt(s[3]);
            szin = s[4];
            eladottDb = Integer.parseInt(s[5]);
            eladasAr = Integer.parseInt(s[6]);
        }

        public int getSorszam() {
            return sorszam;
        }

        public String getMarka() {
            return marka;
        }

        public String getModell() {
            return modell;
        }

        public int getGyEv() {
            return gyEv;
        }

        public String getSzin() {
            return szin;
        }

        public int getEladottDb() {
            return eladottDb;
        }

        public int getEladasAr() {
            return eladasAr;
        }
    }


    public void initialize() {
        tcId.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("sorszam")); tcId.setStyle("-fx-alignment: CENTER");
        tcMarka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));
        tcModell.setCellValueFactory(new PropertyValueFactory<Auto, String>("modell"));
        tcEv.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("gyEv")); tcEv.setStyle("-fx-alignment: CENTER");
        tcSzin.setCellValueFactory(new PropertyValueFactory<Auto, String>("szin")); tcSzin.setStyle("-fx-alignment: CENTER");
        tcDarab.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("eladottDb")); tcDarab.setStyle("-fx-alignment: CENTER");
        tcAtlagar.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("eladasAr")); tcAtlagar.setStyle("-fx-alignment: CENTER");
    }

    private FileChooser fc = null;
    private ArrayList<Auto> autok = new ArrayList<>();
    @FXML private void onBetolt() {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatállományok", "*.csv"));
        File fbe = fc.showOpenDialog(lvAutok.getScene().getWindow());
        beolvas(fbe);
        for (Auto a : autok) tvAutok.getItems().add(a);
    }

    private void beolvas (File f) {
        Scanner be = null;
        try {
            be = new Scanner(f, "UTF-8");
            be.nextLine();
            while (be.hasNextLine()) autok.add(new Auto(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    @FXML private void onListaz() {
        if (!txEv.getText().equals("")) {
            int ev = Integer.parseInt(txEv.getText());
            for (Auto a : autok) {
                if (a.gyEv == ev) {
                    lvAutok.getItems().add(a.marka + " " + a.modell);
                }
            }
        } else {
            return;
        }
    }

    @FXML private void onClose() {
        Alert kerdez = new Alert(Alert.AlertType.CONFIRMATION);
        kerdez.setTitle("Ki akarsz lépni a programból?");
        Optional<ButtonType> a = kerdez.showAndWait();
        if (kerdez.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            kerdez.close();
        }
    }
}