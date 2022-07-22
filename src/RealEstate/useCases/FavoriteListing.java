package RealEstate.useCases;

import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Buyer;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;

    private final HashMap<String, ArrayList<Integer>> generatedFavorites;

    /**
     * Create a FavoriteListing class
     *
     * @param userContainer UserContainer object to be used
     */
    public FavoriteListing(UserContainer<String, User> userContainer) {
        this.userContainer = userContainer;
        this.generatedFavorites = new HashMap<>();
    }

    /**
     * Getter for generatedFavorites
     *
     * @return Generated Favorites
     */
    public HashMap<String, ArrayList<Integer>> getGeneratedFavorites() {
        return generatedFavorites;
    }

    /**
     * Add a Listing object to a Buyer's Favorites
     *
     * @param username  Username of the Buyer
     * @param listingID ID of the Listing
     */
    public void addListingToBuyerFavorites(String username, int listingID) {
        Buyer buyer = userContainer.getBuyer(username);
        if (!buyer.getFavorites().contains(listingID)) {
            buyer.addFavouriteListing(listingID);
        }

    }

    /**
     * @param username the username of the buyer.
     * @param listingID the ID of the listing to be added.
     */
    public void addCreationToGeneratedFavorites(String username, int listingID) {
        if (generatedFavorites.containsKey(username)) {
            if (!generatedFavorites.get(username).contains(listingID)) {
                generatedFavorites.get(username).add(listingID);
            }
        } else {
            ArrayList<Integer> value = new ArrayList<>();
            value.add(listingID);
            generatedFavorites.put(username, value);
        }
    }

    /**
     * Remove a Listing object from a Buyer's favorites.
     *
     * @param username  Username of the Buyer.
     * @param listingID ID of the Listing to be removed.
     */
    public void removeListingFromBuyerFavorites(String username, int listingID) {
        Buyer buyer = userContainer.getBuyer(username);
        buyer.removeFavouriteListing(listingID);
    }

    /**
     * @param username the username that the listing will be removed from.
     * @param listingID the ID of the listing to be removed.
     */
    public void removeFromGeneratedFavorites(String username, int listingID) {
        if (generatedFavorites.containsKey(username)) {
            generatedFavorites.get(username).remove(listingID);
        }
    }
}
