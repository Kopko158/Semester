package sk.uniza.fri;

/**
 * 3.1.2022 - 20:10
 *
 * @author Andrej
 */
public class Inicializacia {
    private Pole pole;

    public Inicializacia() {

    }

    public void spustiHru() {
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
    }
}
