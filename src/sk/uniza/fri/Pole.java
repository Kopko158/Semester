package sk.uniza.fri;
/**
 * 3.1.2022 - 20:10
 *
 * @author Andrej
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


    /**
     * Nastavenie plochy a overenie či nie je väčšia alebo menšia ako je povolené, ak presahuje nastaví defaultnú veľkosť
     */
    public Pole(int velkost) {
        if (MAX_VELKOST_POLA < velkost || velkost < MIN_VELKOST_POLA) {
            this.velkost = ZAKLADNA_VELKOST_POLA;
        } else {
            this.velkost = velkost;
        }
    }

    /**
     * vráti hodnotu kto naposledy bol na ťahu
     */
    public char getPoslednyPohyb() {
        return this.poslednyPohyb;
    }

    /*
    public char[][] getHraciaPlocha() {
        return this.hraciaPlocha;
    }
    */

    public int dajVelkost() {
        return this.velkost;
    }

    /**
     *  Vytvorenie noveho herneho poľa
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
            System.out.print(" **** ");
        }
        System.out.println();
    }

    /**
     * Ťah použivateľa
     */
    public void nastavPole(char znak, int[] krok) {

        int riadok = krok[0];
        int stlpec = krok[1];

        this.hraciaPlocha[riadok][stlpec] = znak;
        this.poslednyPohyb = znak;

    }

    /**
     * Overenie obsadenia políčok
     */
    public boolean overObsadenie(int[] krok) {

        int riadok = krok[0];
        int stlpec = krok[1];
        return this.hraciaPlocha[riadok][stlpec] != 'O' && this.hraciaPlocha[riadok][stlpec] != 'X';

    }

    /**
     * Ťah počítača
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
     */
    public boolean kontrolaPravaDiagonala(char citac, int x, int y) {
        int pocitac = 0;
        boolean vysledokPD = false;

        if (this.velkost - y >= VYHERNA_SERIA && this.velkost - x >= VYHERNA_SERIA) {
            for (int i = y; i <= this.velkost; i++) {
                if (pocitac >= VYHERNA_SERIA) {
                    vysledokPD = true;
                    break;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        vysledokPD = false;
                        break;
                    } else {
                        pocitac++;
                        x++;
                    }
                }
            }
        } else {
            vysledokPD = false;
        }
        return vysledokPD;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v pravej diagonále
     */
    public boolean kontrolaLavaDiagonala(char citac, int x, int y) {
        int pocitac = 0;
        boolean vysledok = false;

        if (y >= VYHERNA_SERIA - 1 && this.velkost - x >= VYHERNA_SERIA) {
            for (int i = y; i <= this.velkost; i--) {
                if (pocitac >= VYHERNA_SERIA) {
                    vysledok = true;
                    break;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        vysledok = false;
                        break;
                    } else {
                        pocitac++;
                        x++;
                    }
                }
            }
        } else {
            vysledok = false;
        }
        return vysledok;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v riadku
     */
    public boolean kontrolaRiadok(char citac, int x, int y) {
        int pocitac = 0;
        boolean vysledok = false;

        if (this.velkost - y >= VYHERNA_SERIA ) {
            for (int i = y; i <= this.velkost; i++) {
                if (pocitac >= VYHERNA_SERIA) {
                    vysledok = true;
                    break;
                } else {
                    if (this.hraciaPlocha[x][i] != citac) {
                        vysledok = false;
                        break;
                    } else {
                        pocitac++;
                    }
                }
            }
        } else {
            vysledok = false;
        }
        return vysledok;
    }

    /**
     * Kontrola či sa nenachádzajú rovnaké symboly v stĺpci
     */
    public boolean kontrolaStlpec(char citac, int x, int y) {
        int pocitac = 0;
        boolean vysledok = false;

        if (this.velkost - x >= VYHERNA_SERIA ) {
            for (int i = x; i <= this.velkost; i++) {
                if (pocitac >= VYHERNA_SERIA) {
                    vysledok = true;
                    break;
                } else {
                    if (this.hraciaPlocha[i][y] != citac) {
                        vysledok = false;
                        break;
                    } else {
                        pocitac++;
                    }
                }
            }
        } else {
            vysledok = false;
        }
        return vysledok;
    }

    /**
     * Kontrola či nastala remíza
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
