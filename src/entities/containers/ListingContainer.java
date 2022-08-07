package entities.containers;

import exceptions.ListingNotFoundException;

public class ListingContainer<Integer, Listing> extends Container<Integer, Listing> {
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws ListingNotFoundException if no Listing is associated with key in the hashmap
     */
    @Override
    public Listing get(Object key) throws ListingNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new ListingNotFoundException();
        }
    }

}
