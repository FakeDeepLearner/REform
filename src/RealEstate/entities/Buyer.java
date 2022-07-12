package RealEstate.entities;

import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends NonAdminUser {
    private final ArrayList<Integer> favorites;

    public Buyer(String username, String password) {
        super(username, password);
        this.favorites = new ArrayList<>();

    }

    public Buyer(String username, String password, ArrayList<Integer> favorites) {
        super(username, password);
        this.favorites = favorites;
    }

    public Buyer(String username, String password, ArrayList<String> loginHistory,
                 ArrayList<Integer> favorites) {
        super(username, password, loginHistory);
        this.favorites = favorites;
    }

    public HashMap<Seller, String> sendOfferMessage(Seller receiver, String message) {
        HashMap<Seller, String> offer = new HashMap<>();
        offer.put(receiver, message);

        return offer;
    }

    public HashMap<Object, ArrayList<Object>> sendOfferMessageWithPrice(Seller receiver, String message, double price) {
        HashMap<Object, ArrayList<Object>> offer = new HashMap<>();

        ArrayList<Object> messageAndPrice = new ArrayList<>();
        messageAndPrice.add(message);
        messageAndPrice.add(price);

        offer.put(receiver, messageAndPrice);

        return offer;
    }

    // Getters and setters
    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    public void addFavouriteListing(int id){
        favorites.add(id);
    }

    public void removeFavouriteListing(int id) {
        favorites.remove(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append(getUsername()).append("\n").append("Favorite Listings:\n");
        for (Integer integer : favorites) {
            builder.append(integer).append(" ");
        }
        return builder.toString();
    }
}
