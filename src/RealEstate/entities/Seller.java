package RealEstate.entities;
import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;

import java.util.ArrayList;

public class Seller extends NonAdminUser {
    private ArrayList<Integer> listings;


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

    public void addListing(int id){
        listings.add(id);
    }

    public void removeListing(int id){
        // This workaround is to make sure we don't call the overloaded method remove(int index)
        listings.remove(Integer.valueOf(id));
    }

    public ArrayList<Integer> getListings() {
        return listings;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append(getUsername()).append("\n").append("Listings:\n");
        for (Integer integer : listings) {
            builder.append(integer).append(" ");
        }
        return builder.toString();
    }
}
