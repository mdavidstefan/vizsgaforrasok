package com.example.levelek;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    @FXML private ComboBox cmbxFelado;
    @FXML private ComboBox cmbxCimzett;

    public class Levelsor {
        public String felado;
        public String cimzett;
        public String idopont;
        public int hossz;

        public Levelsor(String felado, String cimzett, String idopont, int hossz) {
            this.felado = felado;
            this.cimzett = cimzett;
            this.idopont = idopont;
            this.hossz = hossz;
        }

        public String getFelado() { return felado; }
        public String getCimzett() { return cimzett; }
        public String getIdopont() { return idopont; }
        public int getHossz() { return hossz; }
    }

    private Connection con = null;
    private Statement stm = null;
    private ArrayList<Integer> cimId = new ArrayList<>();

    @FXML private TableView<Levelsor> tblLevelek;
    @FXML private TableColumn<Levelsor, String> colFelado;
    @FXML private TableColumn<Levelsor, String> colCimzett;
    @FXML private TableColumn<Levelsor, String> colIdopont;
    @FXML private TableColumn<Levelsor, Integer> colHossz;
    @FXML private TextField txIdopont;
    @FXML private TextField txHossz;

    public void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/levelek","root","");
            stm = con.createStatement();
            String sql = "select * from cimek";
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                cmbxFelado.getItems().add(res.getString("cim"));
                cmbxCimzett.getItems().add(res.getString("cim"));
                cimId.add(res.getInt("caz"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colFelado.setCellValueFactory(new PropertyValueFactory<Levelsor, String>("felado"));
        colCimzett.setCellValueFactory(new PropertyValueFactory<Levelsor, String>("cimzett"));
        colIdopont.setCellValueFactory(new PropertyValueFactory<Levelsor, String>("idopont"));
        colHossz.setCellValueFactory(new PropertyValueFactory<Levelsor, Integer>("hossz"));

        listaz();

    }

    private void listaz() {
        String sql = "select kaz, feladok.cim as felado, cimzettek.cim as cimzett, mikor as idopont, hossz from kuldott" +
                " inner join cimek as feladok on ki=feladok.caz"+
                " inner join cimek as cimzettek on kinek = cimzettek.caz";
        tblLevelek.getItems().clear();
        try {
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) tblLevelek.getItems().add(new Levelsor(res.getString("felado"), res.getString("cimzett"), res.getString("idopont"), res.getInt("hossz")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onInsertPressed() {
        int feladoId = cimId.get(cmbxFelado.getSelectionModel().getSelectedIndex());
        int cimzettId = cimId.get(cmbxCimzett.getSelectionModel().getSelectedIndex());
        String sql = String.format("insert into kuldott set ki=%d, kinek=%d, mikor='%s', hossz='%s'",
                feladoId, cimzettId, txIdopont.getText(), txHossz.getText());
        try {
            stm.executeUpdate(sql);
            listaz();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}