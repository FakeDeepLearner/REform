package RealEstate.useCases;

import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.Seller;
import RealEstate.entities.SellerContainer;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateListing {
    private final ListingContainer<Integer, Listing> listings;
    private final SellerContainer<String, Seller> sellers;
    private final HashMap<String, ArrayList<Listing>> createdListings;
    public CreateListing(ListingContainer<Integer, Listing> listingsInterface,
                         SellerContainer<String, Seller> sellers) {
        listings = listingsInterface;
        this.sellers = sellers;
        createdListings = new HashMap<>();
    }

    public Listing addListing(int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price) {
        int uniqueID = GenerateUniqueID.getUniqueID();
        Listing listing = new Listing(uniqueID, civicAddress, streetName, city, type, bedrooms, bathrooms, price);
        listings.put(uniqueID, listing);
        return listing;
    }

    public Listing addListing(int ID, int unitNumber ,int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, int floors ,BigDecimal price) {
        Listing listing = new Listing(ID, unitNumber,civicAddress, streetName, city, type, bedrooms, bathrooms, floors, price);
        listings.put(ID, listing);
        return listing;
    }

    public void addListingToSeller(String username, Listing listing) {
        Seller seller = sellers.get(username);
        seller.addListing(listing);
    }

    public HashMap<String, ArrayList<Listing>> getCreatedListings() {
        return createdListings;
    }

    public SellerContainer<String, Seller> getSellers() {
        return sellers;
    }

    public ListingContainer<Integer, Listing> getListings() {
        return listings;
    }

    public void addListingToCreatedListings(String username, Listing listing) {
        if(createdListings.containsKey(username)){
            createdListings.get(username).add(listing);
        }
        else{
            ArrayList<Listing> value = new ArrayList<>();
            value.add(listing);
            createdListings.put(username, value);
        }
    }

    public void removeFromCreatedListing (String username, Listing listing) {
        if(createdListings.containsKey(username)) {
            createdListings.get(username).remove(listing);
        }
    }
}
