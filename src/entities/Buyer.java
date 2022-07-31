package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends NonAdminUser {
    private final ArrayList<Integer> favorites;

    /**
     * Create a buyer class
     *
     * @param username Username of the buyer
     * @param password Password of the buyer
     */
    public Buyer(String username, String password) {
        super(UserType.BUYER, username, password);
        this.favorites = new ArrayList<>();

    }

    /**
     * @param username  Username of the buyer
     * @param password  Password of the buyer
     * @param favorites Arraylist of favourite listings IDs
     */
    public Buyer(String username, String password, ArrayList<Integer> favorites) {
        super(UserType.BUYER, username, password);
        this.favorites = favorites;
    }

    /**
     * @param username     Username of the buyer
     * @param password     Password of the buyer
     * @param loginHistory Arraylist of the login history of the buyer
     * @param favorites    Arraylist of favourite listings IDs
     */

    public Buyer(String username, String password, ArrayList<String> loginHistory,
                 ArrayList<Integer> favorites) {
        super(UserType.BUYER, username, password, loginHistory);
        this.favorites = favorites;
    }

    /**
     * Create an offer message
     *
     * @param receiver Seller who will receive the offer
     * @param message  Message of the offer
     * @return A hashmap with the seller as the key and the message as the value
     */
    public HashMap<Seller, String> sendOfferMessage(Seller receiver, String message) {
        HashMap<Seller, String> offer = new HashMap<>();
        offer.put(receiver, message);

        return offer;
    }

    /**
     * Create an offer message with a price
     *
     * @param receiver Seller who will receive the message and the price offer
     * @param message  Message of the offer
     * @param price    Offered price
     * @return Hashmap of the seller as the key and an ArrayList of message and price as the key
     */
    public HashMap<Object, ArrayList<Object>> sendOfferMessageWithPrice(Seller receiver, String message, double price) {
        HashMap<Object, ArrayList<Object>> offer = new HashMap<>();

        ArrayList<Object> messageAndPrice = new ArrayList<>();
        messageAndPrice.add(message);
        messageAndPrice.add(price);

        offer.put(receiver, messageAndPrice);

        return offer;
    }

    // Getters and setters

    /**
     * @return Arraylist of favoirites
     */
    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    /**
     * @param id ID of a listing to add to the favorites
     */
    public void addFavouriteListing(int id) {
        favorites.add(id);
    }

    /**
     * @param id ID of a listing to remove from the favorites
     */
    public void removeFavouriteListing(int id) {
        favorites.remove(id);
    }

    /**
     * @return String representation of the Buyer class
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getUsername()).append("\n").append("Favorite Listings:\n");
        for (Integer integer : favorites) {
            builder.append(integer).append(" ");
        }
        return builder.toString();
    }
}
