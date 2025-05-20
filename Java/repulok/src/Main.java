import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public class Repulo {
        String tipus;
        float hossz;
        int suly;
        int ferohely;
        int tank;

        public Repulo (String sor) {
            String [] s = sor.split(";");
            tipus = s[0];
            hossz = Float.parseFloat(s[1]);
            suly = Integer.parseInt(s[2]);
            ferohely = Integer.parseInt(s[3]);
            tank = Integer.parseInt(s[4]);
        }
    }

    public Main() {
        // 0. feladat
        betolt("repulok.csv");
        System.out.printf("0) Összesen %d repülő adata beolvasva.\n", repulok.size());
        int random = (int)(Math.floor(Math.random() * repulok.size()));
        System.out.printf("   Közülük egy véletlen kiválasztott: %s\n", repulok.get(random).tipus);

        // 1. feladat
        repulok.sort((r1, r2) -> r2.ferohely - r1.ferohely);
        System.out.printf("1) Legtöbb férőhellyel rendelkezik: %s (%d hely)\n", repulok.get(0).tipus, repulok.get(1).ferohely);
        System.out.printf("   A második legtöbb férőhely: %s (%d hely)\n", repulok.get(1).tipus, repulok.get(1).ferohely);

        // 2. feladat
        int db = 0; float atlag = 0;
        for (Repulo r : repulok) if (r.suly < 100000) {atlag += r.suly; db++;}
        System.out.printf("2) A 100000kg súlynál kisebb gépek (%d darab) átlagsúlya: %.02fkg\n", db, atlag/db);

        // 3. feladat
        String[] szamok = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        System.out.printf("3) Típusok, amelyikben nincs szám: ");
        for (Repulo r : repulok ) {
            boolean van = false;
            for (int i = 0; i < szamok.length; i++) {
                if (r.tipus.contains(szamok[i]))  van = true;
            }
            if(!van) System.out.printf("%s\n", r.tipus);
        }

        // 4. feladat
        TreeSet<String> gyartok = new TreeSet<>();
        System.out.printf("4) Gyártók: ");
        for (Repulo r : repulok) {
            String [] hossz = r.tipus.split(" ");
            if (hossz.length > 1) gyartok.add(hossz[0]);
            else gyartok.add(r.tipus);
        }
        String out = "";
        for (String gyarto : gyartok) {
            out += gyarto+", ";
        }
        System.out.println(out.substring(0 ,out.length()-2));
        random = (int)(Math.floor(Math.random() * gyartok.size()));
        String randomgyarto = gyartok.toArray()[random]+"";
        System.out.printf("   Közülük egy véletlen kiválasztott: %s\n   Termékeik:\n", randomgyarto);
        for (Repulo r : repulok) if (r.tipus.contains(randomgyarto)) System.out.printf("    - %s\n", r.tipus);

        // 5. feladat
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("sokutas.txt"), "utf-8");
            for (Repulo r : repulok) if (r.ferohely > 300) writer.printf("%s / %d hely\n", r.tipus, r.ferohely);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                System.out.printf("5) A 300 főnél több férőhelyű gépek adatai a sokutas.txt fájlba mentve.\n");
                writer.close();
            }
        }
    }

    private ArrayList<Repulo> repulok = new ArrayList<>();

    private void betolt (String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) repulok.add(new Repulo(be.nextLine()));
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
