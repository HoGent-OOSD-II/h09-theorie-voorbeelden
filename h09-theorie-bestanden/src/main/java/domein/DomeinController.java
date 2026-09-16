package domein;

import java.util.List;

public class DomeinController {

    private final ProductRepository productRepo;
    private final PersoonRepository persoonRepo;

    public DomeinController() {
        productRepo = new ProductRepository();
        persoonRepo = new PersoonRepository();
    }

    public void schrijfProductWeg(String naam, double prijs, int voorraad) {
        Product p = new Product(naam, prijs, voorraad);
        productRepo.voegToe(p);
    }

    public String leesProductenUitBestand() {
        StringBuilder uitvoer = new StringBuilder();
        List<Product> lijst = productRepo.geefAlleProducten();

        for (Product product : lijst) {
            uitvoer.append(String.format("%-12s%-10.2f%-10d\n",
                    product.getNaam(), product.getPrijs(),
                    product.getVoorraad()));
        }
        return uitvoer.toString();
    }

    public void schrijfPersoonWeg(String naam, int leeftijd) {
        Persoon p = new Persoon(naam, leeftijd, "wachtwoord");
        persoonRepo.voegToe(p);
    }

    public String leesPersonenBestand() {
        StringBuilder uitvoer = new StringBuilder();
        List<Persoon> lijst = persoonRepo.geefAllePersonen();

        for (Persoon persoon : lijst) {
            uitvoer.append(persoon).append(System.lineSeparator());
        }

        return uitvoer.toString();
    }

    public void sluitAf() {
        persoonRepo.sluitAf();
    }
}