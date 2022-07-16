package RealEstate.gateways;

import RealEstate.useCases.CreateListing;
import RealEstate.useCases.DatabaseFilePath;

import java.io.*;

public class ListingsCSVController implements CsvInterface{

    private final CreateListing createListing;

    private final static DatabaseFilePath filepath = new DatabaseFilePath("Listings.csv");

    final private static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    final private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final private static BufferedWriter overWriter;

    static {
        try {
            overWriter = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListingsCSVController(CreateListing createListing) {
        this.createListing = createListing;
    }

    public CreateListing getCreateListing() {
        return createListing;
    }

    @Override
    public void read() throws IOException {

    }

    @Override
    public void write() throws IOException {

    }
}

