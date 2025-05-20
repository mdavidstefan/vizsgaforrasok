package com.example.kemia;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML private TableView<Elem> tblElem;
    @FXML private TableColumn<Elem, String> tcJel;
    @FXML private TableColumn<Elem, String> tcNev;
    @FXML private ListView<String> lvVegyulet;
    @FXML private TextField txVegyulet;
    @FXML private TextField txKotes;
    @FXML private ListView<String> lvKotes;
    @FXML private TextField txElem;
    @FXML private Label lbKotes;

    public class Elem {
        public int rend;
        public String jel;
        public String nev;

        public Elem(String jel, String nev) {
            this.rend = rend;
            this.jel = jel;
            this.nev = nev;
        }

        public int getRend() { return rend; }
        public String getJel() { return jel; }
        public String getNev() { return nev; }
    }

    private Connection con = null;
    private Statement stm = null;
    private ArrayList<Integer> vegyuletazon = new ArrayList<>();

    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kemia","root","");
            stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from elemek");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tcJel.setCellValueFactory(new PropertyValueFactory<Elem, String>("jel")); tcJel.setStyle("-fx-alignment: CENTER");
        tcNev.setCellValueFactory(new PropertyValueFactory<Elem, String>("nev")); tcNev.setStyle("-fx-alignment: CENTER");

        listElemek();
        listVegyulet();
    }

    private void listElemek() {
        tblElem.getItems().clear();
        try {
            ResultSet res = stm.executeQuery("select * from elemek");
            while(res.next()) {
                tblElem.getItems().add(new Elem(res.getString("jel"), res.getString("nev")));
            }
            tblElem.getSelectionModel().select(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listVegyulet() {
        vegyuletazon.clear();
        lvVegyulet.getItems().clear();
        try {
            ResultSet res = stm.executeQuery("select * from vegyulet");
            while(res.next()) {
                lvVegyulet.getItems().add(res.getString("nev"));
                vegyuletazon.add(res.getInt("vaz"));
            }
            lvVegyulet.getSelectionModel().select(0);
            //System.out.println(vegyuletazon);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onVegyulet() {
        lvKotes.getItems().clear();
        int i = lvVegyulet.getSelectionModel().getSelectedIndex()+1;
        if( i != -1) {
            txVegyulet.setText(lvVegyulet.getSelectionModel().getSelectedItem());
            try {
                ResultSet res = stm.executeQuery("select * from kotes inner join elemek on kotes.rend = elemek.rend where vaz=" + i);
                while(res.next()) lvKotes.getItems().add(res.getString("jel") + "(" + res.getInt("db") + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String kotestext = " ";
            for(String sor : lvKotes.getItems()) {
                String[] kotes = sor.split("\\(");
                kotes[1] = kotes[1].substring(0, 1);
                kotestext += kotes[0];
                if(!(kotes[1].equals("1"))) kotestext += kotes[1];
                lbKotes.setText(kotestext);
            }
        }
    }

    @FXML private void onKotes() {
        int i = lvKotes.getSelectionModel().getSelectedIndex()+1;
        if (i != -1) {
            String [] kotes = lvKotes.getSelectionModel().getSelectedItem().split("\\(");
            kotes[1] = kotes[1].substring(0, 1);
            txKotes.setText(kotes[1]);
        }
    }

    @FXML private void onElem() {
        int i = tblElem.getSelectionModel().getSelectedIndex();
        if( i != -1 ) {
            txElem.setText(tblElem.getSelectionModel().getSelectedItem().jel + " : " + tblElem.getSelectionModel().getSelectedItem().nev);
        }
    }


}