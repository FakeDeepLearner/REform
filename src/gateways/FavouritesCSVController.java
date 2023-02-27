package gateways;

import entities.users.Buyer;
import entities.users.User;
import entities.containers.UserContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public non-sealed class FavouritesCSVController extends CSVController {
    final private UserContainer<String, User> users;

    public FavouritesCSVController(UserContainer<String, User> users){
        super("Favourites.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));

        for (String username : users.getAllBuyers().keySet()) {
            Buyer u = users.getBuyer(username);
            for (Integer id : u.getFavorites()) {
                String lineToWrite = username + "," + id + "\n";

                writer.write(lineToWrite);
            }
        }

        writer.close();
    }
}
