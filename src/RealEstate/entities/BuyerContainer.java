package RealEstate.entities;
import RealEstate.exceptions.BuyerNotFoundException;
import java.util.HashMap;

public class BuyerContainer<String, Buyer> extends HashMap<java.lang.String, RealEstate.entities.Buyer> {
    @Override
    public RealEstate.entities.Buyer get(Object key) {
        if (this.containsKey(key)) {
            return super.get(key);
        }
        else{
            throw new BuyerNotFoundException();
        }
    }

    public java.lang.String getBuyerInfo(java.lang.String username) {
        RealEstate.entities.Buyer buyer = get(username);
        return buyer.toString();
    }
}
