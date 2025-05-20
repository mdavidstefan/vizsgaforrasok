package com.example.potter;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class HelloController {

    @FXML private ListView<String> lvCharacters;
    @FXML private ImageView characterImg;
    @FXML private Label lbIndex;
    @FXML private Label lbName;

    public class Character
    {
        public String image;
        public int index;
        public String fullName;

    }

    ArrayList<Character> charactersArray = new ArrayList<>();

    public void initialize() throws Exception
    {
        String uri = "https://potterapi-fedeperin.vercel.app/en/characters";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        Character[] car = new Gson().fromJson(jsonString, Character[].class);
        for(Character x : car) { lvCharacters.getItems().add(x.fullName+" (#"+x.index+")"); charactersArray.add(x); }
        lvCharacters.getSelectionModel().selectFirst();
        list();
    }

    @FXML private void list ()
    {
        if (lvCharacters.getSelectionModel().getSelectedIndex()!=-1)
        {
            Character selectedC = charactersArray.get(lvCharacters.getSelectionModel().getSelectedIndex());
            lbIndex.setText(selectedC.index+"");
            lbName.setText(selectedC.fullName);
            characterImg.setImage(new Image(selectedC.image));
        }
    }

    @FXML private void onRandom() {
        int randindex = (int)(Math.random()*(charactersArray.size()));
        lvCharacters.getSelectionModel().select(randindex);
        list();
    }
}
