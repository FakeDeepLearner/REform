package RealEstate.entities;

import java.util.HashMap;

import RealEstate.exceptions.ListingNotFoundException;

public class ListingContainer<Integer, Listing> extends HashMap<Integer, Listing>{
    @Override
    public Listing get(Object key) throws ListingNotFoundException {
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
        for (Listing listing : this.values()) {
            returnedString.append(listing.toString());
            returnedString.append("\n\n");
        }
        return returnedString.toString();
    }
}
