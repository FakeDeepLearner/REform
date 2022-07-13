package RealEstate.entities;

import RealEstate.exceptions.ListingNotFoundException;

public class ListingContainer<Integer, Listing> extends Container<Integer, Listing>{
    @Override
    public Listing get(Object key) throws ListingNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new ListingNotFoundException();
        }
    }
}
