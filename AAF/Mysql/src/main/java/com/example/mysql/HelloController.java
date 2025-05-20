package com.example.mysql;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    @FXML private ListView<String> lsLista;
    @FXML private CheckBox cbCsokkeno;
    @FXML private TextField txCim;

    private Connection con = null;
    private Statement stm = null;

    private ArrayList<Integer> idLista = new ArrayList<>();

    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mozi", "root", "");
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onNincsClick();
    }
        @FXML private void onNincsClick() { lekerdez("select * from filmek"); }
        @FXML private void onCimClick() { lekerdez("select * from filmek order by cim"); }
        @FXML private void onEvClick() { lekerdez("select * from filmek order by ev"); }
        @FXML private void onHosszClick() { lekerdez("select * from filmek order by hossz"); }
        @FXML private void onImdbClick() { lekerdez("select * from filmek order by imdb"); }

        private void lekerdez(String sql) {
            ResultSet res = null;
            if(cbCsokkeno.isSelected() && sql.contains("order by")) sql += " desc";
            lsLista.getItems().clear();
            idLista.clear();
            try {
                res = stm.executeQuery(sql);
                while(res.next()) {
                    lsLista.getItems().add(String.format("%s (%d): %d perc [%.1f]", res.getString("cim"), res.getInt("ev"), res.getInt("hossz"), res.getFloat("imdb")));
                    idLista.add(res.getInt("faz"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @FXML private void onListaPressed() {
            int i = lsLista.getSelectionModel().getSelectedIndex();
            if (i != -1) {
                System.out.printf("%d ", idLista.get(i));
                String sql = "select * from filmek where faz=" + idLista.get(i);
                ResultSet res = null;
                try {
                    res = stm.executeQuery(sql);
                    res.next();
                    txCim.setText(res.getString("cim"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}