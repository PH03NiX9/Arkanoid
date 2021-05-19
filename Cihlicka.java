

/**
 * Reprezentuje cihlicku, která se rozbije po doknutí míčku
 */
public class Cihlicka {

    char znakCihlicky = '█';

    private Kostka kostka;

    public Cihlicka(int x, int y) {
        kostka = new Kostka(x, y, znakCihlicky);
    }

    public Kostka vratKostku() {
        return kostka;
    }

}
