package entities.containers;

import entities.AdminUser;
import exceptions.UserNotFoundException;
import entities.Buyer;
import entities.Seller;
import exceptions.BuyerNotFoundException;
import exceptions.SellerNotFoundException;
import entities.NonAdminUser;

public class UserContainer<String, User> extends Container<String, User> {

    /**
     * @return a container of all the buyers
     */
    public Container<String, Buyer> getAllBuyers() {
        Container<String, Buyer> buyers = new UserContainer<>();
        for (String username : keySet()) {
            User u = get(username);
            if (u instanceof Buyer) {
                buyers.put(username, (Buyer) u);
            }
        }

        return  buyers;
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws BuyerNotFoundException if no Buyer is associated with key in the hashmap
     */
    public Buyer getBuyer(Object key) throws BuyerNotFoundException {
        try {
            return (Buyer) super.get(key);
        } catch(IllegalArgumentException | ClassCastException e) {
            throw new BuyerNotFoundException();
        }
    }


    /**
     * @return a container of all the sellers
     */
    public Container<String, Seller> getAllSellers() {
        Container<String, Seller> sellers = new UserContainer<>();
        for (String username : keySet()) {
            User u = get(username);
            if (u instanceof Seller) {
                sellers.put(username, (Seller) u);
            }
        }

        return  sellers;
    }


    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws SellerNotFoundException if no Seller is associated with key in the hashmap
     */
    public Seller getSeller(Object key) throws SellerNotFoundException {
        try {
            return (Seller) super.get(key);
        } catch(IllegalArgumentException | ClassCastException e) {
            throw new SellerNotFoundException();
        }
    }


    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws UserNotFoundException if no NonAdminUser is associated with key in the hashmap
     */
    public NonAdminUser getNonAdmin(Object key) throws UserNotFoundException {
        try{
            return (NonAdminUser) super.get(key);
        }
        catch (IllegalArgumentException illegalArgumentException){
            throw new UserNotFoundException();
        }
    }

    public Container<String, AdminUser> getAllAdmins(){
        Container<String, AdminUser> admins = new UserContainer<>();
        for (String username : keySet()) {
            User u = get(username);
            if (u instanceof AdminUser) {
                admins.put(username, (AdminUser) u);
            }
        }
        return  admins;
    }
}
