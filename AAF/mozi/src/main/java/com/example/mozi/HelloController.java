package com.example.mozi;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    @FXML private TableView<Film> tblMozi;
    @FXML private TableColumn<Film, String> columnCim;
    @FXML private TableColumn<Film, Integer> columnEv;
    @FXML private TableColumn<Film, Integer> columnHossz;
    @FXML private TableColumn<Film, Float> columnImdb;
    @FXML private TextField txCim;
    @FXML private TextField txEv;
    @FXML private TextField txHossz;
    @FXML private TextField txImdb;

    public class Film {
        public String cim;
        public int ev;
        public int hossz;
        public float imdb;

        public Film(String cim, int ev, int hossz, float imdb) {
            this.cim = cim;
            this.ev = ev;
            this.hossz = hossz;
            this.imdb = imdb;
        }

        public String getCim() { return cim; }
        public Integer getEv() { return ev; }
        public Integer getHossz() { return hossz; }
        public Float getImdb() { return imdb; }
    }

    private Connection con = null;
    private Statement stm = null;
    private ArrayList<Integer> idList = new ArrayList<>();


    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mozi","root","");
            stm = con.createStatement();
            String sql = "select * from filmek";
            ResultSet res = stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        columnCim.setCellValueFactory(new PropertyValueFactory<Film, String>("cim"));
        columnEv.setCellValueFactory(new PropertyValueFactory<Film, Integer>("ev")); columnEv.setStyle("-fx-alignment: CENTER");
        columnHossz.setCellValueFactory(new PropertyValueFactory<Film, Integer>("hossz")); columnHossz.setStyle("-fx-alignment: CENTER");
        columnImdb.setCellValueFactory(new PropertyValueFactory<Film, Float>("imdb")); columnImdb.setStyle("-fx-alignment: CENTER");

        listFilms();
    }

    private void listFilms() {
        String sql = "select * from filmek";
        idList.clear();
        tblMozi.getItems().clear();
        try {
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                tblMozi.getItems().add(new Film(res.getString("cim"), res.getInt("ev"), res.getInt("hossz"), res.getFloat("imdb")));
                idList.add(res.getInt("faz"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFilmPressed() {
        int i = tblMozi.getSelectionModel().getSelectedIndex();
        if( i != -1) {
            String sql = "select * from filmek where faz=" + idList.get(i);
            try {
                ResultSet res = stm.executeQuery(sql);
                res.next();
                txCim.setText(res.getString("cim"));
                txEv.setText(res.getString("ev"));
                txHossz.setText(res.getString("hossz"));
                txImdb.setText(res.getString("imdb"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onFelvesz() {
        String sql = "insert into filmek set cim='" + txCim.getText() +
                "', ev=" + txEv.getText() + ", hossz=" + txHossz.getText() + ", imdb=" + txImdb.getText();
        try {
            int res = stm.executeUpdate(sql);
            listFilms();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onModosit() {
        int i = tblMozi.getSelectionModel().getSelectedIndex();
        if (i != -1) {
            String sql = "update filmek set cim='" + txCim.getText() +
                    "', ev=" + txEv.getText() +
                    ", hossz=" + txHossz.getText() +
                    ", imdb=" + txImdb.getText() + " where faz=" + idList.get(i);
            try{
                int db = stm.executeUpdate(sql);
                listFilms();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onTorol() {
        int i = tblMozi.getSelectionModel().getSelectedIndex();
        if ( i!= -1 ) {
            String sql = "delete from filmek where faz=" + idList.get(i);
            try{
                int db = stm.executeUpdate(sql);
                listFilms();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}