package com.example.flip;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Scanner;

public class HelloController {

    @FXML private Pane pnJatek;
    @FXML private Label lbDarab;
    @FXML private Label lbSzazalek;
    @FXML private Label lbLevel;

    private ImageView[][] ivKepek = new ImageView[8][12];
    private Image red = null;
    private Image white = null;

    private int whitecount = 96;
    private int level = 1;

    public void initialize() {
        red = new Image(getClass().getResourceAsStream("icons/red.png"));
        white = new Image(getClass().getResourceAsStream("icons/white.png"));
        for(int sor = 0; sor < 8; sor++) {
            for(int oszlop = 0; oszlop < 12; oszlop++) {
                int s = sor, o = oszlop;
                ivKepek[sor][oszlop] = new ImageView(white);
                ivKepek[sor][oszlop].setLayoutX(10 + oszlop*64);
                ivKepek[sor][oszlop].setLayoutY(10 + sor*64);
                ivKepek[sor][oszlop].setOnMousePressed(e -> katt(s, o));
                pnJatek.getChildren().add(ivKepek[sor][oszlop]);
            }
        }
        betolt(1);
    }

    private void betolt(int i) {
        whitecount = 0;
        Scanner be = new Scanner(getClass().getResourceAsStream("levels/level" + i + ".txt"));
        for(int sor = 0; sor < 8; sor++) {
            String sorka = be.nextLine();
            for(int oszlop = 0; oszlop < 12; oszlop++) {
                if(sorka.charAt(oszlop) == 'W') ivKepek[sor][oszlop].setImage(white); else ivKepek[sor][oszlop].setImage(red);
            }
        }
        level = i; lbLevel.setText(level + "");
        showInformation();
    }

    private void katt(int sor, int oszlop) {
        csere(sor, oszlop);
        for(int o = 0; o < 12; o++) csere(sor, o);
        for(int s = 0; s < 8; s++) csere(s, oszlop);
        showInformation();
        if(whitecount == 96) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Flip");
            info.setHeaderText(null);
            info.setContentText("Pálya teljesítve!");
            info.setGraphic(new ImageView(red));
            info.showAndWait();
        }
    }

    private void csere(int sor, int oszlop) {
        if(ivKepek[sor][oszlop].getImage() == red) {
            ivKepek[sor][oszlop].setImage(white); whitecount++;
        } else {
            ivKepek[sor][oszlop].setImage(red); whitecount--;
        }
    }

    private void showInformation() {
        lbDarab.setText(whitecount + " db");
        lbSzazalek.setText(whitecount*100/96 + " %");
    }

    @FXML private void onNextLevel() {
        if(level < 5) betolt(++level);
    }

    @FXML private void onPrevLevel() {
        if(level > 1) betolt(--level);
    }

    @FXML private void onReload() {
        betolt(level);
    }

}