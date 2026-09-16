package domein;

public class Product {
    private final String naam;
    private final double prijs;
    private final int voorraad;

    public Product(String naam, double prijs, int voorraad) {
        this.naam = naam;
        this.prijs = prijs;
        this.voorraad = voorraad;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    // Object als tekst weergeven (bv. "Laptop 999.99 5")
    @Override
    public String toString() {
        return String.format("%s %.2f %d", naam, prijs, voorraad);
    }
}