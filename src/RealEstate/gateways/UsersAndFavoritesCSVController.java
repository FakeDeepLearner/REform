package RealEstate.gateways;

import RealEstate.useCases.FavoriteListing;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersAndFavoritesCSVController implements CsvInterface{
    final private static String filepath = "src/RealEstate/database/UsersAndFavorites.csv";
    final private FavoriteListing favoriteListing;

    public UsersAndFavoritesCSVController(FavoriteListing favoriteListing){
        this.favoriteListing = favoriteListing;
    }

    public FavoriteListing getFavoriteListing() {
        return favoriteListing;
    }

    final private static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    final private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(filepath, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final private static BufferedWriter overWriter;

    static {
        try {
            overWriter = new BufferedWriter(new FileWriter(filepath, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void read() throws IOException {
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            String username = splitLine[0];
            int listingID = Integer.parseInt(splitLine[1]);
            favoriteListing.addListingToBuyerFavorites(username, listingID);

        }
    }

    @Override
    public void write() throws IOException {
        HashMap<String, ArrayList<Integer>> favorites = favoriteListing.getGeneratedFavorites();
        for (String username : favorites.keySet()){
            ArrayList<Integer> usernameFavorites = favorites.get(username);
            for (Integer integer : usernameFavorites){
                writer.write(username + "," + integer);
            }
        }
    }

}
