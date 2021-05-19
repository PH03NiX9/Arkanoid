

public class Kostka {

    /**
     * Vodorovná souřadnice
     */
    private int x;

    /**
     * Svislá souřadnice
     */
    private int y;

    /**
     * Vzhled kostky reprezentován jako znakCihlicky
     */
    private char znak;

    public boolean mimoKonzoliPrava(int sirka) {
        return x >= sirka - 1;
    }

    public boolean mimoKonzoliLeva() {
        return x < 1;
    }

    public boolean mimoKonzoliHorni() {
        return y < 1;
    }

    public boolean mimoKonzoliDolni(int vyska) {
        return y >= vyska - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Inicializuje instanci
     *
     * @param x    Vodorovná souřadnice
     * @param y    Svislá souřadnice
     * @param znak Znak
     */
    public Kostka(int x, int y, char znak) {
        this.x = x;
        this.y = y;
        this.znak = znak;
    }

    /**
     * Vratí znakCihlicky kostky
     *
     * @return Znak
     */
    public char getZnak() {
        return znak;
    }

    /**
     * Zjistí zda kostka koliduje s jinou kostkou v herním poli
     *
     * @param kostka Jiná kostka
     * @return Zda kostka koliduje s jinou kostkou
     */
    public boolean kolize(Kostka kostka) {
        return (x == kostka.x && y == kostka.y);
    }

    public boolean kolizeHoriznotalni(Kostka kostka) {
        return (x == kostka.x - 1 && y == kostka.y) || (x == kostka.x + 1 && y == kostka.y);
    }

    public boolean kolizeVertikalni(Kostka kostka) {
        return (x == kostka.x && y == kostka.y - 1) || (x == kostka.x && y == kostka.y + 1);
    }

    public boolean kolizeDiagonalne(Kostka kostka, int smerX, int smerY) {
        return (x == kostka.x + smerX && y == kostka.y + smerY);
    }
}
