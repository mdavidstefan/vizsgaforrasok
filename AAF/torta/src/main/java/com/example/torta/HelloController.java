package com.example.torta;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    @FXML private TableView<Torta> tblTortak;
    @FXML private TableColumn<Torta, String> tcTorta;
    @FXML private TableColumn<Torta, Integer> tcSzelet;
    @FXML private TableColumn<Torta, String> tcHab;
    @FXML private ComboBox<String> cmbxFajtak;
    @FXML private TextField txSzelet;
    @FXML private CheckBox chbxHab;

    public class Torta {
        public String fajta;
        public int szelet;
        public String hab;

        public Torta (String fajta, int szelet, boolean hab) {
            this.fajta = fajta;
            this.szelet = szelet;
            this.hab = hab ? "Igen" : "-";
        }

        public String getFajta() { return fajta; }
        public int getSzelet() { return szelet; }
        public String getHab() { return hab; }

    }

    private Connection con = null;
    private Statement stm = null;
    private ArrayList<Integer> rendelesId = new ArrayList<>();
    private ArrayList<Integer> tortaId = new ArrayList<>();

    public void initialize () {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/torta","root","");
            stm = con.createStatement();

            ResultSet res = stm.executeQuery("select fajta from tortak");
            while(res.next()) cmbxFajtak.getItems().add(res.getString("fajta"));
            cmbxFajtak.getSelectionModel().select(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tcTorta.setCellValueFactory(new PropertyValueFactory<Torta, String>("fajta"));
        tcSzelet.setCellValueFactory(new PropertyValueFactory<Torta, Integer>("szelet"));
        tcHab.setCellValueFactory(new PropertyValueFactory<Torta, String>("hab"));

        listAll();
    }

    @FXML private void listAll() {
        rendelesId.clear();
        tblTortak.getItems().clear();
        try {
            ResultSet res = stm.executeQuery("select * from tortak inner join rendeles using(taz)");
            while(res.next())  {
                tblTortak.getItems().add(new Torta(res.getString("fajta"), res.getInt("szelet"), res.getBoolean("hab")));
                rendelesId.add(res.getInt("raz"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onClick() {
        int i = tblTortak.getSelectionModel().getSelectedIndex();
        if( i != -1) {
            String sql = "select * from tortak inner join rendeles using(taz) where raz=" + rendelesId.get(i);
            try {
                ResultSet res = stm.executeQuery(sql);
                while(res.next()) {
                    cmbxFajtak.getSelectionModel().select(res.getString("fajta"));
                    txSzelet.setText(res.getString("szelet"));
                    if(res.getInt("hab") == 1) {
                        chbxHab.setSelected(true);
                    }
                    else {
                        chbxHab.setSelected(false);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onInsert() {
        String sql = "insert into rendeles set rendeles.taz=" + (cmbxFajtak.getSelectionModel().getSelectedIndex()+1) + ", szelet=" + txSzelet.getText() + ", hab=";
        if(chbxHab.isSelected()) sql += "1"; else sql += "0";
        try {
            System.out.println(sql);
            int res = stm.executeUpdate(sql);
            listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onUpdate() {
        int i = (tblTortak.getSelectionModel().getSelectedIndex()+1);
        if( i!= -1) {
            String sql = "update rendeles set rendeles.taz=" + (cmbxFajtak.getSelectionModel().getSelectedIndex()+1) + ", szelet=" + txSzelet.getText() + ", hab=";
            if(chbxHab.isSelected()) sql += "1"; else sql += "0";
            sql += " where raz=" + i;
            try {
                System.out.println(sql);
                int res = stm.executeUpdate(sql);
                listAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onDelete() {
        int i = (tblTortak.getSelectionModel().getSelectedIndex()+1);
        if ( i!= -1) {
            try {
                int res = stm.executeUpdate("delete from rendeles where raz=" + i);
                listAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}