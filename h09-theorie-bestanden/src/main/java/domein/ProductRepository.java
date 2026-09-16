package domein;

import persistentie.ProductMapper;

import java.util.List;

public class ProductRepository {

    private ProductMapper pm;

    public ProductRepository() {
        pm = new ProductMapper();
    }

    public void voegToe(Product p) {
        pm.addProduct(p);
    }

    public List<Product> geefAlleProducten() {
        return pm.readProducts();
    }
}