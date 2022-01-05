package sk.uniza.fri;

import javax.swing.JOptionPane;

/**
 * Created by IntelliJ IDEA.
 * User: Andrej
 * Date: 3.1.2022
 * Time: 20:10
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        String vyber;
        String moznosti;
        int cislo = 0;

        do {
            vyber = JOptionPane.showInputDialog(null, "Zadaj formát (terminal/grafika)");
            if (vyber.equals("terminal")) {


                Pole pole = new Pole(3);
                int[] krok;
                pole.vytvorNovePole();
                pole.zobrazPole();

                while (true) {
                    krok = Vstup.dajPoziciu();
                    pole.nastavPole('X', krok);

                    if (pole.kontrolaVyhry()) {
                        System.out.println("Hra skončila");
                        System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                        break;
                    }

                    pole.krokPC('O');
                    pole.zobrazPole();

                    if (pole.kontrolaVyhry()) {
                        System.out.println("Koniec Hry");
                        System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                        break;
                    }
                }
                cislo++;


                //System.out.println(cislo);
            } else if (vyber.equals("grafika")) {
                Frame frame = new Frame();
                cislo++;
                //System.out.println(cislo);
            } else {
                JOptionPane.showMessageDialog(null, "Zadal si nesprávnu voľbu.");
            }
        } while (cislo == 0);
    }




}










