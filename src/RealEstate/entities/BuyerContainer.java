package RealEstate.entities;

import RealEstate.exceptions.BuyerNotFoundException;

public class BuyerContainer<String, Buyer> extends Container<String, Buyer> {
    @Override
    public Buyer get(Object key) throws BuyerNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new BuyerNotFoundException();
        }
    }
}