package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public class Film {
        String cim;
        int ev;
        int hossz;
        float pont;

        public Film (String sor) {
            String [] s = sor.split(";");
            cim = s[0];
            ev = Integer.parseInt(s[1]);
            hossz = Integer.parseInt(s[2]);
            pont = Float.parseFloat(s[3]);
        }
    }

    private ArrayList<Film> filmek = new ArrayList<>();
    
    public Main() {
        //0. feladat
        loadFile("filmek.csv");
        System.out.printf("0) %d film adata beolvasva\n", filmek.size());

        //1., 2., 3. feladat
        float sum = 0;
        Film leghosszabb = filmek.get(0);
        Film legregebbi = filmek.get(0);
        for(Film f : filmek) {
            if (f.hossz > leghosszabb.hossz) {
                leghosszabb = f;
            }
            if (f.ev < legregebbi.ev) {
                legregebbi = f;
            }
            sum += f.pont;
        }
        System.out.printf("1) Leghosszabb film: %s (%d perc)\n", leghosszabb.cim, leghosszabb.hossz);
        System.out.printf("2) Legrégebbi film: %s (%d)\n", legregebbi.cim, legregebbi.ev);
        System.out.printf("3) Átlagpontszám: %.1f\n", (sum/ filmek.size()));

        //4. feladat
        System.out.printf("4) Véletlenszerűen választott hat film:\n");
        for( int i = 1; i < 7; i++) {
            int random = (int)(Math.random() * filmek.size());
            System.out.printf("\t%d. %s\n", i, filmek.get(random).cim);
        }

        //5. feladat
        ArrayList<String> evtizedek = new ArrayList<>();
        for (Film f : filmek) {
            String evtized = Integer.toString(f.ev);
            evtized = evtized.substring(0, 3);
            if( !evtizedek.contains(evtized + "x")) {
                evtizedek.add(evtized + "x");
            }
        }
        int random = (int)(Math.random() * evtizedek.size());
        String randomEvtized = evtizedek.get(random).substring(0, evtizedek.get(random).length() - 1);
        System.out.printf("5) Megjelenés évtizedei: %s\n", evtizedek);
        System.out.printf("Ezek közül egy véletlen évtized: %s, filmjei:\n", randomEvtized + "x");
        for (Film f: filmek) {
            String evtized = Integer.toString(f.ev);
            if(evtized.substring(0, 3).equals(randomEvtized)) {
                System.out.printf("\t- %s (%d)\n", f.cim, f.ev);
            }
        }

    }

    public void loadFile (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev));
            while(be.hasNextLine()) {
                filmek.add(new Film(be.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
	// write your code here
        new Main();
    }
}
