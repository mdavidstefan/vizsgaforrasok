import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {

    public class Utazas {
        String nev;
        String varos;
        String u_datum;
        String u_honap;
        String u_nap;
        String indulas;
        int i_ora;
        int i_perc;

        public Utazas(String sor) {
            String[] s = sor.split(";");
            nev = s[0];
            varos = s[1];
            u_datum = s[2];
            u_honap = s[2].split("\\.")[0];
            u_nap = s[2].split("\\.")[1];
            indulas = s[3];
            i_ora = Integer.parseInt(s[3].split(":")[0]);
            i_perc = Integer.parseInt(s[3].split(":")[1]);
        }
    }

    private ArrayList<Utazas> utazasok = new ArrayList<>();

    public Main() {
        // 0. feladat
        betolt("utazok.csv");
        System.out.printf("0) %d utazó adata beolvasva\n", utazasok.size());

        // 1. feladat
        TreeSet<String> varosok = new TreeSet<>();
        for (Utazas u : utazasok) varosok.add(u.varos);
        int var = (int) (Math.round(Math.random() * varosok.size()));
        System.out.printf("1) Összesen %d városba utaztak\n", varosok.size());
        String randvaros = varosok.toArray()[var].toString();
        System.out.printf("   Közülük egy véletlenszerűen kiválasztott: %s\n", randvaros);
        var = 0;
        for (Utazas u : utazasok) if (u.varos == randvaros) var++;
        System.out.printf("   Ebbe a városba %d utazó érkezett\n", var);

        // 2. feladat
        utazasok.sort((a, b) -> b.indulas.compareTo(a.indulas));
        System.out.printf("2) Legkorábbi indulás: %s\n", utazasok.get(utazasok.size() - 1).indulas);
        var = 0;
        for (Utazas u : utazasok) if (u.i_ora < 12) var++;
        System.out.printf("   Összesen %d utazás kezdődött délelőtt\n", var);

        // 3. feladat
        TreeMap<String, Integer> stat = new TreeMap<>();
        for (Utazas u : utazasok) {
            if (!stat.containsKey(u.u_honap)) stat.put(u.u_honap, 1);
            else stat.put(u.u_honap, stat.get(u.u_honap) + 1);
        }
        System.out.printf("3) Utazások száma hónaponként:\n");
        for (String ho : stat.keySet()) {
            System.out.printf("   %s.hó : %d utazás\n", ho, stat.get(ho));
        }

        // 4. feladat
        ArrayList<String> egyszer = new ArrayList<>();
        TreeSet<String> tobbszor = new TreeSet<>();
        for (Utazas u : utazasok) {
            String vezeteknev = u.nev.split(" ")[0];
            if (!egyszer.contains(vezeteknev)) egyszer.add(vezeteknev);
            else tobbszor.add(vezeteknev);
        }
        System.out.printf("4) Többször szereplő vezetéknevek:\n");
        System.out.printf("   %s", String.join(" ", tobbszor));

        // 5. feladat
        TreeMap<String, Integer> stat2 = new TreeMap<>();
        for (Utazas u : utazasok) {
            if (!stat2.containsKey(u.u_datum)) stat2.put(u.u_datum, 1);
            else stat2.put(u.u_datum, stat2.get(u.u_datum) + 1);
        }

        System.out.printf("\n5) Ugyanazon a napon kettőnél több utazás: ");
        for (String datum : stat2.keySet()) {
            if (stat2.get(datum) > 2) System.out.printf("%s(%d) ", datum, stat2.get(datum));
        }

        // 6. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("szeged.txt"), "utf-8");
            for (Utazas u : utazasok) if (u.varos.equals("Szeged")) writer.printf("%s (%s %s)\n", u.nev, u.u_datum, u.indulas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("\n6) Szegedre utazók kiírva a szeged.txt fájlba");
            if (writer != null) writer.close();
        }


    }

    private void betolt(String filename) {
        Scanner be = null;
        try {
            be = new Scanner(new File(filename), "utf-8");
            while (be.hasNextLine()) utazasok.add(new Utazas(be.nextLine()));
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
