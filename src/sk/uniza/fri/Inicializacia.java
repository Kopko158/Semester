package sk.uniza.fri;

import javax.swing.JOptionPane;

/**
 * 3.1.2022 - 20:10
 *
 * @author Andrej
 */
public class Inicializacia {


    /**
     *
     */
    public Inicializacia() {

        String vyber;
        int cislo = 0;

       // Stav prazdne = Stav.PRAZDNE;
       // Stav xove = Stav.XOVE;
       // Stav ockove = Stav.OCKOVE;

        do {
            vyber = JOptionPane.showInputDialog(null, "Zadaj formát (terminal/grafika)");
            if (vyber.equals("terminal")) {

                Pole pole = new Pole(3);
                int[] krok;
                pole.vytvorNovePole();
                pole.zobrazPole();

                while (true) {
                    krok = Vstup.dajPoziciu();
                    if (pole.overObsadenie(krok)) {
                        pole.nastavPole('X', krok);
                        if (pole.kontrolaVyhry()) {
                            System.out.println("Hra skončila");
                            System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                            pole.zobrazPole();
                            break;
                        } else if (pole.kontrolaRemizy()){
                            pole.zobrazPole();

                        }

                        pole.krokPC('O');
                        pole.zobrazPole();
                        if (pole.kontrolaVyhry()) {
                            System.out.println("Hra skončila");
                            System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                            pole.zobrazPole();
                            break;
                        } else if (pole.kontrolaRemizy()) {
                            pole.zobrazPole();
                        }

                    } else {
                        System.out.println("Opakuj volbu zly tah, obsadene policko\n");
                    }
                }
                cislo++;

                //System.out.println(cislo);
            } else if (vyber.equals("grafika")) {
                new Frame();
                cislo++;
                //System.out.println(cislo);
            } else {
                JOptionPane.showMessageDialog(null, "Zadal si nesprávnu voľbu.");
            }
        } while (cislo == 0);
    }


}
