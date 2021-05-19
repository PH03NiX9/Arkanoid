

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reprezentuje hrací plochu
 */
public class Plocha {

    /**
     * Šířka hrací plochy
     */
    private int sirka;

    /**
     * Výška hrací plochy
     */
    private int vyska;

    /**
     * Hráč/Had
     */
    private Micek micek;

    /**
     * Cihličky
     */
    private ArrayList<Cihlicka> cihlicky = new ArrayList<>();

    private char pozadi = ' ';

    /**
     * Inicializuje hrací plochu
     *
     * @param sirka Šířka hrací plochy
     * @param vyska Výška hrací plochy
     */
    public Plocha(int sirka, int vyska) {
        this.sirka = sirka;
        this.vyska = vyska;
        micek = new Micek(sirka, vyska);
        vyplnCihlicky();
    }

    public void vykresli() {
        while (!cihlicky.isEmpty()) { //Dokud nejsou cihličky prázdné
            micek.posunSe();
            Kostka kostkaMicku = micek.vratKostku();

            kontrolaStranPlochy();
            kontrolaKolizi();

            for (int y = -1; y < vyska + 1; y++) { //Začínáme u -1 abychom omylem nekolidovali s hracím polem
                for (int x = -1; x < sirka + 1; x++) {
                    //Kreslení okrajů
                    if (x == -1 && y == -1) System.out.print("+");
                    else if (x == -1 && y == vyska) System.out.print("+");
                    else if (x == sirka && y == vyska) System.out.print("+");
                    else if (x == sirka && y == -1) System.out.print("+");
                    else if (x == -1 || x == sirka) System.out.print("|");
                    else if (y == -1 || y == vyska) System.out.print("-");
                    else { //Kreslení hrací plochy
                        Kostka kostka = vratKostku(x, y);
                        if (kostka != null) {
                            System.out.print(kostka.getZnak());
                        } else {
                            System.out.print(pozadi);
                        }
                    }
                }

                System.out.println();
            }

            for (int i = 0; i < 3; i++) {
                System.out.println();
            } //Náhrada clear
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Fail silently like a ninja!
            }
            
        }

        System.out.println("Game over!");
    }

    private void vyplnCihlicky() {
        for (int i = 3; i < sirka - 3; i++) {
            for (int j = 4; j < vyska - 1; j++) {
                cihlicky.add(new Cihlicka(i, vyska - j));
            }
        }
    }

    private Kostka vratKostku(int x, int y) {
        Kostka kostkaMicku = micek.vratKostku();
        if (kostkaMicku.getX() == x && kostkaMicku.getY() == y)
            return kostkaMicku;

        for (Cihlicka cihlicka : cihlicky) {
            Kostka iterovanaCihlicka = cihlicka.vratKostku();
            if (iterovanaCihlicka.getX() == x && iterovanaCihlicka.getY() == y)
                return iterovanaCihlicka;
        }

        return null;
    }

    private void kontrolaStranPlochy() {
        Kostka kostka = micek.vratKostku();

        if (kostka.mimoKonzoliDolni(vyska))
            micek.nastvatSmerY(1);
        else if (kostka.mimoKonzoliHorni())
            micek.nastvatSmerY(-1);

        if (kostka.mimoKonzoliLeva())
            micek.nastvatSmerX(1);
        else if (kostka.mimoKonzoliPrava(sirka))
            micek.nastvatSmerX(-1);
    }

    private void kontrolaKolizi() {
        Kostka kostka = micek.vratKostku();
        Cihlicka cihlickaKeSmazani = null;
        for (Cihlicka cihlicka : cihlicky) {
            if (kostka.kolizeVertikalni(cihlicka.vratKostku())) {
                cihlickaKeSmazani = cihlicka;
                micek.nastvatSmerY(micek.vratSmerY() * -1);
                break;
            } else if (kostka.kolizeHoriznotalni(cihlicka.vratKostku())) {
                cihlickaKeSmazani = cihlicka;
                micek.nastvatSmerX(micek.vratSmerX() * -1);
                break;
            }
        }
        if (cihlickaKeSmazani == null) {
            for (Cihlicka cihlicka : cihlicky) {
                if (kostka.kolizeDiagonalne(cihlicka.vratKostku(), micek.vratSmerX(), micek.vratSmerY())) {
                    cihlickaKeSmazani = cihlicka;
                    micek.nastvatSmerY(micek.vratSmerY() * -1);
                    micek.nastvatSmerX(micek.vratSmerX() * -1);
                    break;
                }
            }
        }
        cihlicky.remove(cihlickaKeSmazani);
    }

}
