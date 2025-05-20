package com.example.allatok;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelloController {

    public class Allat {
        String allatfaj;
        int magassag;
        int suly;
        int eletkor;

        public Allat (String sor) {
            String [ ] s = sor.split(";");
            allatfaj = s[0];
            magassag = Integer.parseInt(s[1]);
            suly = Integer.parseInt(s[2]);
            eletkor = Integer.parseInt(s[3]);
        }

        public String getAllatfaj() {
            return allatfaj;
        }

        public int getMagassag() {
            return magassag;
        }

        public int getSuly() {
            return suly;
        }

        public int getEletkor() {
            return eletkor;
        }
    }

    @FXML private ListView<Allat> lvLista;


    private FileChooser fc = null;
    @FXML private void onMegnyitas () {
        fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adatállományok", "*.csv"));
        File fbe = fc.showOpenDialog(lvLista.getScene().getWindow());
        Scanner be = null;
        try {
            be = new Scanner(fbe, "utf-8");
            be.nextLine();
            while (be.hasNextLine()) lvLista.getItems().add(new Allat(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( be != null) be.close();
        }
    }

    @FXML private void onNevjegy() {

    }

    @FXML private void onKilepes() {
        Platform.exit();
    }
}