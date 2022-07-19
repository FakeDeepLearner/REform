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
     * Create a CreateListing class
     *
     * @param listingsInterface ListingContainer
     * @param userContainer     UserContainer
     */
    public CreateListing(ListingContainer<Integer, Listing> listingsInterface,
                         UserContainer<String, User> userContainer) {
        listings = listingsInterface;
        this.userContainer = userContainer;
        createdListings = new HashMap<>();
    }

    /**
     * Create a Listing object and add it to CreateListing.listings
     *
     * @param civicAddress Civic Address of the listing
     * @param streetName   Street name of the listing
     * @param city         City of the listing
     * @param type         Type of the listing
     * @param bedrooms     Number of bedrooms in the listing
     * @param bathrooms    Number of bathrooms in the listings
     * @param price        Price the listing
     * @return Listing object
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
     * Create a Listing object and add it to CreateListing.listings
     *
     * @param unitNumber   Unit number of the listing
     * @param civicAddress Civic Address of the listing
     * @param streetName   Street name of the listing
     * @param city         City of the listing
     * @param type         Type of the listing
     * @param bedrooms     Number of bedrooms in the listing
     * @param bathrooms    Number of bathrooms in the listings
     * @param price        Price the listing
     * @return Listing object
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
     * Create a Listing object and add it to CreateListing.listings
     *
     * @param ID           ID number of the listing
     * @param unitNumber   Unit number of the listing
     * @param civicAddress Civic Address of the listing
     * @param streetName   Street name of the listing
     * @param city         City of the listing
     * @param type         Type of the listing
     * @param bedrooms     Number of bedrooms in the listing
     * @param bathrooms    Number of bathrooms in the listings
     * @param price        Price the listing
     * @return Listing object
     */
    public Listing addListing(int ID, int unitNumber, int civicAddress, String streetName, String city,
                              String type, int bedrooms,
                              int bathrooms, int floors, BigDecimal price) {
        Listing listing = new Listing(ID, unitNumber, civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price);
        listings.put(ID, listing);
        return listing;
    }

    /**
     * Add a listing to a seller
     *
     * @param username Username of the seller
     * @param listing  Listing object to be added
     */
    public void addListingToSeller(String username, Listing listing) {
        Seller seller = userContainer.getSeller(username);
        seller.addListing(listing);
    }

    /**
     * Getter for createdListings
     *
     * @return CreateListing.createdListings
     */
    public HashMap<String, ArrayList<Listing>> getCreatedListings() {
        return createdListings;
    }

    /**
     * Getter for userContainer
     *
     * @return CreateListing.userContainer
     */
    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    /**
     * Getter for listings
     *
     * @return CreateListing.listings
     */
    public ListingContainer<Integer, Listing> getListings() {
        return listings;
    }

    /**
     * Add a Listing to CreateListing.createdListings
     *
     * @param username Username of a user
     * @param listing  Listing object to be added
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
     * Remove a Listing from a User
     *
     * @param username Username of the user
     * @param listing  Listing object to be removed
     */
    public void removeFromCreatedListing(String username, Listing listing) {
        if (createdListings.containsKey(username)) {
            createdListings.get(username).remove(listing);
        }
    }

    /**
     * Get an Arraylist of String containing the String representation of the listings from a seller
     *
     * @param username Username of the seller
     * @return Arraylist of String containing the String representation of the listings
     */
    public ArrayList<String> getSellerListingsStrings(String username) {
        ArrayList<String> listings = new ArrayList<>();
        Seller u = userContainer.getSeller(username);
        for (Listing l : u.getListings()) {
            listings.add(l.toString());
        }
        return listings;
    }

    /**
     * Get a Seller's Listings
     *
     * @param username Username of the seller
     * @return ArrayList of Listings from the seller
     */
    public ArrayList<Listing> getSellerListings(String username) {
        Seller u = userContainer.getSeller(username);
        return u.getListings();
    }

    /**
     * Delete a Listing from a user
     *
     * @param username Username of the user
     * @param listing  Listing to be removed
     */
    public void deleteListing(String username, Listing listing) {
        Seller user = userContainer.getSeller(username);
        int id = listing.getId();
        listings.remove(id);
        user.removeListing(listings.get(id));
    }


}
