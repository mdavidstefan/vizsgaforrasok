package com.example.backend;

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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HelloController {

    @FXML private ListView<String> lvConsole;

    private HttpServer server = null;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    private class httpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange http) throws IOException {
            String method = http.getRequestMethod();
            String uri = http.getRequestURI().toString();
            String ip = http.getLocalAddress().getHostName();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvConsole.getItems().add(ip + " " + method + " " + uri);
                }
            });
            String path = http.getRequestURI().getPath();
            String query = http.getRequestURI().getQuery();
            String response = "This is the response (" + method + " " + path + " ? " + query + ")";
            if(method.equals("GET") && path.equals("/csokik")) {
                response = "[";
                for(int i=0; i<5; i++) {
                    Csoki csoki = csokik.get(i);
                    response += String.format("{'nev':'%s, 'suly':'%d', 'kcal':'%d'}", csoki.nev, csoki.suly, csoki.kcal).replace("'", "\"");
                    if ( i!= 5-1) response += ",";
                }
            }
            if(method.equals("GET") && path.equals("/csoki") && query.contains("id=")) {
                int id = Integer.parseInt(query.split("=")[1]);
                Csoki csoki = csokik.get(id);
                response = String.format("{'nev':'%s, 'suly':'%d', 'kcal':'%d'}", csoki.nev, csoki.suly, csoki.kcal).replace("'", "\"");
                http.getResponseHeaders().add("Content-Type", "application/json");
                System.out.println(response);
            }

            System.out.println(response.length());
            http.sendResponseHeaders(200, response.length());
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public void initialize() {
        betolt("csokik.csv");
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 88), 0);
            server.createContext("/", new  httpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            lvConsole.getItems().add("Szerver elindult a 88-as porton");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Csoki {
        public String nev;
        public int suly;
        public int kcal;

        public Csoki(String sor) {
            nev = sor.split(";")[0];
            suly = Integer.parseInt(sor.split(";")[1]);
            kcal = Integer.parseInt(sor.split(";")[2]);
        }
    }

    private ArrayList<Csoki> csokik = new ArrayList<>();

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            while(be.hasNextLine()) csokik.add(new Csoki(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( be != null) be.close();
        }
    }

}