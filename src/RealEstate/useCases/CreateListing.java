package RealEstate.useCases;

import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.Seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateListing {
    private final ListingContainer<Integer, Listing> listings;
    private final UserContainer<String, User> userContainer;
    private final HashMap<String, ArrayList<Listing>> createdListings;

    public CreateListing(ListingContainer<Integer, Listing> listingsInterface,
                         UserContainer<String, User> userContainer) {
        listings = listingsInterface;
        this.userContainer = userContainer;
        createdListings = new HashMap<>();
    }

    public Listing addListing(int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price) {


//        int uniqueID = GenerateUniqueID.getUniqueID();
        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();

        Listing listing = new Listing(uniqueID, civicAddress, streetName, city, type, bedrooms, bathrooms, price);
        listings.put(uniqueID, listing);
        return listing;
    }

    public Listing addListing(int unitNumber, int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price) {

        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();
        Listing listing = new Listing(uniqueID, unitNumber, civicAddress, streetName, city, type,
                bedrooms, bathrooms, floors, price);
        listings.put(uniqueID, listing);
        return listing;
    }

    public Listing addListing(int ID, int unitNumber, int civicAddress, String streetName, String city,
                              String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price) {
        Listing listing = new Listing(ID, unitNumber, civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price);
        listings.put(ID, listing);
        return listing;
    }

    public void addListingToSeller(String username, Listing listing) {
        Seller seller = userContainer.getSeller(username);
        seller.addListing(listing);
    }

    public HashMap<String, ArrayList<Listing>> getCreatedListings() {
        return createdListings;
    }

    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    public ListingContainer<Integer, Listing> getListings() {
        return listings;
    }

    public void addListingToCreatedListings(String username, Listing listing) {
        if (createdListings.containsKey(username)) {
            createdListings.get(username).add(listing);
        } else {
            ArrayList<Listing> value = new ArrayList<>();
            value.add(listing);
            createdListings.put(username, value);
        }
    }

    public void removeFromCreatedListing(String username, Listing listing) {
        if (createdListings.containsKey(username)) {
            createdListings.get(username).remove(listing);
        }
    }

    public ArrayList<String> getSellerListingsStrings(String username){
        ArrayList<String> listings = new ArrayList<>();
        Seller u = userContainer.getSeller(username);
        for (Listing l : u.getListings()){
            listings.add(l.toString());
        }
        return listings;
    }

    public ArrayList<Listing> getSellerListings(String username){
        Seller u = userContainer.getSeller(username);
        return u.getListings();
    }

    public void deleteListing(String username, Listing listing){
        Seller user = userContainer.getSeller(username);
        int id = listing.getId();
        listings.remove(id);
        user.removeListing(listings.get(id));
    }


}
