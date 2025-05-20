package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public class Adat {
        int year;
        int races;
        int wins;
        int podiums;
        int poles;
        int fastests;

        public Adat ( String sor ) {
            String [] s = sor.split("\t");
            year = Integer.parseInt(s[0]);
            races = Integer.parseInt(s[1]);
            wins = Integer.parseInt(s[2]);
            podiums = Integer.parseInt(s[3]);
            poles = Integer.parseInt(s[4]);
            fastests = Integer.parseInt(s[5]);
        }
    }

    private ArrayList<Adat> adatArr = new ArrayList<>();

    private void loadFile (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) {
                adatArr.add(new Adat(be.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    public Main() {
        // 2. feladat
        loadFile("jackie.txt");

        // 3. feladat
        System.out.printf("3. feladat: %d\n", adatArr.size());

        // 4. feladat
        Adat var = adatArr.get(0);
        for (Adat a : adatArr) { if (a.races > var.races ) var = a;}
        System.out.printf("4. feladat: %d\n", var.year);

        // 5. feladat
        TreeMap<String, Integer> evtizedek = new TreeMap<>();
        for (Adat a : adatArr) {
            String evtized = Integer.toString(a.year).substring(0, 3);
            if (evtizedek.containsKey(evtized)) { evtizedek.put(evtized, evtizedek.get(evtized) + a.wins); }
            else { evtizedek.put(evtized, a.wins); }
        }
        System.out.println("5. feladat:");
        evtizedek.forEach((key, value) -> System.out.printf("\t%s-es évek: %d megnyert verseny\n", key.charAt(2)+"0", value));

        // 6. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("jackie.html"));
            writer.printf("<!doctype html>\n<html>\n<head></head>\n<style>td { border: 1px solid black }</style>\n<body><h1>Jackie Stewart</h1>\n<table>");
            for (Adat a : adatArr) {
                writer.printf("<tr><td>%d</td><td>%d</td><td>%d</td></tr>\n", a.year, a.races, a.wins);
            }
            writer.printf("</table>\n</body>\n</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                System.out.println("6. feladat: jackie.html"); writer.close();}
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
