package RealEstate.gateways;

import RealEstate.useCases.DatabaseFilePath;
import RealEstate.useCases.FavoriteListing;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersAndFavoritesCSVController implements CsvInterface{
    final private static DatabaseFilePath filepath = new DatabaseFilePath("UsersAndFavorites.csv");
    final private FavoriteListing favoriteListing;

    public UsersAndFavoritesCSVController(FavoriteListing favoriteListing){
        this.favoriteListing = favoriteListing;
    }

    public FavoriteListing getFavoriteListing() {
        return favoriteListing;
    }

    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            String username = splitLine[0];
            int listingID = Integer.parseInt(splitLine[1]);
            favoriteListing.addListingToBuyerFavorites(username, listingID);
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), true));
        HashMap<String, ArrayList<Integer>> favorites = favoriteListing.getGeneratedFavorites();
        for (String username : favorites.keySet()){
            ArrayList<Integer> usernameFavorites = favorites.get(username);
            for (Integer integer : usernameFavorites){
                writer.write("\n" + username + "," + integer);
            }
        }
        writer.close();
    }
}
