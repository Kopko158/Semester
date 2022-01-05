package sk.uniza.fri;
/**
 * 24.11.2021 - 9:23
 *
 * @author Andrej
 */


/**
 *
 */
public enum Stav {
    PRAZDNE(' '),
    XOVE('X'),
    OCKOVE('O');

    /**
     *
     */
    private char znak;

    Stav (char znak) {
        this.znak = znak;
    }

    /**
     *
     */
    public char getZnak() {
        return this.znak;
    }
}
