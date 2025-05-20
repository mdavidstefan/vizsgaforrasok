package com.example.tizszam;

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

    @FXML private ListView<String> lvNumbers;
    @FXML private ListView<String> lvConsole;

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    private ArrayList<Integer> randomNumbers = new ArrayList<>();

    public void initialize() {
        for(int i = 0; i < 10; i++) {
            randomNumbers.add((int) (Math.random() * 900) + 100);
            lvNumbers.getItems().add("#" + i + " : " + randomNumbers.get(i));
        }

        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new  httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            lvConsole.getItems().add("Szerver elindult a 88-as porton");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void list() {
        lvNumbers.getItems().clear();
        for(int i : randomNumbers) lvNumbers.getItems().add("#" + i + " : " + randomNumbers.get(i));
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
            // Adat adat = new Gson().fromJson(body, Adat.class);
            // response = new Gson().toJson(adat);
            // http.getResponseHeaders().add("Content-Type", "application/json");

            if(method.equals("GET") && path.equals("/")) {
                response = "<meta charset='utf-8'><h1>Tíz szám (Java) v1.0.0</h1>";
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }

            if(method.equals("GET") && path.equals("/szamok")) {
                response = new Gson().toJson(randomNumbers);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
            }
            if(method.equals("POST") && path.equals("/szam")) {
                Integer szam = Integer.parseInt(body);
                randomNumbers.remove(0);
                randomNumbers.add(szam);
                response = new Gson().toJson(randomNumbers);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                list();
            }
            if(method.equals("PATCH") && path.equals("/csere")) {
                Integer szam = Integer.parseInt(body);
                randomNumbers.remove(0);
                randomNumbers.add(szam);
                response = new Gson().toJson(randomNumbers);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                list();
            }
            if(method.equals("DELETE") && path.equals("/szam?id=x")) {
                Integer id = Integer.parseInt(body);
                randomNumbers.remove(id);
                randomNumbers.add((int) (Math.random() * 900) + 100);
                response = new Gson().toJson(randomNumbers);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        lvConsole.getItems().add(ip + " : " + method + " " + path + " ? query: " + query + " + body: " + body);
                    }
                });
                list();
            }
            http.sendResponseHeaders(200, response.getBytes("utf-8").length);
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}