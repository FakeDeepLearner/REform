package RealEstate.entities;
import LoginSystem.entities.User;

import java.util.ArrayList;

public class Seller extends User {

    // TODO: decide how to implement the Admin status
    private boolean isAdmin = false;
    private ArrayList<Listing> listings;
    private final MessageContainer<Integer, Message> inbox;
    private final MessageContainer<Integer, Message> outbox;

    public Seller(String username, String password) {
        super(username, password);
        listings = new ArrayList<>();
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Seller(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
        inbox = new MessageContainer<>();
        outbox = new MessageContainer<>();
    }

    public Seller(String username, String password, ArrayList<String> loginHistory,
                  MessageContainer<Integer, Message> inbox,
                  MessageContainer<Integer, Message> outbox) {
        super(username, password, loginHistory);
        this.inbox = inbox;
        this.outbox = outbox;
    }

    // TODO: move to use case?

    public void createListing(Listing l){
        this.listing = l;
    }
    public void removeListing(){
        this.listing = null;
    }
    public void changeListingPrice(){}

    public ArrayList<Listing> getListings() {
        return listings;
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
    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public void removeListing(Listing listing){
        listings.remove(listing);
    }

    @Override
    public boolean isAdmin() {
        return this.isAdmin;
    }
}
