import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {

    public class Diafilm {
        String cim;
        int ev;
        int kocka;
        String szines;

        public Diafilm(String sor) {
            String[] s = sor.split(";");
            cim = s[0];
            ev = Integer.parseInt(s[1]);
            kocka = Integer.parseInt(s[2]);
            szines = s[3];
        }
    }

    public Main() {
        // 0. feladat
        loadFile("diafilm.csv");
        System.out.printf("0) %d diafilm adata beolvasva\n", diafilmek.size());
        int var = 0;
        for (Diafilm d : diafilmek) if (d.szines.equals("N")) var++;
        System.out.printf("   Közülük %d még fekete-fehér\n", var);

        // 1. feladat
        Diafilm legregebbi = diafilmek.get(0);
        for (Diafilm d : diafilmek) if (d.ev < legregebbi.ev) legregebbi = d;
        System.out.printf("1) A legrégebbi diafilm: %s (%d)\n", legregebbi.cim, legregebbi.ev);
        System.out.printf("   De ugyanebben az évben készült még:\n");
        for (Diafilm d : diafilmek) if (d.ev == legregebbi.ev) System.out.printf("   - %s (%d)\n", d.cim, d.ev);

        // 2. feladat
        float atlag = 0;
        var = 0;
        for (Diafilm d : diafilmek)
            if (d.ev < 2000) {
                atlag += d.kocka;
                var++;
            }
        System.out.printf("2) A 2000 előtt készült diafilmek átlagos kockaszáma: %.01f\n", atlag / var);
        atlag = 0;
        var = 0;
        for (Diafilm d : diafilmek)
            if (d.ev >= 2000) {
                atlag += d.kocka;
                var++;
            }
        System.out.printf("   A később készült diafilmeknél az átlag: %.01f\n", atlag / var);

        // 3. feladat
        System.out.printf("3) Az egyes évtizedekben készült diafilmek száma:\n");
        Map<Integer, Integer> stat = new HashMap<>();
        for (Diafilm d : diafilmek) {
            int evtized = d.ev / 10;
            if (stat.containsKey(evtized)) {
                stat.put(evtized, stat.get(evtized) + 1);
            } else {
                stat.put(evtized, 1);
            }
        }

        for (Integer i : stat.keySet()) {
            System.out.printf("%d - %d : %d darab\n", i * 10, (i + 1) * 10, stat.get(i));
        }

        // 4. feladat
        TreeMap<Integer, Integer> stats = new TreeMap<>();
        for (Diafilm d : diafilmek) {
            if (!stats.containsKey(d.ev)) {
                stats.put(d.ev, d.kocka);
            } else {
                stats.put(d.ev, stats.get(d.ev) + d.kocka);
            }
        }
        ArrayList<Integer> statsByEv = new ArrayList<>(stats.values());
        statsByEv.sort((a, b) -> b - a);

        // 5. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("200x.txt"), "utf-8");
            for (Diafilm d : diafilmek) {
                int evtized = d.ev / 10;
                if (200 <= evtized < 209) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (writer != null) writer.close();
            System.out.printf("5) A 200x évben megjelent diák adatai elmentve (200x.txt)\n");
        }

    }

    private ArrayList<Diafilm> diafilmek = new ArrayList<>();

    public void loadFile(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) diafilmek.add(new Diafilm(be.nextLine()));
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
