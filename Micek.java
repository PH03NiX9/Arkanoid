

import java.util.Random;

/**
 * Reprezentuje hada
 */
public class Micek {

    /**
     * Jedna kostka, reprezentující micek
     */
    private Kostka kostka;

    /**
     * Směr míčku X 1 (Pravá), -1 (Levá)
     */
    private int smerX;

    /**
     * Směr míčku Y 1 (Nahoru), -1 (Dolu)
     */
    private int smerY;

    /**
     * Znak, který bude reprezentovat hada
     */
    private char znakMicku = 'O';

    /**
     * Inicializuje instanci
     */
    public Micek(int sirkaPlochy, int vyskaPlochy) {
        smerX = 1;
        smerY = 1;

        kostka = new Kostka(sirkaPlochy / 2, vyskaPlochy - 1, znakMicku);
    }

    public int vratSmerX() {
        return smerX;
    }

    public int vratSmerY() {
        return smerY;
    }

    public void nastvatSmerX(int smerX) {
        this.smerX = smerX;
    }

    public void nastvatSmerY(int smerY) {
        this.smerY = smerY;
    }

    public void posunSe() {
        // Posunutí nového míčku podle směru
        if (smerX == 1)
            kostka.setX(kostka.getX() + 1);
        if (smerX == -1)
            kostka.setX(kostka.getX() - 1);
        if (smerY == 1)
            kostka.setY(kostka.getY() - 1);
        if (smerY == -1)
            kostka.setY(kostka.getY() + 1);
    }

    public Kostka vratKostku() {
        return kostka;
    }
}
