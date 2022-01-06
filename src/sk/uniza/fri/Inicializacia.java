package sk.uniza.fri;

import javax.swing.JOptionPane;

/**
 * @version 6.1.2022 - 15:30
 *
 * @author Andrej Kopas
 */
public class Inicializacia {

    /**
     * Úvodné okno cez ktoré si užívateľ vyberá možnosť či chce hrať terminálovú formu alebo grafickú
     *  Ak sa vyberie terminálová tak sa aj inicializujú základné nastavenia + overenia výhry a vypísanie výhercu
     */
    public Inicializacia() {

        String vyber;
        int cislo = 0;

        do {
            vyber = JOptionPane.showInputDialog(null, "Zadaj formát (terminal/grafika)");
            if (vyber.equals("terminal")) {

                Pole pole = new Pole(3);
                int[] krok;
                pole.vytvorNovePole();
                pole.zobrazPole();

                while (true) {
                    krok = Vstup.dajPoziciu(pole.dajVelkost());
                    if (pole.overObsadenie(krok)) {
                        pole.nastavPole('X', krok);
                        if (pole.kontrolaVyhry()) {
                            System.out.println("Hra skončila");
                            System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                            pole.zobrazPole();
                            break;
                        } else if (pole.kontrolaRemizy()){
                            System.out.println("Hra skončila");

                        }

                        pole.krokPC('O');
                        pole.zobrazPole();
                        if (pole.kontrolaVyhry()) {
                            System.out.println("Hra skončila");
                            System.out.println(" Víťaz je " + pole.getPoslednyPohyb());
                            pole.zobrazPole();
                            break;
                        } else if (pole.kontrolaRemizy()) {
                            System.out.println("Hra skončila");
                        }

                    } else {
                        System.out.println("Opakuj volbu zly tah, obsadene policko\n");
                    }
                }
                cislo++;


            } else if (vyber.equals("grafika")) {
                new Frame();
                cislo++;

            } else {
                JOptionPane.showMessageDialog(null, "Zadal si nesprávnu voľbu.");
            }
        } while (cislo == 0);
    }


}
