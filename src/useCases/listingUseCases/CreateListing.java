package useCases.listingUseCases;

import entities.User;
import entities.containers.UserContainer;
import entities.Listing;
import entities.containers.ListingContainer;
import entities.Seller;
import useCases.DataInterface;
import useCases.miscellaneous.GenerateUniqueID;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateListing {
    private final ListingContainer<Integer, Listing> listings;
    private final UserContainer<String, User> userContainer;
    private final HashMap<String, ArrayList<Listing>> createdListings;
    public DataInterface i;


    /**
     * Constructor for CreateListing
     * @param listingsInterface the listing's container hashmap
     * @param userContainer the user container hashmap
     */
    public CreateListing(ListingContainer<Integer, Listing> listingsInterface,
                         UserContainer<String, User> userContainer, DataInterface i) {
        listings = listingsInterface;
        this.userContainer = userContainer;
        createdListings = new HashMap<>();
        this.i = i;
    }

    public void read() throws IOException {
        i.read();
    }

    public void write() throws IOException {
        i.write();
    }

    /**
     * Add a listing without a unit number into the ListingContainer.
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param floors the number of floors
     * @param price the price of the property
     * @param info additional information about the listing
     * @return a listing object created with the given attributes
     */
    public Listing addListing(int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price, String info) {
        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();
        Listing listing = new Listing(uniqueID, civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price, info);
        listings.put(uniqueID, listing);
        return listing;
    }

    /**
     * Add a listing with a unit number into the ListingContainer.
     * @param unitNumber the unit number of the listing
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param price the price of the property
     * @param info additional information about the listing
     * @return a listing object created with the given attributes
     */
    public Listing addListing(int unitNumber, int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price, String info) {
        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.listings);
        int uniqueID = IDGenerator.getUniqueID();
        Listing listing = new Listing(uniqueID, unitNumber, civicAddress, streetName, city, type, bedrooms, bathrooms,
                price, info);
        listings.put(uniqueID, listing);
        return listing;
    }

    /**
     * Add a listing with a known ID and a unit number into the ListingContainer.
     * @param ID the ID of the listing
     * @param unitNumber the unit number of the listing
     * @param civicAddress the civic address
     * @param streetName the street name
     * @param city the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param price the price of the property
     * @param info additional information about the listing
     * @return a listing object with the given attributes
     */
    public Listing addListing(int ID, int unitNumber, int civicAddress, String streetName, String city, String type,
                              int bedrooms, int bathrooms, BigDecimal price, String info) {
        Listing listing = new Listing(ID, unitNumber, civicAddress, streetName, city, type, bedrooms, bathrooms, price, info);
        listings.put(ID, listing);
        return listing;
    }

    /**
     * Add a listing with a known ID and without a unit number into the ListingContainer
     * @param ID the ID of the listing
     * @param civicAddress the civic address number
     * @param streetName the street name
     * @param city the name of the city
     * @param type the type of the property. Must be in {APARTMENT, HOUSE, TOWNHOUSE}
     * @param bedrooms the number of bedrooms
     * @param bathrooms the number of bathrooms
     * @param floors the number of floors
     * @param price the price of the listing
     * @param info additional information about the listing
     * @return a listing object created with the given attributes
     * */
    public Listing addListing(int ID, int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price, String info){
        Listing listing = new Listing(ID, civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price, info);
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
    public void removeFromCreatedListings(String username, Listing listing) {

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
