package com.example.iskola;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    @FXML private ComboBox<String> cmbxOsztaly;
    @FXML private TableView<Diak> tblDiakok;
    @FXML private TableColumn<Diak, String> tcNev;
    @FXML private TableColumn<Diak, String> tcOsztaly;
    @FXML private TableColumn<Diak, String> tcSzulnap;
    @FXML private CheckBox chbxNev;
    @FXML private CheckBox chbxOsztaly;
    @FXML private CheckBox chbxSzulnap;
    @FXML private TextField txNev;
    @FXML private TextField txSzulnap;

    public class Diak {
        public String nev;
        public String osztaly;
        public String szul;

        public Diak(String nev, String osztaly, String szul) {
            this.nev = nev;
            this.osztaly = osztaly;
            this.szul = szul;
        }

        public String getNev() { return nev; }
        public String getOsztaly() { return osztaly; }
        public String getSzul() { return szul; }
    }

    private Connection con = null;
    private Statement stm = null;

    private ArrayList<Integer> diakId = new ArrayList<>();
    private ArrayList<Integer> osztalyId = new ArrayList<>();

    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iskola","root","");
            stm = con.createStatement();
            ResultSet res = stm.executeQuery("select * from osztaly");
            while(res.next()) { cmbxOsztaly.getItems().add(res.getString("osztaly")); osztalyId.add(res.getInt("oaz")); }
            cmbxOsztaly.getSelectionModel().select(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tcNev.setCellValueFactory(new PropertyValueFactory<Diak, String>("nev")); tcNev.setStyle("-fx-alignment: CENTER");
        tcOsztaly.setCellValueFactory(new PropertyValueFactory<Diak, String>("osztaly")); tcOsztaly.setStyle("-fx-alignment: CENTER");
        tcSzulnap.setCellValueFactory(new PropertyValueFactory<Diak, String>("szul")); tcSzulnap.setStyle("-fx-alignment: CENTER");
        String sql = "select * from tanulo inner join osztaly on tanulo.oaz = osztaly.oaz";
        listAll(sql);
    }

    private void listAll(String sql) {
        tblDiakok.getItems().clear();
        diakId.clear();
        try {
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                tblDiakok.getItems().add(new Diak(res.getString("nev"), res.getString("osztaly"), res.getString("szul")));
                diakId.add(res.getInt("taz"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onKereses() {
        String sql = "select * from tanulo inner join osztaly on tanulo.oaz = osztaly.oaz";
        if(chbxNev.isSelected() && txNev.getText() != "") sql += " where nev like '%" + txNev.getText() + "%'";
        if(chbxNev.isSelected() && chbxOsztaly.isSelected()) sql+= " and osztaly = '" + cmbxOsztaly.getSelectionModel().getSelectedItem() + "'";
        if(chbxNev.isSelected() && chbxOsztaly.isSelected() && chbxSzulnap.isSelected() && txSzulnap.getText() != "") sql += " and szul like '%" + txSzulnap.getText() + "%'";

        if(chbxOsztaly.isSelected() && !(chbxNev.isSelected())) sql += " where osztaly = '" + cmbxOsztaly.getSelectionModel().getSelectedItem() + "'";
        if(chbxOsztaly.isSelected() && chbxSzulnap.isSelected() && chbxSzulnap.getText() != "") sql += " and szul like '%" + txSzulnap.getText() + "%'";

        if(chbxSzulnap.isSelected() && txSzulnap.getText() != "") sql += " where szul like '%" + txSzulnap.getText() + "'";
        if(chbxSzulnap.isSelected() && chbxNev.isSelected() && txNev.getText() != "") sql += " and szul like '%" + txSzulnap.getText() + "%'";
        System.out.println(sql);
        listAll(sql);
    }

    @FXML private void onExport() {

    }

    @FXML private void onNevjegy() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Névjegy");
        info.setHeaderText(null);
        info.setContentText("Iskola v1.0.0\n(C) 2024");
        info.showAndWait();
    }

    @FXML private void onKilepes() {
        Platform.exit();
    }
}