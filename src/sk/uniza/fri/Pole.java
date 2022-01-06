package sk.uniza.fri;
/**
 * 6.1.2022 - 15:30
 *
 * @author Andrej Kopas
 */
public class Pole {

    private static final int ZAKLADNA_VELKOST_POLA = 5;
    private static final int VYHERNA_SERIA = 3;
    private static final int MIN_VELKOST_POLA = 2;
    private static final int MAX_VELKOST_POLA = 10;
    private static final char ZAKLADNA_HODNOTA_POLA = ' ';
    private final int velkost;
    private char[][] hraciaPlocha;
    private char poslednyPohyb;


    /***
     * Nastavenie plochy a overenie či nie je väčšia alebo menšia ako je povolené, ak presahuje nastaví defaultnú veľkosť
     * @param velkost
     */
    public Pole(int velkost) {
        if (MAX_VELKOST_POLA < velkost || velkost < MIN_VELKOST_POLA) {
            this.velkost = ZAKLADNA_VELKOST_POLA;
        } else {
            this.velkost = velkost;
        }
    }


    public int dajVelkost() {
        return this.velkost;
    }

    /***
     * Vytvorenie noveho herneho poľa
     */
    public void vytvorNovePole() {
        this.hraciaPlocha = new char[this.velkost][this.velkost];
        for (int x = 0; x < this.velkost; x++) {
            for (int y = 0; y < this.velkost; y++) {
                this.hraciaPlocha[x][y] = ZAKLADNA_HODNOTA_POLA;
            }
        }
    }

    /**
     * Vykreslenie pola do terminálu
     */
    public void zobrazPole() {
        System.out.print("  |");
        for (int i = 0; i < this.velkost; i++) {
            System.out.print("_" + i + "_|"); // riadkove oznacenie
        }
        System.out.println();

        for (int i = 0; i < this.velkost; i++) {
            System.out.print( i + " |"); // stlpcove oznacenie
            for (int j = 0; j < this.velkost; j++) {
                System.out.print(" " + this.hraciaPlocha[i][j] + " |");
            }
            System.out.println();

            System.out.print("  +");
            for (int j = 0; j < this.velkost; j++) {
                System.out.print("--- ");
            }
            System.out.println();
        }

        System.out.print("***");
        for (int j = 0; j < this.velkost; j++) {
            System.out.print(" *** ");
        }
        System.out.println();
    }

    /**
     * Ťah použivateľa
     * @param krok
     * @param znak
     */
    public void nastavPole(char znak, int[] krok) {

        int riadok = krok[0];
        int stlpec = krok[1];

        this.hraciaPlocha[riadok][stlpec] = znak;
        this.poslednyPohyb = znak;

    }

    /**
     * vráti hodnotu kto naposledy bol na ťahu
     * @return
     */
    public char getPoslednyPohyb() {
        return this.poslednyPohyb;
    }

    /**
     * Overenie obsadenia políčok
     * @param krok
     * @return
     */
    public boolean overObsadenie(int[] krok) {

        int riadok = krok[0];
        int stlpec = krok[1];
        return this.hraciaPlocha[riadok][stlpec] != 'O' && this.hraciaPlocha[riadok][stlpec] != 'X';

    }

    /**
     * ťah počítača
     * @param znak
     */
    public void krokPC(char znak) {
        String seria = "";
        for (int x = 0; x < this.velkost; x++) {
            for (int y = 0; y < this.velkost; y++) {
                seria = seria + this.hraciaPlocha[x][y];
            }
        }
        int dlzka = seria.length();

        char citacPC;
        int random;

        do {
            random = (int)(Math.random() * dlzka);
            citacPC = seria.charAt(random);
        } while (citacPC != ' ');
        int x = random / this.velkost;
        int y = random % this.velkost;
        this.hraciaPlocha[x][y] = znak;
        this.poslednyPohyb = znak;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v pravej diagonále
     * @param citac
     * @param x
     * @param y
     * @return
     */
    public boolean kontrolaPravaDiagonala(char citac, int x, int y) {
        int pocet = 0;

        if (this.velkost - y >= VYHERNA_SERIA && this.velkost - x >= VYHERNA_SERIA) {
            for (int i = y; i <= this.velkost; i++) {
                if (pocet >= VYHERNA_SERIA) {
                    return true;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        return false;
                    } else {
                        pocet++;
                        x++;
                    }
                }
            }
        } return false;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v pravej diagonále
     * @param citac
     * @param x
     * @param y
     * @return
     */
    public boolean kontrolaLavaDiagonala(char citac, int x, int y) {
        int counter = 0;

        if (y >= VYHERNA_SERIA - 1 && this.velkost - x >= VYHERNA_SERIA) {
            for (int i = y; i <= this.velkost; i--) {
                if (counter >= VYHERNA_SERIA) {
                    return true;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        return false;
                    } else {
                        counter++;
                        x++;
                    }
                }
            }
        } return false;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v riadku
     * @param citac
     * @param x
     * @param y
     * @return
     */
    public boolean kontrolaRiadok(char citac, int x, int y) {
        int pocetR = 0;

        if (this.velkost - y >= VYHERNA_SERIA ) {
            for (int i = y; i <= this.velkost; i++) {
                if (pocetR >= VYHERNA_SERIA) {
                    return true;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        return false;
                    } else {
                        pocetR++;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v stĺpci
     * @param citac
     * @param x
     * @param y
     * @return
     */
    public boolean kontrolaStlpec(char citac, int x, int y) {
        int pocetS = 0;
        if (this.velkost - x >= VYHERNA_SERIA ) {
            for (int i = x; i <= this.velkost; i++) {
                if (pocetS >= VYHERNA_SERIA) {
                    return true;
                } else {
                    if (this.hraciaPlocha[i][y] != citac) {
                        return false;
                    } else {
                        pocetS++;
                    }
                }
            }
        } return false;
    }

    /**
     * Kontrola či nastala remíza
     * @return
     */
    public boolean kontrolaRemizy() {
        int plnePolicko = 0;
        for (int x = 0; x < this.velkost; x++)  {
            for (int y = 0; y < this.velkost; y++) {
                if (this.hraciaPlocha[x][y] != ' ') {
                    plnePolicko++;
                }
            }
        }
        if (plnePolicko == 9) {
            System.out.println("Nastala remíza");
            this.zobrazPole();
        }
        return false;
    }

    /**
     * Kontrola či niekto vyhral
     * @return
     */
    public boolean kontrolaVyhry() {
        boolean vysledok = false;
        for (int x = 0; x < this.velkost; x++) {
            for (int y = 0; y < this.velkost; y++) {
                char citac = this.hraciaPlocha[x][y];
                if (vysledok) {
                    break;
                }
                if (citac != ZAKLADNA_HODNOTA_POLA) {
                    vysledok = this.kontrolaRiadok(citac, x, y);
                    if (!vysledok) {
                        vysledok = this.kontrolaPravaDiagonala(citac, x, y);
                    }
                    if (!vysledok) {
                        vysledok = this.kontrolaLavaDiagonala(citac, x, y);
                    }
                    if (!vysledok) {
                        vysledok = this.kontrolaStlpec(citac, x, y);
                    }
                }
            }
        }
        return vysledok;
    }
}
