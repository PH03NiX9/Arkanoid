

public class Main {

    public static void main(String[] args) {
        int sirka = 15;
        int vyska = 6;
        System.out.println("Hrací plocha arkanoid " + 15 + "x" + 6 + ": ");

        Plocha plocha = new Plocha(sirka, vyska);
        plocha.vykresli();
    }
}
