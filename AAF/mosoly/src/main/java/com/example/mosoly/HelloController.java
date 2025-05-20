package com.example.mosoly;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HelloController {

    @FXML private ListView<String> lvConsole;
    @FXML private ImageView ivKep1;
    @FXML private ImageView ivKep2;
    @FXML private ImageView ivKep3;
    @FXML private ImageView ivKep4;

    private ArrayList<Integer> picIds = new ArrayList<>();

    public int getRand() {
        int rand = (int)Math.round((Math.random()*8)+1);
        picIds.add(rand);
        return rand;
    }

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void initialize() {
        picIds.clear();
        ivKep1.setImage(new Image(getClass().getResourceAsStream("fej" + getRand() + ".png")));
        ivKep2.setImage(new Image(getClass().getResourceAsStream("fej" + getRand() + ".png")));
        ivKep3.setImage(new Image(getClass().getResourceAsStream("fej" + getRand() + ".png")));
        ivKep4.setImage(new Image(getClass().getResourceAsStream("fej" + getRand() + ".png")));
        lvConsole.getItems().add(String.valueOf(picIds));

        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new  httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            lvConsole.getItems().add("Szerver elindítva a 88-as porton");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Kepek {
        public int kep1;
        public int kep2;
        public int kep3;
        public int kep4;
    }

    public class Kep {
        public int x;
    }

    private class httpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange http) throws IOException {
            String method = http.getRequestMethod();
            String path = http.getRequestURI().getPath();
            String query = http.getRequestURI().getQuery();
            byte[] data = new byte[256];
            int length = http.getRequestBody().read(data);
            String body = length != -1 ? new String(data, 0, length, "utf-8") : null;
            String ip = http.getRemoteAddress().getHostString();
            String response = "This is the response (" + ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body + ")";

            if(method.equals("GET") && path.equals("/")) response="<h1>Mosoly (Java) v1.0.0</h1>";
            if(method.equals("GET") && path.equals("/allapot")) {
                response = String.valueOf(picIds);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            if(method.equals("POST") && path.equals("/kepek")) {
                Kepek kepek = new Gson().fromJson(body, Kepek.class);
                ivKep1.setImage(new Image(getClass().getResourceAsStream("fej" + kepek.kep1 + ".png")));
                ivKep2.setImage(new Image(getClass().getResourceAsStream("fej" + kepek.kep2 + ".png")));
                ivKep3.setImage(new Image(getClass().getResourceAsStream("fej" + kepek.kep3 + ".png")));
                ivKep4.setImage(new Image(getClass().getResourceAsStream("fej" + kepek.kep4 + ".png")));
                response = kepek.toString();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            if(method.equals("PATCH") && path.equals("/kep1")) {
                Kep kep = new Gson().fromJson(body, Kep.class);
                ivKep1.setImage(new Image(getClass().getResourceAsStream("fej" + kep.x + ".png")));
                response = kep.toString();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }if(method.equals("PATCH") && path.equals("/kep2")) {
                Kep kep = new Gson().fromJson(body, Kep.class);
                ivKep2.setImage(new Image(getClass().getResourceAsStream("fej" + kep.x + ".png")));
                response = kep.toString();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }if(method.equals("PATCH") && path.equals("/kep3")) {
                Kep kep = new Gson().fromJson(body, Kep.class);
                ivKep3.setImage(new Image(getClass().getResourceAsStream("fej" + kep.x + ".png")));
                response = kep.toString();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }if(method.equals("PATCH") && path.equals("/kep4")) {
                Kep kep = new Gson().fromJson(body, Kep.class);
                ivKep4.setImage(new Image(getClass().getResourceAsStream("fej" + kep.x + ".png")));
                response = kep.toString();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            http.sendResponseHeaders(200, response.getBytes("utf-8").length);
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}