package entities;
import java.util.ArrayList;

public class Seller extends User{

    // TODO: decide how to implement the Admin satus
    private boolean isAadmin = false;
    private Listing listing;
    private boolean listingIsSold = false;

    public Seller(String username, String password) {
        super(username, password);
    }

    public Seller(String username, String password, Listing l) {
        super(username, password);
        this.listing = l;
    }

    public Seller(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
    }
    public Seller(String username, String password, ArrayList<String> loginHistory, Listing l) {
        super(username, password, loginHistory);
        this.listing = l;
    }

    // TODO: move to use case?
    public void createListing(Listing l){
        this.listing = l;
    }
    public void removeListing(){
        this.listing = null;
    }
    public void changeListingPrice(){}

    public void acceptOffer(Offer o){
        this.listingIsSold = true;
    }


    // Getters and setters
    public Listing getListing() {
        return listing;
    }
    public boolean isListingIsSold() {
        return listingIsSold;
    }
    public void setListing(Listing listing) {
        this.listing = listing;
    }
    public void setListingIsSold(boolean listingIsSold) {
        this.listingIsSold = listingIsSold;
    }

    @Override
    public boolean isAdmin() {
        return this.isAadmin;
    }
}
