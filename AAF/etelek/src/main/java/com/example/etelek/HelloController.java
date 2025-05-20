package com.example.etelek;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.*;

public class HelloController {
    @FXML private ListView lvEtelek;
    @FXML private ListView lvRendelesek;

    private Connection con = null;
    private Statement stm = null;

    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etel","root","");
            stm = con.createStatement();

            ResultSet res = stm.executeQuery("select * from etelek");
            while(res.next()) { lvEtelek.getItems().add(res.getString("nev") + " (" + res.getInt("ar") + ",-Ft)"); }

            res = stm.executeQuery("select * from etelek inner join rendeles using(az) ORDER by datum");
            while(res.next()) { lvRendelesek.getItems().add(res.getString("datum") + ": " + res.getString("nev") + " (" + res.getInt("adag") + ")");}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML private void onRendelesekClick() {
        
    }
}