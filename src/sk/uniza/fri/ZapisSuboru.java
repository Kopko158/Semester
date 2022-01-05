package sk.uniza.fri;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

/**
 * 3.1.2022 - 20:10
 *
 * @author Andrej
 */

public class ZapisSuboru {

    //private File  suhrn = new File("suhrn.txt");
    private File statistiky = new File("statistiky.txt");
    private int x = 0;
    private int y = 0;

    /**
     * Vytvorí ak ešte nie sú vytvorené textový dokument  statistiky.txt
     */
    public ZapisSuboru() {
        try {

            if (this.statistiky.createNewFile()) {
                System.out.println("Subor bol vytvorený: " + this.statistiky.getName());
            } else {
                System.out.println("Subor: " + this.statistiky.getName() + " už existuje.");
            }
        } catch (IOException e) {
            System.out.println("Nastal niekde problem.");
            e.printStackTrace();
        }
    }
    /**
     *  Metóda prečíta, vypíše a zapíše do súboru aktuálne skóre
     */
    public void zapisStatistiku(int vyhral) {
        switch (vyhral) {
            case 5:
                this.x++;
                break;
            case 10:
                this.y++;
                break;
            case 15:
                this.x--;
                this.y--;
                break;
        }

        try {
            FileWriter statsW = new FileWriter("statistiky.txt");
            statsW.write("\n\nAktuálna hra (pokial sa nezavrie aplikácia) : \n");
            statsW.write("Ak vyhráš dostaneš +1b ak prehráš body ostávajú, ale ak nastane remíza dostanete obaja -1b) : \n");
            statsW.write("Tvoje výhry: " + this.x );
            statsW.write("\nProtihráčové výhry: " + this.y );
            statsW.close();
        } catch (IOException e) {
            System.out.println("Nastal niekde problem.");
            e.printStackTrace();
        }
        this.vypisZoSuboru();
    }
    /**
     * Metóda ktorá prečíta všetko zo súboru a vypíše do konzoly
     *
     * int i;
     * while  ((i = fr.read()) != -1) {
     * System.out.print((char)i);
     * }
     * použite z https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     *
     */
    public void vypisZoSuboru() {
        try {
            System.out.println("-------------------------");
            FileReader fr = new FileReader(this.statistiky);

            int i;
            while  ((i = fr.read()) != -1) {
                System.out.print((char)i);
            }

            fr.close();
            System.out.println("\n-------------------------");
        } catch (IOException e) {
            System.out.println("Nastal niekde problem.");
            e.printStackTrace();
        }
    }
}

