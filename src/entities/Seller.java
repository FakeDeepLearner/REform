package entities;
import entities.containers.MessageContainer;

import java.util.ArrayList;

public class Seller extends NonAdminUser {
    private ArrayList<Listing> listings;

    /**
     * Create a Seller class
     * @param username      Username of the seller
     * @param password      Password of the seller
     */
    public Seller(String username, String password) {
        super(username, password);
        listings = new ArrayList<>();
    }

    /**
     * @param username      Username of the seller
     * @param password      Password of the seller
     * @param loginHistory  Login History of the seller
     */
    public Seller(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
    }

    /**
     * @param username      Username of the seller
     * @param password      Password of the seller
     * @param loginHistory  Login History of the seller
     * @param inbox         Message inbox of the seller
     * @param outbox        Message outbox of the seller
     */
    public Seller(String username, String password, ArrayList<String> loginHistory,
                  MessageContainer<Integer, Message> inbox,
                  MessageContainer<Integer, Message> outbox) {
        super(username, password, loginHistory);
        this.inbox = inbox;
        this.outbox = outbox;
    }

    /**
     * Add a Listing class to listings
     * @param listing       Listing object to add to seller's listings
     */
    public void addListing(Listing listing){
        listings.add(listing);
    }

    /**
     * Remove a listing from seller's listings
     * @param listing       Listing object to remove from seller's listings
     */
    public void removeListing(Listing listing){
        // This workaround is to make sure we don't call the overloaded method remove(int index)
        listings.remove(listing);
    }

    /**
     *
     * @return Seller's listings
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }

    /**
     *
     * @return String representation of the seller class
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Username: ").append(getUsername()).append("\n").append("Listings:\n\n");
        for (Listing listing : listings) {
            builder.append(listing).append("\n");
        }
        return builder.toString();
    }

    /**
     *
     * @return String representation of seller's listings
     */
    public String listingsAsString(){
        return listings.toString();
    }
}
