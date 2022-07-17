package RealEstate.useCases;

import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Buyer;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;

    private final HashMap<String, ArrayList<Integer>> generatedFavorites;

    public FavoriteListing(UserContainer<String, User> userContainer) {
        this.userContainer = userContainer;
        this.generatedFavorites = new HashMap<>();
    }

    public HashMap<String, ArrayList<Integer>> getGeneratedFavorites() {
        return generatedFavorites;
    }

    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    public void addListingToBuyerFavorites(String username, int listingID) {
        Buyer buyer = userContainer.getBuyer(username);
        if (!buyer.getFavorites().contains(listingID)) {
            buyer.addFavouriteListing(listingID);
        }

    }

    public void addCreationToGeneratedFavorites(String username, int listingID){
        if (generatedFavorites.containsKey(username)){
            if (!generatedFavorites.get(username).contains(listingID)){
                generatedFavorites.get(username).add(listingID);
            }
        }
        else{
            ArrayList<Integer> value = new ArrayList<>();
            value.add(listingID);
            generatedFavorites.put(username, value);
        }
    }

    public void removeListingFromBuyerFavorites(String username, int listingID) {
        Buyer buyer = userContainer.getBuyer(username);
        buyer.removeFavouriteListing(listingID);
    }

    public void removeFromGeneratedFavorites(String username, int listingID) {
        if (generatedFavorites.containsKey(username)){
            generatedFavorites.get(username).remove(listingID);
        }
    }
}
