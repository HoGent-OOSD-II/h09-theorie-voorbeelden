package ui;

import domein.DomeinController;

public class UitlezenApplicatie {
	
	public static void main(String[] args) {
		start();
	}
	
	private static void start() {
		DomeinController dc = new DomeinController();
		
		System.out.printf("%-12s%-10s%-10s%n", "Product",
                "Prijs", "Voorraad");
		
		System.out.println(dc.leesProductenUitBestand());
	}
	
}