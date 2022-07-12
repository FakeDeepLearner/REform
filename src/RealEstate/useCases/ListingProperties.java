package RealEstate.useCases;

import RealEstate.entities.ListingContainer;
import RealEstate.entities.Listing;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.function.Predicate;


public class ListingProperties {

    private final ListingContainer<Integer, Listing> listingContainer;

    public ListingProperties(ListingContainer<Integer, Listing> listingContainer) {
        this.listingContainer = listingContainer;
    }

    private ArrayList<Listing> SearchByProperty(Predicate<Listing> property){
        ArrayList<Listing> listings = new ArrayList<>();

        for (Listing listing : listingContainer.values()) {
            if (property.test(listing) && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> SearchByStreetName(String streetName){
        Predicate<Listing> property = (listing) -> listing.getStreetName().equals(streetName);
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByCivicAddress(int civicAdress){
        Predicate<Listing> property = (listing) -> listing.getCivicAddress() == civicAdress;
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByCity(String city){
        Predicate<Listing> property = (listing) -> listing.getCity().equals(city);
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByBedrooms(int bedrooms) {
        Predicate<Listing> property = (listing) -> listing.getBedrooms() == bedrooms;
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByBathrooms(int bathrooms) {
        Predicate<Listing> property = (listing) -> listing.getBathrooms() == bathrooms;
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByFloors(int floors) {
        Predicate<Listing> property = (listing) -> listing.getFloors() == floors;
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByPrice(BigDecimal upperLimit, BigDecimal lowerLimit) {
        Predicate<Listing> property = (listing) -> upperLimit.compareTo(listing.getPrice()) >= 0 && lowerLimit.compareTo(listing.getPrice()) <= 0;
        return SearchByProperty(property);
    }

    public ArrayList<Listing> SearchByListingType(String type){
        Predicate<Listing> property = (listing) -> listing.getType().equals(type);
        return SearchByProperty(property);
    }
}
