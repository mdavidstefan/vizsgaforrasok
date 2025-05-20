import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public class Kigyo {
        String fajta;
        int hossz;
        String elofordulas;
        String merges;

        public Kigyo(String sor) {
            String[] s = sor.split(";");
            fajta = s[0];
            hossz = Integer.parseInt(s[1]);
            elofordulas = s[2];
            merges = s[3];
        }
    }

    public Main() {
        // 0. feladat
        betolt("kigyok.csv");
        System.out.printf("0) Összesen %d kígyó adata beolvasva.\n", kigyok.size());
        int merges = 0;
        int nemmerges = 0;
        for (Kigyo k : kigyok) if (k.merges.equals("Igen")) merges++; else nemmerges++;
        System.out.printf("   Közülük %d mérges és %d nem mérges.\n", merges, nemmerges);

        // 1. feladat
        float var = 0;
        for (Kigyo k : kigyok) var+=k.hossz;
        System.out.printf("1) A kígyók teljes hossza méterben: %.02fm\n", var/100);

        // 2. feladat
        Kigyo leghosszabb = kigyok.get(0);
        for (Kigyo k : kigyok) if (k.merges.equals("Igen") && k.hossz > leghosszabb.hossz) leghosszabb = k;
        System.out.printf("2) A leghosszabb mérges kígyó: %s (%dcm)\n", leghosszabb.fajta, leghosszabb.hossz);

        // 3. feladat
        TreeSet<String> szarmazas = new TreeSet<>();
        for (Kigyo k : kigyok) szarmazas.add(k.elofordulas);
        System.out.printf("3) A kígyók származási helye (abc): %s\n", String.join(", ", szarmazas));

        // 4. feladat
        ArrayList<Kigyo> mergeskigyok = new ArrayList<>();
        for (Kigyo k : kigyok) if (k.merges.equals("Igen")) mergeskigyok.add(k);
        int random = (int)(Math.round(Math.random() * mergeskigyok.size()));
        System.out.printf("4) Egy véletlen kiválasztott mérges kígyó: %s\n", mergeskigyok.get(random).fajta);
        System.out.printf("   Származási helye %s, hossza %dcm\n", mergeskigyok.get(random).elofordulas, mergeskigyok.get(random).hossz);

        // 5. feladat
        System.out.printf("5) Adott fajhoz (abc) tartozó kígyók darabszáma:\n");
        TreeMap<String, Integer> stat = new TreeMap<>();
        for (Kigyo k : kigyok) {
            String faj = "";
            if (k.fajta.contains(" ")) faj = k.fajta.split(" ")[k.fajta.split(" ").length-1];
            else faj = k.fajta;

            if (!stat.containsKey(faj)) stat.put(faj, 1);
            else stat.put(faj, stat.get(faj) + 1);
        }

        for (String s : stat.keySet()) {
            System.out.printf("  %s : %d féle\n", s, stat.get(s));
        }

        // 6. feladat
        ArrayList<String> mambak = new ArrayList<>();
        for (Kigyo k : kigyok) if (k.fajta.contains("Mamba")) mambak.add(k.fajta);
        System.out.printf("6) Az utolsó mamba fajtája: %s\n", mambak.get(mambak.size()-1));

        // 7. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("kobra.txt"), "utf-8");
            for (Kigyo k : kigyok) if (k.fajta.contains("Kobra")) writer.printf("%s (%dcm)\n", k.fajta, k.hossz);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("7) Minden kobra mentve a kobra.txt fájlba");
            writer.close();
        }

    }

    private ArrayList<Kigyo> kigyok = new ArrayList<>();

    public void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) kigyok.add(new Kigyo(be.nextLine()));
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
