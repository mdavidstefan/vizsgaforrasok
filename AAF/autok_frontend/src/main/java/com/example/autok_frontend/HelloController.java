package com.example.autok_frontend;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    public class Marka {
        String marka;
    }

    public class Adat {
        public String nev;
        public int magas;
        public Adat(String nev, int magas) {
            this.nev = nev; this.magas = magas;
        }
    }

    public class Auto {
        public String marka;
        public String tipus;
        public int ev;
        public String url;

        public String getMarka() {
            return marka;
        }

        public String getTipus() {
            return tipus;
        }

        public int getEv() {
            return ev;
        }

        public String getUrl() {
            return url;
        }
    }

    @FXML private TableView<Auto> tvAutok;
    @FXML private TableColumn<Auto, String> tcMarka;
    @FXML private TableColumn<Auto, String> tcTipus;
    @FXML private TableColumn<Auto, Integer> tcEvjarat;
    @FXML private TableColumn<Auto, String> tcUrl;
    @FXML private ComboBox<String> cmbxMarkak;

    public void initialize() {
        tcMarka.setCellValueFactory(new PropertyValueFactory<>("marka"));
        tcTipus.setCellValueFactory(new PropertyValueFactory<>("tipus"));
        tcEvjarat.setCellValueFactory(new PropertyValueFactory<>("ev"));
        tcUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        try {
            String uri = "http://localhost:88/markak";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            Marka[] markak = new Gson().fromJson(jsonString, Marka[].class);
            cmbxMarkak.getItems().add("Mind"); cmbxMarkak.getSelectionModel().select(0);
            for( Marka x : markak ) cmbxMarkak.getItems().add(x.marka);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaz();
        //Adat a = new Adat("Béla", 165);
        //String jsonString = new Gson().toJson(a);
        //System.out.println(jsonString);
        //Adat zoli = new Gson().fromJson("{\"nev\":\"Zoli\", \"magas\":177}", Adat.class);
        //System.out.println(zoli.nev + ", " + zoli.magas);
    }

    @FXML private void listaz() {
        try {
            String uri = "http://localhost:88/autok";
            if(!cmbxMarkak.getSelectionModel().getSelectedItem().equals("Mind")) uri += "?marka=" + cmbxMarkak.getSelectionModel().getSelectedItem();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            Auto[] autok = new Gson().fromJson(jsonString, Auto[].class);
            tvAutok.getItems().clear();
            tvAutok.getItems().addAll(autok);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}