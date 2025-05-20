package com.example.uzenetek;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HelloController {

    @FXML private ListView<String> lvConsole;
    @FXML private ListView<String> lvUzenetek;

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void initialize() {
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new  httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lvConsole.getItems().add("Szerver elindult a 88-as porton");
    }

    public class Uzenet {
        int id;
        String felado;
        String uzenet;
    }

    private ArrayList<Uzenet> uzenettomb = new ArrayList<>();

    private void list() {
        lvUzenetek.getItems().clear();
        for(Uzenet u : uzenettomb) {
            lvUzenetek.getItems().add("#" + u.id + " - " + u.felado + ": " + u.uzenet);
        }
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

            if(method.equals("GET") && path.equals("/")) response = "<meta charset='UTF-8'><h1>Üzenetek (Java) v1.0.0</h1>";
            if(method.equals("GET") && path.equals("/uzenetek")) {
                response = new Gson().toJson(uzenettomb);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            if(method.equals("POST") && path.equals("/uzenet")) {
                Uzenet uzenet = new Gson().fromJson(body, Uzenet.class);
                uzenettomb.add(uzenet);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                response = new Gson().toJson(uzenet.id + uzenet.felado + uzenet.uzenet);
                list();
            }

            if(method.equals("PATCH") && path.equals("/uzenet")) {
                Uzenet adat = new Gson().fromJson(body, Uzenet.class);
                for(Uzenet u : uzenettomb) {
                    if(u.id == adat.id) {
                        u.felado = adat.felado; u.uzenet = adat.uzenet;
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                response = new Gson().toJson(adat);
                list();
            }
            if(method.equals("DELETE") && path.equals("/uzenet?id=")) {
                Uzenet adat = new Gson().fromJson(body, Uzenet.class);
                for(Uzenet u : uzenettomb) {
                    if(u.id == adat.id) {
                        uzenettomb.remove(u);
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                response = new Gson().toJson(adat.id);
                list();
            }
            // Adat adat = new Gson().fromJson(body, Adat.class);
            // response = new Gson().toJson(adat);
            // http.getResponseHeaders().add("Content-Type", "application/json");
            http.sendResponseHeaders(200, response.getBytes("utf-8").length);
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}