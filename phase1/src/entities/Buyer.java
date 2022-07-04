package entities;

import java.util.ArrayList;
import java.util.HashMap;


public class Buyer extends User {

    private String username;
    private String password;

    // TODO: decide how to implement the Admin satus
    private boolean isAadmin = false;
    private ArrayList<Listing> favList;

    public Buyer(String username, String password) {
        super(username, password);
    }

    public Buyer(String username, String password, ArrayList<Listing> favList) {
        super(username, password);
        this.favList = favList;
    }

    public Buyer(String username, String password, ArrayList<String> loginHistory, ArrayList<Listing> favList) {
        super(username, password, loginHistory);
        this.favList = favList;
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
        return favList;
    }

    public void setFavList(ArrayList<Listing> favList) {
        this.favList = favList;
    }

    public void addListingToFavList(Listing l){
        this.favList.add(l);
    }

    @Override
    public boolean isAdmin() {
        return this.isAadmin;
    }
}
