package sk.uniza.fri;

/**
 * 5.1.2022 - 11:43
 *
 * @author Andrej
 */
public class Policko {

    private Stav stav;
    public Policko() {

    }

    /**
     * Vracia typ, políčka.
     *
     * @return typ polička
     */
    public Stav dajStav() {
        return this.stav;
    }

    /**
     * Nastavuje typ políčka na typ zadaný v parametri.
     */
    public void nastavStav(Stav typ) {
        this.stav = stav;
    }

    /**
     * Vracia hodnotu políčka v primitívnom type char.
     */
    public char dajCharHodnotu() {
        return this.stav.getZnak();
    }
}
