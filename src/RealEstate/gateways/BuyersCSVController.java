package RealEstate.gateways;

import RealEstate.useCases.CreateBuyer;
import RealEstate.useCases.DatabaseFilePath;

import javax.xml.crypto.Data;
import java.io.*;

public class BuyersCSVController implements CsvInterface{
    final private CreateBuyer createBuyer;
    final private static DatabaseFilePath filepath = new DatabaseFilePath("Buyers.csv");

    public BuyersCSVController(CreateBuyer createBuyer) {
        this.createBuyer = createBuyer;
    }

    public CreateBuyer getCreateBuyer() {
        return createBuyer;
    }

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

    @Override
    public void read() throws IOException {

    }

    @Override
    public void write() throws IOException {

    }
}