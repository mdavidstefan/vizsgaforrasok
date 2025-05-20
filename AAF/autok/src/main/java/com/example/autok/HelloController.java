package com.example.autok;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HelloController {

    @FXML private ListView<String> lvConsole;
    @FXML private ListView<String> lvAutok;

    public class Auto {
        public int id;
        public String tipus;
        public int suly;
        public int loero;

        public Auto(String sor) {
            String [] s = sor.split(";");
            id = Integer.parseInt(s[0]);
            tipus = s[1];
            suly = Integer.parseInt(s[2]);
            loero = Integer.parseInt(s[3]);
        }

        public String toString() {
            return String.format("#%d : %s (%d kg, %d LE)", id, tipus, suly, loero);
        }
    }

    private ArrayList<Auto> autok = new ArrayList<>();
    private int nextId = 0;

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void initialize() {
        betolt("autok.csv");
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new  httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                }
            });
            String response = "This is the response (" + ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body + ")";
            if(method.equals("GET") && path.equals("/")) response="<meta charset='UTF-8'><h1>Autók (Java) v1.0.0</h1>";
            if (method.equals("GET") && path.equals("/autok")) {
                response = new Gson().toJson(autok);
                http.getResponseHeaders().add("Content-Type", "application/json");
            }
            if(method.equals("POST") && path.equals("/auto")) {
                Auto auto = new Gson().fromJson(body, Auto.class);
                auto.id = nextId; nextId++;
                autok.add(auto);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        listaz();
                    }
                });
                response = new Gson().toJson(auto);
                http.getResponseHeaders().add("Content-Type", "application/json");
            }
            if(method.equals("PUT") && path.equals("/auto")) {
                Auto auto = new Gson().fromJson(body, Auto.class);
                int i = 0; while (i < autok.size() && autok.get(i).id != auto.id) i++;
                if( i < autok.size()) {
                    autok.set(i, auto);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            listaz();
                        }
                    });
                    response = new Gson().toJson(auto);
                    http.getResponseHeaders().add("Content-Type", "application/json");
                } else response = "Hibás ID!";

            }if(method.equals("DELETE") && path.equals("/auto") && query.contains("id=")) {
                int id = Integer.parseInt(query.split("=")[1]);
                int i = 0; while (i < autok.size() && autok.get(i).id != auto.id) i++;
                if( i < autok.size()) {
                    autok.remove(i);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            listaz();
                        }
                    });
                    response = "{ \"id\":" + id + " }";
                    http.getResponseHeaders().add("Content-Type", "application/json");
                } else response = "Hibás ID!";
            }

            http.sendResponseHeaders(200, response.getBytes("utf-8").length);
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while(be.hasNextLine()) autok.add(new Auto(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }

        for(Auto auto: autok) {
            lvAutok.getItems().add(auto.toString());
            if (auto.id > nextId) nextId = auto.id;
        }
        nextId++;
        lvConsole.getItems().add("Beolvasott autók száma: " + autok.size());
        lvConsole.getItems().add("Következő autó azonosítója: " + nextId);
        listaz();
    }

    private void listaz() {
        lvAutok.getItems().clear();
        for(Auto auto: autok) {
            lvAutok.getItems().add(auto.toString());
        }
    }
}