package gateways;

import entities.Seller;
import entities.User;
import entities.containers.UserContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public non-sealed class SellersCSVController extends CSVController {
    private final UserContainer<String, User> users;

    public SellersCSVController(UserContainer<String, User> users){
        super("Sellers.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, Seller> createdSellers = users.getAllSellers();
        for(String username : createdSellers.keySet()){
            writer.write(username + "," + createdSellers.get(username).getPassword() + "\n");
        }
        writer.close();
    }
}
