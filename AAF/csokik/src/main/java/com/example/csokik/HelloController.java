package com.example.csokik;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    @FXML private TableView<Csoki> tableCsokik;
    @FXML private TableColumn<Csoki, String> tcNev;
    @FXML private TableColumn<Csoki, Integer> tcSuly;
    @FXML private TableColumn<Csoki, Integer> tcKaloria;
    @FXML private TextField txNev;
    @FXML private TextField txSuly;
    @FXML private TextField txKaloria;

    public void initialize() {
        loadTable();
        tableCsokik.getSelectionModel().select(0);
        onClick();
        tcNev.setCellValueFactory(new PropertyValueFactory<Csoki, String>("nev"));
        tcSuly.setCellValueFactory(new PropertyValueFactory<Csoki, Integer>("suly"));
        tcKaloria.setCellValueFactory(new PropertyValueFactory<Csoki, Integer>("kcal"));
    }

    public class Csoki {
        public int id;
        public String nev;
        public int suly;
        public int kcal;

        public int getId() {
            return id;
        }

        public String getNev() {
            return nev;
        }

        public int getSuly() {
            return suly;
        }

        public int getKcal() {
            return kcal;
        }
    }

    Csoki[] csokik = null;

    private void loadTable() {
        try {
            String uri = "http://10.201.2.19:88/csokik/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            csokik = new Gson().fromJson(jsonString, Csoki[].class);
            tableCsokik.getItems().clear();
            for(Csoki cs : csokik) tableCsokik.getItems().add(cs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onClick() {
        int i = tableCsokik.getSelectionModel().getSelectedIndex();
        if ( i!= -1) {
            txNev.setText(csokik[i].nev);
            txSuly.setText(csokik[i].suly + "");
            txKaloria.setText(csokik[i].kcal + "");
        }
    }

    @FXML private void onHozzaad() {
        try {
            String jsonData = String.format("{ 'nev':'%s', 'suly':'%s', 'kcal':'%s' }", txNev.getText(), txSuly.getText(), txKaloria.getText()).replace("'", "\"");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://10.201.2.19:88/csoki/"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadTable();
        tableCsokik.getSelectionModel().selectLast();
        tableCsokik.scrollTo(tableCsokik.getItems().size()-1);
    }

    @FXML private void onModosit() {
        int i = tableCsokik.getSelectionModel().getSelectedIndex();
        if (i != -1) {
            try {
                String jsonData = String.format("{ 'id':'%d', 'nev':'%s', 'suly':'%s', 'kcal':'%s' }", csokik[i].id, txNev.getText(), txSuly.getText(), txKaloria.getText()).replace("'", "\"");
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://10.201.2.19:88/csoki/"))
                        .header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(jsonData))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String jsonString = response.body();
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadTable();
            tableCsokik.getSelectionModel().select(i);
        }
    }

    @FXML private void onDelete() {
        int i = tableCsokik.getSelectionModel().getSelectedIndex();
        if (i != -1) {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://10.201.2.19:88/csoki?id=" + csokik[i].id))
                        .header("Content-Type", "application/json")
                        .DELETE()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String jsonString = response.body();
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadTable();
            if(i >= tableCsokik.getItems().size()) i = tableCsokik.getItems().size()-1;
            if ( i!= -1) tableCsokik.getSelectionModel().select(i);
        }
    }
}