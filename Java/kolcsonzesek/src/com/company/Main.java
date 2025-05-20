package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public class Kolcsonzes {
        String nev;
        char jAzon;
        int eOra;
        int ePerc;
        int vOra;
        int vPerc;

        public Kolcsonzes (String sor) {
            String [] s = sor.split(";");
            nev = s[0];
            jAzon = s[1].charAt(0);
            eOra = Integer.parseInt(s[2]);
            ePerc = Integer.parseInt(s[3]);
            vOra = Integer.parseInt(s[4]);
            vPerc = Integer.parseInt(s[5]);
        }
    }

    public Main() {
        // 4. feladat
        loadFile("kolcsonzesek.txt");

        // 5. feladat
        System.out.printf("5. feladat: Napi kölcsönzések száma: %d\n", kolcsonzesek.size());

        // 6. feladat
        System.out.println("6. feladat: Kérek egy nevet: ");
        Scanner input = new Scanner(System.in);
        String bekertNev = input.nextLine();
        System.out.printf("%s kölcsönzései:\n", bekertNev);
        boolean voltKolcsonzes = false;
        for (Kolcsonzes k : kolcsonzesek) {
            if (k.nev.equals(bekertNev)) { voltKolcsonzes = true; System.out.printf("%02d:%02d-%02d:%02d\n", k.eOra, k.ePerc, k.vOra, k.vPerc); }
        }
        if(!voltKolcsonzes) System.out.println("Nem volt ilyen nevű kölcsönző!");

        // 7. feladat
        System.out.println("7. feladat: Adjon megy egy időpontot óra:perc alakban: ");
        String bekertIdo = input.nextLine();
        bekertIdo = String.format("%02d:%02d", Integer.parseInt(bekertIdo.split(":")[0]), Integer.parseInt(bekertIdo.split(":")[1]));
        System.out.println("A vízen lévő járművek:");
        for (Kolcsonzes k : kolcsonzesek) {
            String elvitel = String.format("%02d:%02d", k.eOra, k.ePerc);
            String visszahozatal = String.format("%02d:%02d", k.vOra, k.vPerc);
            if(bekertIdo.compareTo(elvitel) > 0 && bekertIdo.compareTo(visszahozatal) < 0) {
                System.out.printf("\t%s-%s : %s\n", elvitel, visszahozatal, k.nev);
            }
        }

        // 8. feladat

        // 9. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("F.txt", "utf-8");
            for(Kolcsonzes k : kolcsonzesek) {
                String elvitel = String.format("%02d:%02d", k.eOra, k.ePerc);
                String visszahozatal = String.format("%02d:%02d", k.vOra, k.vPerc);
                if (Character.toString(k.jAzon).equals("F")) {
                    writer.printf("%s-%s : %s\n", elvitel, visszahozatal, k.nev);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }

        // 10. feladat
        TreeMap<Character, Integer> statisztika = new TreeMap<>();
        for (Kolcsonzes k : kolcsonzesek) {
            if (statisztika.containsKey(k.jAzon)) {
                statisztika.put(k.jAzon, statisztika.get(k.jAzon)+1);
            } else {
                statisztika.put(k.jAzon, 1);
            }
        }

    }

    public ArrayList<Kolcsonzes> kolcsonzesek = new ArrayList<>();

    public void loadFile (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) kolcsonzesek.add(new Kolcsonzes(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
