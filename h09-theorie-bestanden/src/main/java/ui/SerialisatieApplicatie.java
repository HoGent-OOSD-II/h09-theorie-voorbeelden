package ui;

import domein.DomeinController;

public class SerialisatieApplicatie {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        DomeinController dc = new DomeinController();

        dc.schrijfPersoonWeg("Kiara", 22);
        dc.schrijfPersoonWeg("Nafi", 19);
        dc.schrijfPersoonWeg("Tamu", 33);

        dc.sluitAf();
    }

}