package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    private class Telefon {
        public String nev;
        public String telszam;

        public Telefon(String sor) {
            String[] s = sor.split("\t");
            nev = s[0];
            telszam = s[1];
        }
    }

    private ArrayList<Telefon> telefonArr = new ArrayList<>();

    private int db = 0;

    public Main() {
        //0. feladat
        betolt("telefon.txt");
        System.out.printf("0) %d telefonszám beolvasva\n", telefonArr.size());

        //1. feladat
        int i = 0;
        while (i < telefonArr.size() && !telefonArr.get(i).nev.equals("Luca")) i++;
        if (i < telefonArr.size()) System.out.printf("1) Luca telefonszáma: %s\n", telefonArr.get(i).telszam);
        else System.out.printf("1) Nincs Luca nevű személy!\n");

        //2. feladat
        int db = 0;
        for(Telefon t : telefonArr) {
            if(t.telszam.split("/")[0].contains("36-20")) db++;
        }
        System.out.printf("2) %d telefonszám van 20-as körzetben\n", db);

        //3. feladat
        TreeSet<String> korzetek = new TreeSet<>();
        for (Telefon t: telefonArr) korzetek.add(t.telszam.substring(4, 4+2));
        System.out.printf("3) %d féle körzetben vannak a telefonszámok: \n", korzetek.size());
        for (String korzet : korzetek) {
            System.out.printf("(%s)", korzet);
        }
        System.out.printf("\n");

        //3,5. feladat
        TreeMap<String, Integer> korzetDb = new TreeMap<>();
        for (Telefon t : telefonArr) {
            String kor = t.telszam.substring(4, 4+2);
            if (korzetDb.containsKey(kor)) {
                korzetDb.put(kor, korzetDb.get(kor)+1);
            }
            else korzetDb.put(kor, 1);
        }

        System.out.printf("   ");
        for(String kor : korzetDb.keySet()) System.out.printf("(%s) : %d", kor, korzetDb.get(kor));
        System.out.printf("\n");

        //4. feladat
        //System.out.printf("%s -> %d\n", telefonArr.get(0).telszam, telszum(telefonArr.get(0).telszam));
        int max = telszum(telefonArr.get(0).telszam);
        for(Telefon t : telefonArr) {
            int szum = telszum(t.telszam);
            if (szum > max) max = szum;
        }

        System.out.printf("4) A legnagyobb telefonszámjegy összeg: %d\n", max);
        for(Telefon t : telefonArr) if((telszum(t.telszam) == max)) System.out.printf(t.telszam + "\n");
    }

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            while (be.hasNextLine()) telefonArr.add(new Telefon(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (be != null) be.close();
        }
    }

    private int telszum (String tel) {
        int szum = 0;
        for (int i = 0; i < tel.length(); i++) {
            char c = tel.charAt(i); if(c >= '0' && c <= '9') szum += Integer.parseInt(c + "0"); // c - '0'
        }
        return szum;
    }

    public static void main(String[] args) {
	    new Main();
    }
}
