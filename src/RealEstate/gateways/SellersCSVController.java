package RealEstate.gateways;

import RealEstate.entities.Seller;
import RealEstate.useCases.CreateSeller;
import RealEstate.useCases.DatabaseFilePath;
import RealEstate.useCases.UserFactory;

import java.io.*;
import java.util.HashMap;

public class SellersCSVController implements CsvInterface{
    private final UserFactory userFactory;

    private final static DatabaseFilePath filepath = new DatabaseFilePath("Sellers.csv");

    public SellersCSVController(UserFactory userFactory){
        this.userFactory = userFactory;
    }

    public UserFactory getCreateSeller() {
        return userFactory;
    }



    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            String username = splitLine[0];
            String password = splitLine[1];
            userFactory.createUser("seller", username, password);
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, Seller> createdSellers = userFactory.getCreatedSellers();
        for(String username : createdSellers.keySet()){
            writer.write("\n" + username + "," + createdSellers.get(username).getPassword());
        }
        writer.close();
    }
}
