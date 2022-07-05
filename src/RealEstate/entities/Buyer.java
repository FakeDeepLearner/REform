package RealEstate.entities;
import LoginSystem.entities.User;
import java.util.ArrayList;
import java.util.HashMap;



public class Buyer extends User {

    private String username;
    private String password;

    // TODO: decide how to implement the Admin satus
    private boolean isAdmin = false;
    private final ArrayList<Listing> favorites;
    private final MessageContainer<Integer, Message> inbox;
    private final MessageContainer<Integer, Message> outbox;

    public Buyer(String username, String password) {
        super(username, password);
        this.favorites = new ArrayList<>();
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Buyer(String username, String password, ArrayList<Listing> favorites) {
        super(username, password);
        this.favorites = favorites;
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Buyer(String username, String password, ArrayList<String> loginHistory,
                 ArrayList<Listing> favorites) {
        super(username, password, loginHistory);
        this.favorites = favorites;
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public HashMap<Seller, String> sendOfferMessage(Seller receiver, String message) {
        HashMap<Seller, String> offer = new HashMap<Seller, String>();
        offer.put(receiver, message);

        return offer;
    }

    public HashMap<Object, ArrayList<Object>> sendOfferMessageWithPrice(Seller receiver, String message, double price) {
        HashMap<Object, ArrayList<Object>> offer = new HashMap<Object, ArrayList<Object>>();

        ArrayList<Object> messageAndPrice = new ArrayList<>();
        messageAndPrice.add(message);
        messageAndPrice.add(price);

        offer.put(receiver, messageAndPrice);

        return offer;
    }


    // Getters and setters
    public ArrayList<Listing> getFavList() {
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

    public void addListingToFavList(Listing listing){
        this.favorites.add(listing);
    }

    public void removeListingFromFavList(Listing listing) {
        favorites.remove(listing);
    }



    @Override
    public boolean isAdmin() {
        return this.isAdmin;
    }
}
