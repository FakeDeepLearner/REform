package RealEstate.entities;

import java.util.HashMap;

import RealEstate.exceptions.ListingNotFoundException;

public class ListingContainer<Integer, Listing> extends HashMap<java.lang.Integer, RealEstate.entities.Listing>{
    @Override
    public RealEstate.entities.Listing get(Object key) throws ListingNotFoundException {
        if(this.containsKey(key)){
            return super.get(key);
        }
        else{
            throw new ListingNotFoundException();
        }
    }

    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        for (RealEstate.entities.Listing listing : this.values()) {
            returnedString.append(listing.toString()).append("\n");
        }
        return returnedString.toString();
    }

    public String getListingInfo(int listingID) {
        RealEstate.entities.Listing listing = get(listingID);
        return listing.toString();
    }
}
