package com.example.csiga;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.sql.*;

public class HelloController {

    @FXML private TableView<Csiga> tblCsigak;
    @FXML private TableColumn<Csiga, String> tcNev;
    @FXML private TableColumn<Csiga, Integer> tcSebesseg;
    @FXML private TableColumn<Csiga, Integer> tcSuly;
    @FXML private TextField txNev;
    @FXML private TextField txSebesseg;
    @FXML private TextField txSuly;

    public class Csiga {
        public int csaz;
        public String nev;
        public int sebesseg;
        public int suly;

        public Csiga(int csaz, String nev, int sebesseg, int suly) {
            this.csaz = csaz; this.nev = nev; this.sebesseg = sebesseg; this.suly = suly;
        }

        public int getCsaz() {
            return csaz;
        }

        public String getNev() {
            return nev;
        }

        public int getSebesseg() {
            return sebesseg;
        }

        public int getSuly() {
            return suly;
        }
    }

    private Connection con = null;
    private Statement stm = null;

    public void initialize() {
        list();
        tcNev.setCellValueFactory(new PropertyValueFactory<Csiga, String>("nev")); tcNev.setStyle("-fx-alignment: CENTER");
        tcSebesseg.setCellValueFactory(new PropertyValueFactory<Csiga, Integer>("sebesseg")); tcSebesseg.setStyle("-fx-alignment: CENTER");
        tcSuly.setCellValueFactory(new PropertyValueFactory<Csiga, Integer>("suly")); tcSuly.setStyle("-fx-alignment: CENTER");
    }

    @FXML private void list() {
        tblCsigak.getItems().clear();
        ResultSet res = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csiga","root","");
            stm = con.createStatement();
            res = stm.executeQuery("select * from csigak");
            while(res.next()) {
                tblCsigak.getItems().add(new Csiga(res.getInt("csaz"), res.getString("nev"), res.getInt("sebesseg"), res.getInt("suly")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML private void onTable() {
        int i = tblCsigak.getSelectionModel().getSelectedIndex();
        if ( i!= -1) {
            Csiga x = tblCsigak.getSelectionModel().getSelectedItem();
            txNev.setText(x.nev);
            txSebesseg.setText(x.sebesseg + "");
            txSuly.setText(x.suly + "");
        }
    }

    @FXML private void onInsert() {
        String sql = String.format("insert into csigak set nev='%s', sebesseg=%d, suly=%d",
                txNev.getText(), Integer.parseInt(txSebesseg.getText()), Integer.parseInt(txSuly.getText()));
        try {
            stm.executeUpdate(sql);
            list();
            tblCsigak.getSelectionModel().selectLast();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onUpdate() {
        int i = tblCsigak.getSelectionModel().getSelectedIndex();
        if ( i != -1) {
            int csaz = tblCsigak.getSelectionModel().getSelectedItem().csaz;
            String sql = String.format("update csigak set nev='%s', sebesseg=%d, suly=%d where csaz=%d",
                    txNev.getText(), Integer.parseInt(txSebesseg.getText()), Integer.parseInt(txSuly.getText()), csaz);
            try {
                stm.executeUpdate(sql);
                list();
                tblCsigak.getSelectionModel().select(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onDelete() {
        int i = tblCsigak.getSelectionModel().getSelectedIndex();
        if ( i!= -1) {
            int csaz = tblCsigak.getSelectionModel().getSelectedItem().csaz;
            try {
                stm.executeUpdate("delete from csigak where csaz=" + csaz);
                list();
                if (i >= tblCsigak.getItems().size()) i = tblCsigak.getItems().size()-1;
                tblCsigak.getSelectionModel().select(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}