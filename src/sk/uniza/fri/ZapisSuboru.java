package sk.uniza.fri;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

/**
 * @version 6.1.2022 - 15:30
 *
 * @author Andrej Kopas
 */

public class ZapisSuboru {

    private File statistiky = new File("statistiky.txt");
    private int x = 0;
    private int y = 0;

    /**
     * Vytvorí ak ešte nie je vytvorený textový dokument statistiky.txt
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
     * Metóda prečíta, vypíše a zapíše do súboru aktuálne skóre
     * @param vyhral Dáva informáciu ako skončila hra ( Vyhral X,O alebo Remíza)
     */
    public void zapisStatistiku(int vyhral) {
        switch (vyhral) {
            case 5 -> this.x++;
            case 10 -> this.y++;
            case 15 -> {
                this.x = this.x;
                this.y = this.y;
            }
        }

        try {
            FileWriter statsW = new FileWriter("statistiky.txt");
            statsW.write("\n\nAktuálna hra (pokial sa nezavrie aplikácia) : \n");
            statsW.write("Ak vyhráš dostaneš +1b a ak nastane remíza body ostávajú z predchádzajúceho stavu) : \n");
            statsW.write("Výhry X: " + this.x );
            statsW.write("\nVýhry O: " + this.y );
            statsW.close();
        } catch (IOException e) {
            System.out.println("Nastal niekde problem.");
            e.printStackTrace();
        }
        this.vypisZoSuboru();
    }

    /**
     *  Metóda ktorá prečíta všetko zo súboru a vypíše do konzoly
     *
     *   int i;
     *   while  ((i = fr.read()) != -1) {
     *   System.out.print((char)i);
     *   }
     *  použite z https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
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

