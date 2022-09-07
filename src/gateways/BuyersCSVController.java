package gateways;

import entities.Buyer;
import entities.User;
import entities.containers.UserContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public non-sealed class BuyersCSVController extends CSVController {
    private final UserContainer<String, User> users;

    public BuyersCSVController(UserContainer<String, User> users) {
        super("Buyers.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, Buyer> createdBuyers = users.getAllBuyers();
        for (String username : createdBuyers.keySet()){
            writer.write(username + "," + createdBuyers.get(username).getPassword() + "\n");
        }
        writer.close();
    }
}
