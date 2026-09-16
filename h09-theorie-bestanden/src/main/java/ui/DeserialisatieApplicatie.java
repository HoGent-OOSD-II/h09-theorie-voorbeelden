package ui;

import domein.DomeinController;

public class DeserialisatieApplicatie {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        DomeinController dc = new DomeinController();

        System.out.println(dc.leesPersonenBestand());
        dc.sluitAf();
    }

}