package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private final double USD_FT = 332.87;
    private final double LE_KW = 0.735;

    public class SportCar {
        public String tipus;
        public int teljesitmeny;
        public float ar;
        public int darabszam;
        public String leiras;

        public SportCar (String sor) {
            String [] s = sor.split(";");
            tipus = s[0];
            teljesitmeny = Integer.parseInt(s[1]);
            ar = Float.parseFloat(s[2]);
            darabszam = Integer.parseInt(s[3]);
            leiras = s[4];
        }

        public String leirasRovid() {
            String [] s = leiras.split(" ");
            String rovid = "";
            int i = 0; while ( rovid.length() + s[i].length() < 50) { rovid += s[i] + " "; i++; }
            return rovid;
        }
    }

    private ArrayList<SportCar> cars = new ArrayList<>();

    public Main() {
        // 4. feladat
        betolt("topcars.txt");

        // 5. feladat
        int db5 = 0; for (SportCar car: cars) if(car.ar > 5) db5++;
        System.out.printf("5. feladat: Autótípusok száma 5 millió USD felett: %d\n", db5);

        // 6. feladat
        float osszeg = 0; int darab = 0; for (SportCar car : cars) {osszeg += car.ar * car.darabszam; darab += car.darabszam; }
        System.out.printf("6. feladat: A gyártók átlagos bevétele: %.3f millió USD\n", osszeg / darab);

        // 7. feladat
        //System.out.printf("%s\n%s", cars.get(0).leiras, cars.get(0).leirasRovid());

        // 8. feladat
        SportCar legerosebb = cars.get(0);
        for (SportCar car : cars) { if(car.teljesitmeny > legerosebb.teljesitmeny) legerosebb = car;}
        System.out.printf("8. feladat: A legerősebb autó adatai\n");
        System.out.printf("\tTípus: %s\n\tTeljesítmény: %d LE\n\tÁr: %.1f millió USD\n\tDarabszám: %d db\n\tRövid leírás: %s", legerosebb.tipus,
                    legerosebb.teljesitmeny, legerosebb.ar, legerosebb.darabszam, legerosebb.leirasRovid());

        // 9. feladat
        TreeMap<String, Integer> szavak = new TreeMap<>();
        String tiltott = "(a)(A)(az)(Az)(és)(egy)(is)(ez)(Ez)";
        for (SportCar car : cars) {
            String [] s = car.leiras.split(" ");
            for( int i = 0; i < s.length; i++) {
                if (szavak.containsKey(s[i])) szavak.put(s[i], 1);
                else szavak.put(s[i], szavak.get(s[i]) + 1);
            }
        }
        System.out.printf("9. feladat: Leírás statisztika:\n");
        for(int i = 0; i<3; i++) {
            String max = null;
            for(String key : szavak.keySet()) if(max == null || szavak.get(key) > szavak.get(max)) max = key;
            System.out.printf("\t%s - %d\n", max, szavak.get(max));
            szavak.put(max, -1);
        }

        // 10. feladat
        PrintWriter ki = null;
        try {
            ki = new PrintWriter(new File("topcars_new.txt"), "utf-8");
            ki.printf("Típus;Teljesítmény_kW;Ár_HUF;Darabszám;Leírás;\r\n");
            for (SportCar car: cars) {
                ki.printf("%s;%.0f;%.0f, %d, %s\r\n", car.tipus, car.teljesitmeny*LE_KW, car.ar * 1_000_00 * USD_FT, car.darabszam, car.leiras);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ki != null) ki.close();
        }
    }

    public void betolt (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "UTF-8");
            be.nextLine();
            while(be.hasNextLine()) cars.add(new SportCar(be.nextLine()));
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
