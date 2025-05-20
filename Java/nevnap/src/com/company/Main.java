package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private final ArrayList<Nev> nevek = new ArrayList<>();


    public Main() {
        // 0. feladat
        betolt("nevnap.csv");
        System.out.printf("0) Összesen %d név beolvasva.\n", nevek.size());
        System.out.printf("A tizedik név: %s, akinek %d névnapja van.\n", nevek.get(9).nev, nevek.get(9).napok.size());

        // 1. feladat
        System.out.printf("1) János névnapjai: ");
        for (Nev n : nevek) {
            if (n.nev.equals("János")) {
                System.out.printf("%s", String.join(", ", n.napok));
            }
        }
        System.out.printf("\n");

        // 2. feladat
        System.out.printf("2) Április elsején van névnapja:");
        for (Nev n : nevek) {
            for (int i = 0; i < n.napok.size(); i++) {
                if (n.napok.get(i).equals("0401")) System.out.printf("%s ", n.nev);
            }
        }
        System.out.printf("\n");

        // 3. feladat
        Nev legtobb = nevek.get(0);
        for (Nev n : nevek) if (n.napok.size() > legtobb.napok.size()) legtobb = n;
        System.out.printf("3) Legtöbb névnapja (%d) %s nevűeknek van!\n", legtobb.napok.size(), legtobb.nev);
        System.out.printf("   De ugyanennyi névnapja van még: ");
        for (Nev n : nevek) {
            if (n.nev.equals(legtobb.nev)) {
                continue;
            }
            if (n.napok.size() == legtobb.napok.size()) System.out.printf("%s ", n.nev);
        }
        System.out.printf("\n");

        // 4. feladat
        int osszesen = 0;
        for (Nev n : nevek) {
            osszesen += n.napok.size();
        }
        System.out.printf("4) Összesen %d nap van a nevekhez rendelve.\n", osszesen);

        // 5. feladat
        TreeMap<String, Integer> stat = new TreeMap<>();
        for (Nev n : nevek) {
            for (String nap : n.napok) {
                String ho = nap.substring(0, 2);
                if (!stat.containsKey(ho)) stat.put(ho, 1);
                else { int eddig = stat.get(ho); stat.put(ho, eddig+1); }
            }
        }
        String[] honevek = { "", "Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};
        System.out.printf("5) Az egyes hónapokban tartott névnapok száma: \n");
        for (String key: stat.keySet()) {
            System.out.printf("   %s: %d\n", honevek[Integer.parseInt(key)], stat.get(key));
        }

        // 6. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("elseje.txt"), StandardCharsets.UTF_8);
            for (Nev n : nevek) {
                for (String nap : n.napok) {
                    if (nap.substring(2, 4).equals("01")) writer.write(nap + ": " + n.nev + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
                System.out.printf("6) Elsejei névnapok kiírva az elseje.txt fájlba.");
            }
        }


    }

    public static void main(String[] args) {
        new Main();
    }

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev));
            while (be.hasNextLine()) nevek.add(new Nev(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    public class Nev {
        public String nev;
        public ArrayList<String> napok = new ArrayList<>();

        public Nev(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            for (int i = 1; i < s.length; i++) {
                napok.add(s[i]);
            }
        }
    }
}
