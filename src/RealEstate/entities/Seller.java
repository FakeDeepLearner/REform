package RealEstate.entities;
import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;

import java.util.ArrayList;

public class Seller extends NonAdminUser {
    private ArrayList<Listing> listings;


    public Seller(String username, String password) {
        super(username, password);
        listings = new ArrayList<>();
    }

    public Seller(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
    }

    public Seller(String username, String password, ArrayList<String> loginHistory,
                  MessageContainer<Integer, Message> inbox,
                  MessageContainer<Integer, Message> outbox) {
        super(username, password, loginHistory);
        this.inbox = inbox;
        this.outbox = outbox;
    }

    public void addListing(Listing listing){
        listings.add(listing);
    }

    public void removeListing(Listing listing){
        // This workaround is to make sure we don't call the overloaded method remove(int index)
        listings.remove(listing);
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append("Username: ").append(getUsername()).append("\n").append("Listings:\n\n");
        for (Listing listing : listings) {
            builder.append(listing).append("\n");
        }
        return builder.toString();
    }

    public String listingsAsString(){
        return listings.toString();
    }
}
