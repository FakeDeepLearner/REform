package RealEstate.entities;

import RealEstate.exceptions.BuyerNotFoundException;
import java.util.HashMap;

public class BuyerContainer<String, Buyer> extends HashMap<String, Buyer> {
    @Override
    public Buyer get(Object key) {
        if (this.containsKey(key)) {
            return super.get(key);
        }
        else{
            throw new BuyerNotFoundException();
        }
    }
}
