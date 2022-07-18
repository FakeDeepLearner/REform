package RealEstate.gateways;

import RealEstate.entities.Seller;
import RealEstate.useCases.CreateSeller;
import RealEstate.useCases.DatabaseFilePath;

import java.io.*;
import java.util.HashMap;

public class SellersCSVController implements CsvInterface{
    private final CreateSeller createSeller;

    private final static DatabaseFilePath filepath = new DatabaseFilePath("Sellers.csv");

    public SellersCSVController(CreateSeller createSeller){
        this.createSeller = createSeller;
    }

    public CreateSeller getCreateSeller() {
        return createSeller;
    }



    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            String username = splitLine[0];
            String password = splitLine[1];
            createSeller.createNewSeller(username, password);
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), true));
        HashMap<String, Seller> createdSellers = createSeller.getCreatedSellers();
        for(String username : createdSellers.keySet()){
            writer.write(username + "," + createdSellers.get(username).getPassword());
            writer.write("\n");
        }
        writer.close();
    }
}
