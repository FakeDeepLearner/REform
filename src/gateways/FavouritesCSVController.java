package gateways;

import entities.Buyer;
import entities.User;
import entities.containers.UserContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FavouritesCSVController extends CSVController {
    final private UserContainer<String, User> users;

    public FavouritesCSVController(UserContainer<String, User> users){
        super("Favourites.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), true));

        for (String username : users.getAllBuyers().keySet()) {
            Buyer u = (Buyer) users.get(username);
            for (Integer id : u.getFavorites()) {

                writer.write(username + "," + id + "\n");
            }
        }

        writer.close();
    }
}
