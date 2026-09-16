package persistentie;

import domein.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;

public class ProductMapper {

    public void addProductClassicTryCatch(Product product) {
        OutputStream outStream = null;
        try {
            outStream = Files.newOutputStream(geefOutputPad("producten.txt"),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Formatter output = new Formatter(outStream);
            output.format("%s %s %s%n", product.getNaam(), product.getPrijs(),
                    product.getVoorraad());
        } catch (IOException e) {
            exitApplication("Fout bij schrijven.");
        } finally {
            closeStream(outStream);  // <1>
        }
    }

    // Helper methode om veilig een stream veilig te sluiten
    private void closeStream(OutputStream stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                System.err.println("Fout bij sluiten.");
            }
        }
    }

    
    public void addProduct(Product product) {
        try (Formatter output = new Formatter(
                Files.newOutputStream(geefOutputPad("producten.txt"),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND))) { // <1>
            output.format("%s %s %s%n", product.getNaam(), product.getPrijs(),
                    product.getVoorraad()); // <2>
        } catch (FormatterClosedException fe) { // <3>
            exitApplication("Error writing to file.");
        } catch (InvalidPathException ie) { // <4>
            exitApplication("Error finding file.");
        } catch (IOException e) { // <5>
            exitApplication("Error opening file.");
        }
    }



    public List<Product> readProducts() {
        List<Product> lijst = new ArrayList<>();

        try (Scanner input = new Scanner(Files.newInputStream(
                geefInputPad("producten.txt")))) { // <1>
            while (input.hasNext()) {
                lijst.add(new Product(input.next(),
                        Double.parseDouble(input.next()), Integer.parseInt(input.next()))); // <2>
            }
        } catch (InputMismatchException elementException) { // <3>
            exitApplication("File improperly formed.");
        } catch (NoSuchElementException elementException) { // <4>
            exitApplication("Element missing");
        } catch (IllegalStateException stateException) { // <5>
            exitApplication("Error reading from file.");
        } catch (InvalidPathException ie) { // <6>
            exitApplication("Error finding file.");
        } catch (IOException ex) { // <7>
            exitApplication("Error opening file.");
        }
        return lijst;
    }



    public List<Product> readDataWithStreams() {
        List<Product> lijst = new ArrayList<>();

        try (Stream<String> stream = Files.lines(
                Path.of("src", "bestanden", "producten.txt"))) { // <1>
            stream.forEach(line -> {
                String[] split = line.split(" "); // <2>
                lijst.add(new Product(
                        split[0],
                        Double.parseDouble(split[1].replaceAll(",", ".")),
                        Integer.valueOf(split[2])));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lijst;
    }


    // INPUT: Moet een bestaand bestand in target zijn
    private Path geefInputPad(String bestandsnaam) {
        URL url = PersoonMapper.class.getResource("/bestanden/" + bestandsnaam);
        if (url == null) {
            exitApplication("Bestand niet gevonden: bestanden/" + bestandsnaam);
        }
        try {
            return Path.of(url.toURI());
        } catch (Exception e) {
            exitApplication("Kan bestand niet openen: " + bestandsnaam);
            throw new IllegalStateException(e);
        }
    }

    // OUTPUT: Schrijven naar een bestand in target (mag nieuw zijn)
    private Path geefOutputPad(String bestandsnaam) {
        Path pad = Path.of("target", "classes", "bestanden", bestandsnaam);
        try {
            Files.createDirectories(pad.getParent());
            return pad;
        } catch (IOException e) {
            exitApplication("Kan map niet maken voor: " + bestandsnaam);
            throw new IllegalStateException(e);
        }
    }

    private void exitApplication(String message) {
        System.err.println(message);
        System.exit(1);
    }
}