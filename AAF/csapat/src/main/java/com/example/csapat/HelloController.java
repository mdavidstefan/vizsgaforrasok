package com.example.csapat;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    @FXML private Label lbNev;
    @FXML private Label lbEmail;
    @FXML private Label lbRegisztralt;
    @FXML private ImageView mainImg;
    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private ImageView img3;
    @FXML private Label lbNev1;
    @FXML private Label lbNev2;
    @FXML private Label lbNev3;

    public class Adat {
        public Results[] results;
    }

    public class Results {
        public Name name;
        public Registered registered;
        public String email;
        public Picture picture;
    }

    public class Name {
        public String first;
        public String last;
    }

    public class Registered {
        public String date;
    }

    public class Picture {
        public String large;
    }

    Adat current;
    Adat left;
    Adat middle;
    Adat right;

    public void initialize() {
        onKovetkezo();
    }

    @FXML private void onKovetkezo() {
        try {
            String uri = "https://randomuser.me/api/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            Adat adat = new Gson().fromJson(jsonString, Adat.class);
            mainImg.setImage(new Image(adat.results[0].picture.large));
            lbNev.setText(adat.results[0].name.first + " " + adat.results[0].name.last);
            lbEmail.setText(adat.results[0].email);
            lbRegisztralt.setText((adat.results[0].registered.date.split("T")[0].replaceAll("-", ".")));
            current = adat;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void felvesz1() { img1.setImage(new Image(current.results[0].picture.large)); lbNev1.setText(current.results[0].name.first + " " + current.results[0].name.last);}
    @FXML private void felvesz2() { img2.setImage(new Image(current.results[0].picture.large)); lbNev2.setText(current.results[0].name.first + " " + current.results[0].name.last);}
    @FXML private void felvesz3() { img3.setImage(new Image(current.results[0].picture.large)); lbNev3.setText(current.results[0].name.first + " " + current.results[0].name.last);}
}