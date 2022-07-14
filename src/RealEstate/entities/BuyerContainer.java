package RealEstate.entities;

import RealEstate.exceptions.BuyerNotFoundException;

public class BuyerContainer<String, Buyer> extends Container<java.lang.String, RealEstate.entities.Buyer> {
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws BuyerNotFoundException if no Buyer is associated with key in the hashmap
     */
    @Override
    public RealEstate.entities.Buyer get(Object key) throws BuyerNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new BuyerNotFoundException();
        }
    }

}
