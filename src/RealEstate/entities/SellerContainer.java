package RealEstate.entities;
import RealEstate.exceptions.SellerNotFoundException;

public class SellerContainer<Integer, Seller> extends Container<java.lang.String, RealEstate.entities.Seller> {
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws SellerNotFoundException if no Seller is associated with key in the hashmap
     */
    @Override
    public RealEstate.entities.Seller get(Object key) throws SellerNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new SellerNotFoundException();
        }
    }
}
