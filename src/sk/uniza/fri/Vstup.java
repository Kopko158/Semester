package sk.uniza.fri;

import java.util.Scanner;

/**
 * @version 6.1.2022 - 15:30
 *
 * @author Andrej Kopas
 */
public class Vstup {

           /**
            * Metóda, ktorá načíta zadané pozície riadok/stlpec použivatelom z konzoly
            * @param velkost určuje aká plocha je vytvorená
            * @return vracia súradnie [x,y] - [riadok,stlpec]
            */
    public static int[] dajPoziciu(int velkost) {

        int riadok;
        int stlpec;
        int[] krok = new int[2];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Zadaj riadok");
        riadok = scanner.nextInt();

        while (riadok > velkost-1  || riadok < 0){
            System.out.println("Zadali ste zlu hodnotu riadku, zadajte znovu");
            riadok = scanner.nextInt();
        }

        System.out.println("Zadaj stlpec");
        stlpec = scanner.nextInt();
        while (stlpec > velkost-1 || stlpec < 0){
            System.out.println("Zadali ste zlu hodnotu stlpca, zadajte znovu");
            stlpec = scanner.nextInt();
        }

        krok[0] = riadok;
        krok[1] = stlpec;

        return krok;
    }
}