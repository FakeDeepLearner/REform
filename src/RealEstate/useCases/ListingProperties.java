package RealEstate.useCases;

import RealEstate.entities.ListingContainer;
import RealEstate.entities.Listing;

import java.util.ArrayList;
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
     * Searches Listings that by given property Predicate and unsold criteria
     *
     * @param property Predicate that indicates specific listing criteria
     * @return ArrayList of String containing the String representation of the Listing
     */
    private ArrayList<String> searchByproperty(Predicate<Listing> property) {
        ArrayList<String> listings = new ArrayList<>();

        for (Listing listing : listingContainer.values()) {
            if (property.test(listing) && !listing.getIsSold()) {
                listings.add(listing.toString());
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
    public ArrayList<String> searchBystreetName(String streetName) {
        Predicate<Listing> property = (listing) -> listing.getStreetName().equals(streetName);
        return searchByproperty(property);
    }

    /**
     * Search Listing by civic address
     *
     * @param civicAdress civic address to search
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchBycivicAddress(int civicAdress) {
        Predicate<Listing> property = (listing) -> listing.getCivicAddress() == civicAdress;
        return searchByproperty(property);
    }

    /**
     * Search Listing by City
     *
     * @param city Coty to Search
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchBycity(String city) {
        Predicate<Listing> property = (listing) -> listing.getCity().equals(city);
        return searchByproperty(property);
    }

    /**
     * Search Listing by number of bedrooms
     *
     * @param bedrooms Number of Bedrooms
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchBybedrooms(int bedrooms) {
        Predicate<Listing> property = (listing) -> listing.getBedrooms() == bedrooms;
        return searchByproperty(property);
    }

    /**
     * Search Listing by number of Bathroom
     *
     * @param bathrooms Number of Bathrooms
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchBybathrooms(int bathrooms) {
        Predicate<Listing> property = (listing) -> listing.getBathrooms() == bathrooms;
        return searchByproperty(property);
    }

    /**
     * Search Listing by number of floors
     *
     * @param floors Number of floors
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchByfloors(int floors) {
        Predicate<Listing> property = (listing) -> listing.getFloors() == floors;
        return searchByproperty(property);
    }

    /**
     * Search Listing by Price
     *
     * @param upperLimit Upper limit of the price
     * @param lowerLimit Lower limit of the price
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchByprice(BigDecimal upperLimit, BigDecimal lowerLimit) {
        Predicate<Listing> property = (listing) -> upperLimit.compareTo(listing.getPrice()) >= 0 && lowerLimit.compareTo(listing.getPrice()) <= 0;
        return searchByproperty(property);
    }

    /**
     * Search Listing by type of property
     *
     * @param type Type of the property
     * @return ArrayList of Strings containing the String representation of the Listing
     */
    public ArrayList<String> searchBylistingType(String type) {
        Predicate<Listing> property = (listing) -> listing.getType().equals(type);
        return searchByproperty(property);
    }
}
