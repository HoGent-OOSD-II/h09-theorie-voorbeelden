package ui;

import domein.DomeinController;

public class WegschrijvenApplicatie {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        DomeinController dc = new DomeinController();
        dc.schrijfProductWeg("Laptop", 850, 2);
        dc.schrijfProductWeg("Smartphone", 320.56, 5);
        dc.schrijfProductWeg("Smartwatch", 99.99, 24);
    }


}