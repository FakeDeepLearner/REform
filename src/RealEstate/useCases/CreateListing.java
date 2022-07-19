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

    /**
     * Constructor for CreateListing
     * @param listingsInterface the listing's container hashmap
     * @param userContainer the user container hashmap
     */
    public CreateListing(ListingContainer<Integer, Listing> listingsInterface,
                         UserContainer<String, User> userContainer) {
        listings = listingsInterface;
        this.userContainer = userContainer;
        createdListings = new HashMap<>();
    }

    /**
     * Add a listing into the listings container.
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param price the price of the property
     * @return a listing object for the property
     */
    public Listing addListing(int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price) {


//        int uniqueID = GenerateUniqueID.getUniqueID();
        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();

        Listing listing = new Listing(uniqueID, civicAddress, streetName, city, type, bedrooms, bathrooms, price);
        listings.put(uniqueID, listing);
        return listing;
    }

    /**
     * Add a listing into the listings container.
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param price the price of the property
     * @return a listing object for the property
     */
    public Listing addListing(int unitNumber, int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price) {

        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();
        Listing listing = new Listing(uniqueID, unitNumber, civicAddress, streetName, city, type,
                bedrooms, bathrooms, floors, price);
        listings.put(uniqueID, listing);
        return listing;
    }

    /**
     * Add a listing into the listings container.
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param price the price of the property
     * @return a listing object for the property
     */
    public Listing addListing(int ID, int unitNumber, int civicAddress, String streetName, String city,
                              String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price) {
        Listing listing = new Listing(ID, unitNumber, civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price);
        listings.put(ID, listing);
        return listing;
    }

    /**
     * Add a listing to a seller's listings
     * @param username the username of the seller
     * @param listing the listing to be added
     */
    public void addListingToSeller(String username, Listing listing) {
        Seller seller = userContainer.getSeller(username);
        seller.addListing(listing);
    }

    /**
     * Return the newly created listings
     * @return a hashmap of the newly created listings
     */
    public HashMap<String, ArrayList<Listing>> getCreatedListings() {
        return createdListings;
    }

    /**
     * Return the user container
     * @return the user container hashmap
     */
    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    /**
     * Return the listings container
     * @return the listings container hashmap
     */
    public ListingContainer<Integer, Listing> getListings() {
        return listings;
    }

    /**
     * Add a listing to the createsListings hashmap
     * @param username the username of the user who is listing the property
     * @param listing the listing to be added to the hashmap
     */
    public void addListingToCreatedListings(String username, Listing listing) {
        if (createdListings.containsKey(username)) {
            createdListings.get(username).add(listing);
        } else {
            ArrayList<Listing> value = new ArrayList<>();
            value.add(listing);
            createdListings.put(username, value);
        }
    }

    /**
     * Remove a listing from the createdListings hashmap
     * @param username the username of the user who made the listing
     * @param listing the listing to be removed from the hashmap
     */
    public void removeFromCreatedListing(String username, Listing listing) {
        if (createdListings.containsKey(username)) {
            createdListings.get(username).remove(listing);
        }
    }

    /**
     * Get a seller's listings
     * @param username the username of the seller
     * @return an arraylist of the string representations of the listings
     */
    public ArrayList<String> getSellerListingsStrings(String username){
        ArrayList<String> listings = new ArrayList<>();
        Seller u = userContainer.getSeller(username);
        for (Listing l : u.getListings()){
            listings.add(l.toString());
        }
        return listings;
    }

    /**
     * Get an arraylist of a seller's listings
     * @param username the username of the seller
     * @return an arraylist of listings for this seller
     */
    public ArrayList<Listing> getSellerListings(String username){
        Seller u = userContainer.getSeller(username);
        return u.getListings();
    }

    /**
     * Delete a listing from the listings
     * @param username the username for the user
     * @param listing the listing to be deleted
     */
    public void deleteListing(String username, Listing listing){
        Seller user = userContainer.getSeller(username);
        int id = listing.getId();
        user.removeListing(listings.get(id));
        listings.remove(id);
    }
}
