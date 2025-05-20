package com.example.szavak;

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

    @FXML
    private ListView<Szo> lvBal;
    @FXML
    private ListView<Szo> lvJobb;
    @FXML
    private ListView<String> lvConsole;

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


    public void initialize() {
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            lvConsole.getItems().add("Szerver elindult a 88-as porton");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Szo {
        public int id;
        public String szo;
    }

    private ArrayList<Szo> arrayBal;
    private ArrayList<Szo> arrayJobb;

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
            // Adat adat = new Gson().fromJson(body, Adat.class);
            // response = new Gson().toJson(adat);
            // http.getResponseHeaders().add("Content-Type", "application/json");

            if(method.equals("GET") && path.equals("/")) {
                response = "<meta charset='utf-8'><h1>Szavak (Java) v1.0.0</h1>";
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            if(method.equals("GET") && path.equals("/bal")) {
                response = new Gson().toJson(arrayBal);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }if(method.equals("GET") && path.equals("/jobb")) {
                response = new Gson().toJson(arrayJobb);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }

            if(method.equals("POST") && path.equals("/bal")) {
                String word = new Gson().fromJson(String.format("{'id': %d, 'szo':%s}", lvBal.getItems().size(), ));
            }

            http.sendResponseHeaders(200, response.getBytes("utf-8").length);
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}