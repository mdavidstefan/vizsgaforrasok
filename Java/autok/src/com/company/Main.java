package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
// 1. feladat

    // 2. feladat
    public class Auto {
        int sorszam;
        String marka;
        String modell;
        int gyEv;
        String szin;
        int eladottDb;
        int eladasAr;

        // 3. feladat
        public Auto (String sor) {
            String [] s = sor.split(";");
            sorszam = Integer.parseInt(s[0]);
            marka = s[1];
            modell = s[2];
            gyEv = Integer.parseInt(s[3]);
            szin = s[4];
            eladottDb = Integer.parseInt(s[5]);
            eladasAr = Integer.parseInt(s[6]);
        }
    }

    public Main() {
        // 4. feladat
        beolvas("autok.csv");

        // 5. feladat
        System.out.printf("5. feladat: %d autó található a listában\n", autok.size());

        // 6. feladat
        float atlag = 0;
        for (Auto a : autok) atlag += a.eladottDb;
        atlag /= autok.size();
        System.out.printf("6. feladat: Az autók esetében az átlagosan eladott darabszám %.1f\n", atlag);

        // 7. feladat
        System.out.println("7. feladat: Az elmúlt 5 évben gyártott autók:");
        for (Auto a : autok) {
            if (a.gyEv >= 2019) System.out.printf("\t- %s %s: %d\n", a.marka, a.modell, a.gyEv);
        }

        // 8. feladat
        System.out.println("8. feladat: Legsikeresebb márkák listája az eladott darabszám alapján:");
        autok.sort((a, b) -> b.eladottDb - a.eladottDb);
        for (Auto a : autok) System.out.printf("\t- %s: %d darab\n", a.marka, a.eladottDb);
    }

    private ArrayList<Auto> autok = new ArrayList<>();

    private void beolvas (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "UTF-8");
            be.nextLine();
            while (be.hasNextLine()) autok.add(new Auto(be.nextLine()));
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
