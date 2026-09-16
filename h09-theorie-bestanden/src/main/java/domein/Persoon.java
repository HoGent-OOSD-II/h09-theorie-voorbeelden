package domein;

import java.io.Serializable;


public class Persoon implements Serializable {

    // 1. Versiebeheer: voorkomt InvalidClassException bij wijzigingen aan deze klasse
    private static final long serialVersionUID = 1L;

    private String naam;
    private int leeftijd;

    // 3. Transient: Wordt NIET opgeslagen (veiligheid/privacy)
    private transient String wachtwoord;



    public Persoon(String naam, int leeftijd, String wachtwoord) {
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String toString() {
        return String.format("Naam: %s, Leeftijd: %d, Wachtwoord: %s",
                naam, leeftijd, wachtwoord);
    }
}