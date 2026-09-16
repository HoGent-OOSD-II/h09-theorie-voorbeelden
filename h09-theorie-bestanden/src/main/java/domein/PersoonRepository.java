package domein;

import persistentie.PersoonMapper;

import java.util.List;

public class PersoonRepository {

    private PersoonMapper pm;

    public PersoonRepository() {
        pm = new PersoonMapper();
    }

    public void voegToe(Persoon p) {
        pm.addPersoon(p);
    }

    public List<Persoon> geefAllePersonen() {
        return pm.readData();
    }

    public void sluitAf() {
        pm.closeFile();
    }
}