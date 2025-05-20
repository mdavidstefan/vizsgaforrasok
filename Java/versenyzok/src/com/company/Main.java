package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    ArrayList<Versenyzo> versenyzok = new ArrayList<>();

    public Main() {
        // 4. feladat
        load("versenyzok.csv");

        // 5. feladat
        System.out.printf("5. feladat: %d\n", versenyzok.size());

        // 6.feladat
        System.out.print("6. feladat: ");
        for (Versenyzo v : versenyzok) if (v.kod.equals("ALO")) System.out.printf("%s\n", v.nev);

        // 7. feladat
        System.out.println("7. feladat:");
        for (Versenyzo v : versenyzok) {
            String[] datum = v.szuletes.split("\\.");
            if (datum[1].equals("01") && datum[2].equals("01")) System.out.printf("\t%s (%s)\n", v.nev, v.szuletes);
        }

        // 8. feladat
        System.out.println("8. feladat: Kérek egy rövidítést:");
        Scanner input = new Scanner(System.in);
        String bekert = input.nextLine();
        while (!input.nextLine().equals("")) {
            bekert = input.nextLine();
        }
        Boolean vanVersenyzo = false;
        // 9. feladat
        System.out.println("9. feladat:");
        for (Versenyzo v : versenyzok) {
            if (v.kod.equals(bekert) && !bekert.equals("")) {
                vanVersenyzo = true;
                System.out.printf("\tnév:\t%s\n", v.nev);
                System.out.printf("\tnemzetiség:\t%s\n", v.nemzetiseg);
                System.out.printf("\tszületési dátum:\t%s\n", v.szuletes);
            }
        }
        if (!vanVersenyzo) {
            System.out.println("Nem található a keresett versenyző!");
        }

        // 10. feladat
        TreeMap<String, Integer> treetiz = new TreeMap<>();
        for (Versenyzo v : versenyzok) {
            if (treetiz.containsKey(v.nemzetiseg)) treetiz.put(v.nemzetiseg, treetiz.get(v.nemzetiseg) + 1);
            else treetiz.put(v.nemzetiseg, 1);
        }
        System.out.println("10. feladat:");

        ArrayList<String> nemzetisegek = new ArrayList<>();
        for (String v : treetiz.keySet()) {
            if (treetiz.get(v) > 25) {
                nemzetisegek.add(v);
            }
        }
        for (String n : nemzetisegek) {
            System.out.print(n + ", ");
        }

    }

    public static void main(String[] args) {
        new Main();
    }

    private void load(String filename) {
        Scanner be = null;
        try {
            be = new Scanner(new File(filename), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) versenyzok.add(new Versenyzo(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }

    }

    public class Versenyzo {
        String nev;
        String szuletes;
        String nemzetiseg;
        String kod;

        public Versenyzo(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            szuletes = s[1];
            nemzetiseg = s[2];
            if (s.length == 3) kod = "";
            else kod = s[3];
        }
    }
}
