package com.company;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public class Etel {
        String datum;
        String etelnev;
        int adag;
        int adagAr;

        public Etel (String sor) {
            String [] s = sor.split(";");
            datum = s[0];
            etelnev = s[1];
            adag = Integer.parseInt(s[2]);
            adagAr = Integer.parseInt(s[3]);
        }
    }

    private ArrayList<Etel> etelek = new ArrayList<>();

    public Main() {
        //0. feladat
        loadFile("etel.csv");
        System.out.printf("0) %d étel adata beolvasva\n", etelek.size());

        //1. feladat
        Etel legkorabbi = etelek.get(0);
        for(Etel e : etelek) {
            if(e.datum.compareTo(legkorabbi.datum) < 0) {
                legkorabbi = e;
            }
        }
        System.out.printf("1) A legelső rendelés %d db %s (%s)\n", legkorabbi.adag, legkorabbi.etelnev, legkorabbi.datum);


        //2. feladat
        int legdragabbDb = 0;
        Etel legdragabbEtel = etelek.get(0);
        for( Etel e: etelek) {
            if ( e.adagAr > legdragabbEtel.adagAr) legdragabbEtel = e;
            if(e.etelnev.equals(legdragabbEtel.etelnev)) legdragabbDb += e.adag;
        }
        System.out.printf("2) A legdrágább étel: %s (%d,-Ft)\n", legdragabbEtel.etelnev, legdragabbEtel.adagAr);
        System.out.printf("Ebből %d adagot adtak el, ami összesen %d,-Ft bevétel\n", legdragabbDb, legdragabbDb*legdragabbEtel.adagAr);

        //3. feladat
        int [] honapDb = new int [13];
        for(Etel e: etelek) {
            honapDb [Integer.parseInt(e.datum.split("\\.")[0])]++;
        }

        //4. feladat
        TreeMap<String , Integer> rendelesek = new TreeMap<>();
        for (Etel e : etelek)
        {
            if (rendelesek.containsKey(e.etelnev))
            {
                rendelesek.put(e.etelnev, rendelesek.get(e.etelnev)+e.adag);
            }else
            {
                rendelesek.put(e.etelnev, e.adag);
            }
        }

        System.out.printf("4) Összesen %d féle ételt rendeltek\n", rendelesek.size());
        System.out.printf("Legkevesebbet %s rendeltek (%d adag)", rendelesek.lastKey(), rendelesek.get(rendelesek.lastKey()));

    }

    public void loadFile(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev));
            be.nextLine();
            while(be.hasNextLine()) {
                etelek.add(new Etel(be.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( be != null) be.close();
        }
    }

    public static void main(String[] args) {
	// write your code here
        new Main();
    }
}
