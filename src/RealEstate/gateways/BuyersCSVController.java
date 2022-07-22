package RealEstate.gateways;
import RealEstate.entities.Buyer;
import RealEstate.useCases.DatabaseFilePath;
import RealEstate.useCases.UserFactory;

import java.io.*;
import java.util.HashMap;

public class BuyersCSVController implements CsvInterface{
    final private UserFactory userFactory;
    final private static DatabaseFilePath filepath = new DatabaseFilePath("Buyers.csv");

    public BuyersCSVController(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public UserFactory getCreateBuyer() {
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
            userFactory.createUser("buyer", username, password);
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, Buyer> createdBuyers = userFactory.getCreatedBuyers();
        for(String username : createdBuyers.keySet()){
           writer.write(username + "," + createdBuyers.get(username).getPassword() + "\n");
        }
        writer.close();
    }
}
