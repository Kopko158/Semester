package sk.uniza.fri;

import java.util.Scanner;

/**
 * 3.1.2022 - 20:10
 *
 * @author Andrej
 */
public class Vstup {

    public static int[] dajPoziciu() {

        int riadok;
        int stlpec;
        int[] krok = new int[2];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Zadaj riadok");
        riadok = scanner.nextInt();
        System.out.println("Zadaj stlpec");
        stlpec = scanner.nextInt();

        krok[0] = riadok;
        krok[1] = stlpec;

        return krok;
    }
}
