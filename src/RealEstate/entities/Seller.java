package RealEstate.entities;
import LoginSystem.entities.User;

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<Integer> listings;
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
}
