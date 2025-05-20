package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public class Adat {
        String orszag;
        String rovidites;
        int o_lakossag;
        String fovaros;
        int f_lakossag;

        public Adat (String sor) {
            String [] s = sor.split(";");
            orszag = s[0];
            rovidites = s[1];
            o_lakossag = Integer.parseInt(s[2]);
            fovaros = s[3];
            f_lakossag = Integer.parseInt(s[4]);
        }
    }

    public Main() {
        // 0. feladat
        betolt("fovaros.csv");
        System.out.printf("0) Összesen %d ország adata lett beolvasva.\n", adatok.size());

        // 1. feladat
        Adat legnepesebb = adatok.get(0);
        for (Adat a : adatok) { if (a.o_lakossag > legnepesebb.o_lakossag) legnepesebb = a; }
        System.out.printf("1) Az ország, ahol a legtöbben élnek: %s, %,d fő\n", legnepesebb.orszag, legnepesebb.o_lakossag);

        adatok.sort( (a, b) -> b.o_lakossag - a.o_lakossag);
        System.out.printf("   A második legnépesebb ország: %s, %,d fő\n", adatok.get(1).orszag, adatok.get(1).o_lakossag);

        // 2. feladat
        int kevesebb = 0, bplakossag = 0;
        for (Adat a : adatok) if (a.fovaros.equals("Budapest")) bplakossag+=a.f_lakossag;
        for (Adat a : adatok)  if (a.f_lakossag < bplakossag) kevesebb++;
        System.out.printf("2) Összesen %d fővárosban élnek kevesebben, mint Budapesten!\n", kevesebb);

        // 3. feladat
        System.out.printf("3) Országjel, amiben szerepel 'C' betű: ");
        ArrayList<String> roviditesek = new ArrayList<>();
        for (Adat a : adatok) if(a.rovidites.contains("C")) roviditesek.add(a.rovidites);
        roviditesek.sort( (a, b) -> a.compareTo(b));
        System.out.printf("%s", String.join(", ", roviditesek));
        System.out.printf("\n");

        // 4. feladat
        int ossz = 0;
        for (Adat a : adatok) if(a.o_lakossag < 20000000) ossz+= a.f_lakossag;
        System.out.printf("4) A 20 millió főnél kisebb országok fővárosainak össznépessége: %,d fő\n", ossz);

        // 5. feladat
        System.out.printf("5) Fővárosok népesség szerint csoportosítva (5 millió fő):\n");
        Map<Integer, Integer> fovarosok = new HashMap<>();
        for (Adat a : adatok )
        {
            int lakossagi_kategoria = a.f_lakossag / 5000000;
            if (fovarosok.containsKey(lakossagi_kategoria))
            {
                fovarosok.put(lakossagi_kategoria, fovarosok.get(lakossagi_kategoria)+1);
            }else
            {
                fovarosok.put(lakossagi_kategoria, 1);
            }
        }

        for (Integer k : fovarosok.keySet())
        {
            System.out.printf("%,d - %,d: %d\n", k*5000000, (k+1)*5000000, fovarosok.get(k));
        }


        // 6. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("nagyok.txt"), "utf-8");
            for (Adat a : adatok) if (a.o_lakossag > 200000000) writer.printf("%s (%d millió)\n", a.orszag, (a.o_lakossag / 1000000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("6) Nagy népességű országok a nagyok.txt fájlban!");
            if (writer != null) writer.close();
        }

    }

    public ArrayList<Adat> adatok = new ArrayList<>();

    public void betolt (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) adatok.add(new Adat(be.nextLine()));
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
