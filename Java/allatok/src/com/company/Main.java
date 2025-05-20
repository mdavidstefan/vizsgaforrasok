package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public class Allat {
        String allatfaj;
        int magassag;
        int suly;
        int eletkor;

        public Allat (String sor) {
            String [] s = sor.split(";");
            allatfaj = s[0];
            magassag = Integer.parseInt(s[1]);
            suly = Integer.parseInt(s[2]);
            eletkor = Integer.parseInt(s[3]);

        }
    }

    public Main() {
        // 0. feladat
        beolvas("allatok.csv");
        System.out.printf(" 0) Összesen %d féle állatfajta adata beolvasva\n", allatok.size());

        // 1. feladat
        Allat var = allatok.get(0);
        for (Allat a : allatok) if (a.magassag > var.magassag) var = a;
        System.out.printf("1) A legmagasabb állatfajta: %s, %dcm\n", var.allatfaj, var.magassag);

        // 2. feladat
        int db = 0; float atlag = 0;
        for (Allat a : allatok) if (a.suly > 20) {db++; atlag += a.eletkor;}
        atlag /= db;
        System.out.printf("2) A húsz kilónál nehezebb fajták átlagéletkora: %.02f év\n", atlag);

        // 3. feladat
        int atlagsuly = 0;
        for (Allat a : allatok) atlagsuly += a.suly;
        atlagsuly /= allatok.size();

        int smallestDiff = Math.abs(atlagsuly - allatok.get(0).suly);
        Allat legkozelebbi = allatok.get(0);

        for (Allat a : allatok) {
            int currentDiff = Math.abs(atlagsuly - a.suly);
            if (currentDiff < smallestDiff) {
                smallestDiff = currentDiff;
                legkozelebbi = a;
            }
        }
        System.out.printf("3) Az átlagsúlyhoz (%dkg) legközelebbi fajta: %s (%dkg)\n", atlagsuly,legkozelebbi.allatfaj, legkozelebbi.suly);

        // 4. feladat
        System.out.println("4) Kettős betű van a fajta nevében:");
        for (Allat a : allatok) {
            char [] charArr = a.allatfaj.toCharArray();
            for (int i = 0; i < charArr.length - 1; i++) {
                for (int j = i + 1; j < charArr.length; j++) {
                    if (charArr[i] == charArr[j]) {
                        System.out.printf("\t* %s\n", a.allatfaj);
                    }
                }
            }
        }

        // félig szar

        // 5. feladat

        // 6. feladat

        // 7. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("kicsi.csv"), "utf-8");
            for (Allat a : allatok) if (a.magassag < 100) writer.printf("%s;%d;%d;%d\n", a.allatfaj, a.magassag, a.suly, a.eletkor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }

    }

    ArrayList<Allat> allatok = new ArrayList<>();

    private void beolvas (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) allatok.add(new Allat(be.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( be != null) be.close();
        }
    }

    public static void main(String[] args) {
	    new Main();
    }
}
