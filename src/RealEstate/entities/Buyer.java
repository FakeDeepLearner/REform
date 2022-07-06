package RealEstate.entities;

import LoginSystem.entities.User;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User {
    private final ArrayList<Integer> favorites;
    private final MessageContainer<Integer, Message> inbox;
    private final MessageContainer<Integer, Message> outbox;

    public Buyer(String username, String password) {
        super(username, password);
        this.favorites = new ArrayList<>();
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Buyer(String username, String password, ArrayList<Integer> favorites) {
        super(username, password);
        this.favorites = favorites;
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Buyer(String username, String password, ArrayList<String> loginHistory,
                 ArrayList<Integer> favorites) {
        super(username, password, loginHistory);
        this.favorites = favorites;
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
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
    public ArrayList<Integer> getFavList() {
        return favorites;
    }

    public MessageContainer<Integer, Message> getInbox() {
        return inbox;
    }

    public MessageContainer<Integer, Message> getOutbox() {
        return outbox;
    }

    public void printInbox() {
        System.out.println(inbox);
    }

    public void printOutbox() {
        System.out.println(outbox);
    }

    public void addFavouriteListing(int id){
        favorites.add(id);
    }

    public void removeFavouriteListing(int id) {
        favorites.remove(id);
    }
}
