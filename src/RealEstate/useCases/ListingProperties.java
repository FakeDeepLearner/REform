package RealEstate.useCases;

import RealEstate.entities.ListingContainer;
import RealEstate.entities.Listing;
import java.util.ArrayList;
import java.math.BigDecimal;


public class ListingProperties {

    private final ListingContainer<Integer, Listing> listingContainer;

    public ListingProperties(ListingContainer<Integer, Listing> listingContainer) {
        this.listingContainer = listingContainer;
    }

    public ArrayList<Listing> SearchbystreetName(String streetName){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getStreetName().equals(streetName) && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> SearchbycivicAdress(int civicAdress){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getCivicAddress()==civicAdress && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> Searchbycity(String city){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getCity().equals(city) && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> Searchbybedrooms(int bedrooms){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getBedrooms()==bedrooms && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> Searchbybathrooms(int bathrooms){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getBathrooms()==bathrooms && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> Searchbyfloors(int floors){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getFloors()==floors && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> Searchbyprice(BigDecimal upperLimit, BigDecimal lowerLimit){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (upperLimit.compareTo(listing.getPrice())>=0 && lowerLimit.compareTo(listing.getPrice())<=0 && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }

    public ArrayList<Listing> SearchbylistingType(String type){
        ArrayList<Listing> listings = new ArrayList<Listing>();
        for (Listing listing : listingContainer.values()) {
            if (listing.getType().equals(type) && !listing.getIsSold()){
                listings.add(listing);
            }
        }
        return listings;
    }
}
