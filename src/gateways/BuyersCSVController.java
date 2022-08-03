package gateways;

import entities.Buyer;
import entities.User;
import entities.containers.UserContainer;
import useCases.CSVUseCases.DatabaseFilePath;
import useCases.userUseCases.UserFactory;

import java.io.*;
import java.util.HashMap;

public class BuyersCSVController implements CsvInterface {
    private final UserContainer<String, User> users;
    private final UserFactory userFactory;
    private final static DatabaseFilePath filepath = new DatabaseFilePath("Buyers.csv");

    public BuyersCSVController(UserContainer<String, User> users, UserFactory userFactory) {
        this.userFactory = userFactory;
        this.users = users;
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
        HashMap<String, Buyer> createdBuyers = users.getAllBuyers();
        for(String username : createdBuyers.keySet()){
           writer.write(username + "," + createdBuyers.get(username).getPassword() + "\n");
        }
        writer.close();
    }
}
