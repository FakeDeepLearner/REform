package RealEstate.entities;

import RealEstate.exceptions.BuyerNotFoundException;
import RealEstate.exceptions.ListingNotFoundException;

public class ListingContainer<Integer, Listing> extends Container<java.lang.Integer, RealEstate.entities.Listing>{
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws ListingNotFoundException if no Listing is associated with key in the hashmap
     */
    @Override
    public RealEstate.entities.Listing get(Object key) throws ListingNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new ListingNotFoundException();
        }
    }

}
