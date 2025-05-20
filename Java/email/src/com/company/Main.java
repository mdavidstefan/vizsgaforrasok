package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    
    public class Email {
        public String ki;
        public String kinek;
        public String mikor;
        public int hossz;
        public int ora;
        public int perc;

        public Email (String sor) {
            String [] s = sor.split(";");
            ki = s[0];
            kinek = s[1];
            mikor = s[2];
            hossz = Integer.parseInt(s[3]);
            ora = Integer.parseInt(mikor.split(":")[0]);
            perc = Integer.parseInt(mikor.split(":")[1]);
        }
    }

    private ArrayList<Email> emailArray = new ArrayList<>();

    int count = 0;
    
    public Main() {
        //0. feladat
        readFile("email.csv");
        System.out.printf("0) %d levél beolvasva\n", emailArray.size());

        //1. feladat
        Email legkorabbi = emailArray.get(0);

        for ( Email e : emailArray) {
            if (e.mikor.compareTo(legkorabbi.mikor) < 0) {
                legkorabbi = e;
            }
        }
        System.out.printf("1) legkorábbi levél: %s -> %s (%s)\n", legkorabbi.ki, legkorabbi.kinek, legkorabbi.mikor);


        //2. feladat
        System.out.printf("2) Leghosszabb levelek:\n");
        count = emailArray.get(0).hossz;
        for(Email e : emailArray) {
            if( e.hossz > count) count = e.hossz;
        }
        for(Email e : emailArray) {
            if(e.hossz == count) {
                System.out.printf("%s -> %s (%d bájt)\n", e.ki, e.kinek, e.hossz);
            }
        }

        //3. feladat
        count = 0;
        System.out.printf("3) Saját magának küldött levelek:\n");
        for(Email e : emailArray) {
            if( e.ki.equals(e.kinek)) {
                System.out.printf("%s (%s)\n", e.ki, e.mikor);
                count++;
            }
        }
        System.out.printf("Összesen: %d db\n", count);

        //4. feladat
        TreeSet<String> feladok = new TreeSet<>();
        for(Email e : emailArray) {
            feladok.add(e.ki);
        }
        System.out.printf("4) Összesen %d feladó van a listában\n", feladok.size());

        try {
            PrintWriter writer = new PrintWriter(new File("cimek.txt"));
            for(String felado : feladok) writer.write(felado);

        } catch (Exception e) {
            e.printStackTrace();
        }

        count = 0;
        for(String felado : feladok) {
            if(felado.split("@")[1].equals("gmail.com")) {
                count++;
            }
        }
        System.out.printf("Közülük %d darab @gmail.com\n", count);

        System.out.println("De van még: ");


    }

    private void readFile(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while ( be.hasNextLine() ) {
                emailArray.add(new Email(be.nextLine()));
            }
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
