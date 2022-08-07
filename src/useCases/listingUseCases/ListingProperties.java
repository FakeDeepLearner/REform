package useCases.listingUseCases;

import entities.containers.ListingContainer;
import entities.Listing;

import java.util.*;
import java.math.BigDecimal;
import java.util.function.Predicate;


public class ListingProperties {

    private final ListingContainer<Integer, Listing> listingContainer;

    /**
     * Create a ListingProperties class
     *
     * @param listingContainer ListingContainer class
     */
    public ListingProperties(ListingContainer<Integer, Listing> listingContainer) {
        this.listingContainer = listingContainer;
    }

    /**
     * Searches Listings that by given property Predicate and unsold criteria.
     *
     * @param property Predicate that indicates specific listing criteria
     * @return ArrayList of String containing the String representation of the Listing
     */
    private ArrayList<Listing> searchByProperty(Predicate<Listing> property) {
        ArrayList<Listing> listings = new ArrayList<>();

        for (Listing listing : listingContainer.values()) {
            if (property.test(listing) && !listing.getIsSold()) {
                listings.add(listing);
            }
        }
        return listings;
    }

    /**
     * Search Listings by Street Name
     *
     * @param streetName Street name to search
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByStreetName(String streetName) {
        Predicate<Listing> property = (listing) -> listing.getStreetName().equals(streetName);
        return searchByProperty(property);
    }

    /**
     * Search Listing by civic address
     *
     * @param civicAddress civic address to search
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByCivicAddress(int civicAddress) {
        Predicate<Listing> property = (listing) -> listing.getCivicAddress() == civicAddress;
        return searchByProperty(property);
    }

    /**
     * Search Listing by City
     *
     * @param city Coty to Search
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByCity(String city) {
        Predicate<Listing> property = (listing) -> listing.getCity().equals(city);
        return searchByProperty(property);
    }

    /**
     * Search Listing by number of bedrooms
     *
     * @param bedrooms Number of Bedrooms
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByBedrooms(int bedrooms) {
        Predicate<Listing> property = (listing) -> listing.getBedrooms() == bedrooms;
        return searchByProperty(property);
    }

    /**
     * Search Listing by number of Bathroom
     *
     * @param bathrooms Number of Bathrooms
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByBathrooms(int bathrooms) {
        Predicate<Listing> property = (listing) -> listing.getBathrooms() == bathrooms;
        return searchByProperty(property);
    }

    /**
     * Search Listing by number of floors
     *
     * @param floors Number of floors
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByFloors(int floors) {
        Predicate<Listing> property = (listing) -> listing.getFloors() == floors;
        return searchByProperty(property);
    }

    /**
     * Search Listing by Price
     *
     * @param upperLimit Upper limit of the price
     * @param lowerLimit Lower limit of the price
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByPrice(BigDecimal upperLimit, BigDecimal lowerLimit, int order) {
        Predicate<Listing> property = (listing) -> upperLimit.compareTo(listing.getPrice()) >= 0 &&
                lowerLimit.compareTo(listing.getPrice()) <= 0;
        ArrayList<Listing> listings = searchByProperty(property);
        Comparator<Listing> compareByPrice = (Listing l1, Listing l2) -> l1.getPrice().compareTo(l2.getPrice());
        switch(order){
            case 1:{
                Collections.sort(listings, compareByPrice);
            }
            case 2:{
                Collections.sort(listings, compareByPrice);
            }
        }
        return listings;
    }

    /**
     * Search Listing by type of property
     *
     * @param type Type of the property
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<Listing> searchByListingType(String type) {
        Predicate<Listing> property = (listing) -> listing.getType().equals(type.toUpperCase());
        return searchByProperty(property);
    }

    /**
     * Search Listing by type of property
     *
     * @param listings arraylist of listings
     * @return the string description of each listing
     */
    public ArrayList<String> getListingsStrings(ArrayList<Listing> listings){
        ArrayList<String> listingStrings = new ArrayList<>();
        for (Listing l : listings){
            listingStrings.add(l.toString());
        }
        return listingStrings;
    }

    /**
     * Search Listing by type of property
     *
     * @param listings arraylist of listings
     * @return the ID of each listing
     */
    public ArrayList<Integer> getListingsID(ArrayList<Listing> listings){
        ArrayList<Integer> listingsID = new ArrayList<>();
        for (Listing l : listings){
            listingsID.add(l.getId());
        }
        return listingsID;
    }
}
