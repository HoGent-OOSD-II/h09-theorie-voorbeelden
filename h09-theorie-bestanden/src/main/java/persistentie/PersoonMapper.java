package persistentie;

import domein.Persoon;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PersoonMapper {

    private ObjectOutputStream output;  // <1>

    private void openFileForWriting() {
        try {  // <2>
            OutputStream outStream = Files.newOutputStream(geefOutputPad("personen.ser"));
            output = new ObjectOutputStream(outStream);
        } catch (InvalidPathException ie) {
            exitApplication("Not a valid path name:");
        } catch (IOException ex) {
            exitApplication("Error opening file ");
        }
    }


    public void addPersoon(Persoon persoon) {
        try {
            if (output == null) openFileForWriting(); // <1>
            output.writeObject(persoon);// <2>
        } catch (IOException ex) {
            System.err.println("Error writing to file.");
        }
    }


    private ObjectInputStream openFileForReading() {
        try {
            InputStream inStream = Files.newInputStream(geefInputPad("personen.ser"));
            return new ObjectInputStream(inStream);
        } catch (InvalidPathException ie) {
            exitApplication("Not a valid path name:");
        } catch (IOException ex) {
            exitApplication("Error opening file ");
        }
        return null;
    }

    public List<Persoon> readData() {
        List<Persoon> allePersonen = new ArrayList<>();
        try (ObjectInputStream input = openFileForReading()) {
            while (true) {  // <1>
                Persoon persoon = (Persoon) input.readObject(); // <2>
                allePersonen.add(persoon);
            }
        } catch (EOFException e) {  // <3>
        } catch (ClassNotFoundException ex) { // <4>
            exitApplication("Original class of deserialized objects cannot be found");
        } catch (IOException e1) {  // <5>
            exitApplication("Error reading from file");
        }
        return allePersonen;
    }

    // bestandsnaam is de naam van het bestand die we willen openen
    private Path geefInputPad(String bestandsnaam) {
        URL url = PersoonMapper.class.getResource("/bestanden/" + bestandsnaam); // <1>
        if (url == null) {
            exitApplication("Bestand niet gevonden: bestanden/" + bestandsnaam); // <2>
        }
        try {
            return Path.of(url.toURI()); // <3>
        } catch (Exception e) {
            exitApplication("Kan bestand niet openen: " + bestandsnaam);
            throw new IllegalStateException(e); // <4>
        }
    }

    private Path geefOutputPad(String bestandsnaam) {
        Path pad = Path.of("target", "classes", "bestanden", bestandsnaam); // <1>
        try {
            // Maak parent directories als die nog niet bestaan (target/classes/bestanden)
            Files.createDirectories(pad.getParent()); // <2>
            return pad;
        } catch (IOException e) {
            // Kan niet schrijven naar target map (bijv. permissions probleem)
            exitApplication("Kan map niet maken voor: " + bestandsnaam);
            throw new IllegalStateException(e); // <3>
        }
    }

    private void exitApplication(String message) {
        System.err.println(message);
        System.exit(1);
    }

    public void closeFile() {
        try {
            if (output != null)
                output.close();
        } catch (IOException ioe) {
            exitApplication("Error closing file");
        }
    }
}