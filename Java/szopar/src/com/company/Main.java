package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public class Szo {
        String szo1;
        String szo2;

        public Szo (String sor) {
            String [] s = sor.split("-");
            szo1 = s[0];
            szo2 = s[1];
        }
    }

    public Main() {
        // 0. feladat
        betolt("szopar.txt");
        System.out.printf("0) %d szópár beolvasva\n", szavak.size());

        // 1. feladat
        ArrayList<String> egyformak = new ArrayList<>(); int azonos = 0;
        for (Szo szo : szavak) if (szo.szo1.equals(szo.szo2)) { egyformak.add(szo.szo1 + "-" + szo.szo2); azonos++; }
        System.out.printf("1) Közülük %d esetben a két szó azonos:\n", azonos);
        System.out.printf("%s", String.join(", ", egyformak));
        System.out.printf("\n");

        // 2. feladat
        TreeSet<String> szoParok = new TreeSet<>(); String leghosszabb = "";
        for (Szo szo : szavak) { szoParok.add(szo.szo1); szoParok.add(szo.szo2);  if (szo.szo1.length() > leghosszabb.length()) leghosszabb = szo.szo1; }
        System.out.printf("2) Összesen %d féle szó van a párokban (abc):\n", szoParok.size());
        System.out.printf("A leghosszabb szó: %s\n", leghosszabb);

        // 3. feladat
        String randomSzo = (String) szoParok.toArray()[(int) (Math.random() * szoParok.size())];
        System.out.printf("3) Közülük egy véletlenszerűen kiválasztott: %s\n", randomSzo);

        TreeMap<String, Integer> stat = new TreeMap<>();
        for (Szo szo : szavak) {
            if (szo.szo1.equals(randomSzo)) {
                if (!stat.containsKey(szo.szo2)) stat.put(szo.szo2, 1);
                else stat.put(szo.szo2, stat.get(szo.szo2) + 1);
            }
        }
        System.out.printf("Ez %d szóval szerepel párban (abc):\n   ", stat.size());
        for (String key : stat.keySet()) {
            System.out.printf("-%s(%dx) ", key, stat.get(key));
        }
    }

    private ArrayList<Szo> szavak = new ArrayList<>();

    private void betolt (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            while (be.hasNextLine()) szavak.add(new Szo(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
