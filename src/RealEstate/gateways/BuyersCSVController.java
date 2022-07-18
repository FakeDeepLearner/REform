package RealEstate.gateways;

import RealEstate.entities.Buyer;
import RealEstate.useCases.CreateBuyer;
import RealEstate.useCases.DatabaseFilePath;

import java.io.*;
import java.util.HashMap;

public class BuyersCSVController implements CsvInterface{
    final private CreateBuyer createBuyer;
    final private static DatabaseFilePath filepath = new DatabaseFilePath("Buyers.csv");

    public BuyersCSVController(CreateBuyer createBuyer) {
        this.createBuyer = createBuyer;
    }

    public CreateBuyer getCreateBuyer() {
        return createBuyer;
    }

    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            String username = splitLine[0];
            String password = splitLine[1];
            createBuyer.createNewBuyer(username, password);
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), true));
        HashMap<String, Buyer> createdBuyers = createBuyer.getCreatedBuyers();
        for(String username : createdBuyers.keySet()){
           writer.write(username + "," + createdBuyers.get(username).getPassword());
           writer.write("\n");
        }
        writer.close();
    }
}
