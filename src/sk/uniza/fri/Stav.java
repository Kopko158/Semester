package sk.uniza.fri;

/**
 * 24.11.2021 - 9:23
 *
 * @author Andrej
 */


public enum Stav {
    PRAZDNE(""),
    XOVE("X"),
    OCKOVE("O");

    private String znak;

    Stav (String znak) {
        this.znak = znak;
    }

    public String getZnak() {
        return this.znak;
    }
}
