package entities.containers;

import entities.*;
import entities.users.AdminUser;
import entities.users.Buyer;
import entities.users.NonAdminUser;
import entities.users.Seller;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public non-sealed class UserContainer<String, User> extends Container<String, User> {

    @Override
    public User get(Object key) throws IllegalArgumentException {
        try{
            return super.get(key);
        }
        catch (IllegalArgumentException e){
            throw new UserNotFoundException();
        }
    }

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
        return admins;
    }

    public AdminUser getAdmin(Object key){
        try{
            return (AdminUser) super.get(key);
        }
        catch (IllegalArgumentException | ClassCastException e){
            throw new AdminNotFoundException();
        }
    }

    public List<Listing> getSellerListings(String username){
        Seller seller = getSeller(username);
        return seller.getListings();
    }

    public List<java.lang.String> getSellerListingStrings(String username){
        ArrayList<java.lang.String> listings = new ArrayList<>();
        Seller u = getSeller(username);
        for (Listing l : u.getListings()) {
            listings.add(l.toString());
        }
        return listings;
    }


}
