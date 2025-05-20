import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public class Adat {
        String megnevezes;
        int beszerzes;
        int db;
        int egysegar;

        public Adat (String sor) {
            String [] s = sor.split(";");
            megnevezes = s[0];
            beszerzes = Integer.parseInt(s[1]);
            db = Integer.parseInt(s[2]);
            egysegar = Integer.parseInt(s[3]);
        }
    }

    private ArrayList<Adat> adatok = new ArrayList<>();

    public Main() {
        // 0. feladat
        betolt("leltar.csv");
        System.out.printf("0) A beolvasott leltári tételek száma: %d\n", adatok.size());
        int variable = 0;
        for (Adat a : adatok) variable+= (a.egysegar * a.db);
        System.out.printf("   A benne lévő eszközök összára: %,d,-Ft\n", variable);

        // 1. feladat
        Adat legdragabb = adatok.get(0);
        for (Adat a : adatok) if (a.egysegar > legdragabb.egysegar) legdragabb = a;
        System.out.printf("1) A legdrágább eszköz: %s (%,d,-Ft)\n", legdragabb.megnevezes, legdragabb.egysegar);

        // 2. feladat
        int mettol = adatok.get(0).beszerzes, meddig = adatok.get(0).beszerzes;
        for (Adat a : adatok ) {if (a.beszerzes < mettol) mettol = a.beszerzes; if (a.beszerzes > meddig) meddig = a.beszerzes; }
        System.out.printf("2) A leltár a %d-%d éveket tartalmazza\n", mettol, meddig);

        // 3. feladat


        // 4. feladat
        TreeMap<Integer, Integer> stat = new TreeMap<>();
        for (Adat a : adatok) {
            if (!stat.containsKey(a.beszerzes)) stat.put(a.beszerzes, (a.egysegar * a.db));
            else stat.put(a.beszerzes, stat.get(a.beszerzes) + (a.egysegar * a.db));
        }

        System.out.printf("4) A legnagyobb összértékű beszerzés éve: %d\n", stat.firstKey());
        System.out.printf("   Ekkor a beszerzés összértéke: %,d,-Ft\n", stat.get(stat.firstKey()));

        // 5. feladat
        Adat legnagyobb = adatok.get(0);
        for (Adat a: adatok) if ((a.egysegar * a.db) > (legnagyobb.egysegar * legnagyobb.db)) legnagyobb = a;
        System.out.printf("5) A legnagyobb értékű beszerzés:\n");
        System.out.printf("   %d darab %s = %,d,-Ft\n", legnagyobb.db, legnagyobb.megnevezes, (legnagyobb.egysegar * legnagyobb.db));

        // 6. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("kezdes.txt"), "utf-8");
            for (Adat a : adatok) if (a.beszerzes == mettol) writer.printf("%d x %s = %,d,-Ft\n", a.db, a.megnevezes, (a.egysegar * a.db));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                System.out.printf("6) Az első év adatai kiírva a kezdes.txt fájlba");
                writer.close();
            }
        }
    }

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
