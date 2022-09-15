package useCases.listingUseCases;

import entities.Listing;
import entities.Seller;
import entities.User;
import entities.containers.UserContainer;
import exceptions.SellerNotFoundException;
import exceptions.UserIsNotASellerException;

import java.util.ArrayList;
import java.util.List;

public class ViewListings {
    private final UserContainer<String, User> users;

    public ViewListings(UserContainer<String, User> users){
        this.users = users;
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

}
