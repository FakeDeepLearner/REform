package useCases.listingUseCases;

import entities.users.Buyer;
import entities.Listing;
import entities.users.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import exceptions.SellerNotFoundException;
import exceptions.UserIsNotASellerException;

import java.util.ArrayList;
import java.util.List;

public class ViewListings {
    private final UserContainer<String, User> users;
    private final ListingContainer<Integer, Listing> listings;

    public ViewListings(UserContainer<String, User> users, ListingContainer<Integer, Listing> listings)
    {
        this.users = users;
        this.listings = listings;
    }

    public List<Listing> getSellerListings(String username){
        try {
            return users.getSellerListings(username);
        }
        catch (SellerNotFoundException exception){
            throw new UserIsNotASellerException();
        }
    }

    public List<String> getSellerListingsStrings(String username){
        try {
            return users.getSellerListingStrings(username);
        }
        catch(SellerNotFoundException exception){
            throw new UserIsNotASellerException();
        }
    }

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing IDs.
     */
    public ArrayList<Integer> getBuyerFavouritesID(String username){
        Buyer buyer = users.getBuyer(username);
        return buyer.getFavorites();
    }

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing strings.
     */
    public List<String> getBuyerFavouritesString(String username){
        Buyer buyer = users.getBuyer(username);
        ArrayList<String> favListings = new ArrayList<>();
        for(Integer id : buyer.getFavorites()){
            favListings.add(listings.get(id).toString());
        }
        return favListings;
    }

}
