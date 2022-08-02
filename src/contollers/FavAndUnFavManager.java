package contollers;
import entities.Buyer;
import entities.Listing;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import useCases.listingUseCases.FavoriteListing;

import java.util.ArrayList;

public class FavAndUnFavManager {
    private final FavoriteListing favoriteListing;

    /**
     * Constructor for FavAndUnFavManager
     * @param users are the users that can favourite listings
     */
    public FavAndUnFavManager(UserContainer<String, User> users, ListingContainer<Integer, Listing> listingContainer) {
        this.favoriteListing = new FavoriteListing(users, listingContainer);
    }

    /**
     * Constructor for FavAndUnFavManager
     * @param favoriteListing the favourite listing
     */
    public FavAndUnFavManager(FavoriteListing favoriteListing) {
        this.favoriteListing = favoriteListing;
    }

    /**
     * Getter for favouriteListing
     * @return favouriteListing
     */
    public FavoriteListing getFavoriteListing() {
        return favoriteListing;
    }

    /**
     * Add a listing to the buyer's favourites
     * @param username the username of the buyer
     * @param listingID the id of the listing to be favourite'd
     */
    public void addToBuyerFavorites(String username, int listingID) {
        favoriteListing.addListingToBuyerFavorites(username, listingID);
        favoriteListing.addCreationToGeneratedFavorites(username, listingID);

    }

    /**
     * Remove a listing from a buyer's favourites
     * @param username the username of the buyer
     * @param listingID the id of the listing to be removed
     */
    public void removeFromBuyerFavorites(String username, int listingID) {
        favoriteListing.removeListingFromBuyerFavorites(username, listingID);
        favoriteListing.removeFromGeneratedFavorites(username, listingID);
    }

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing IDs.
     */
    public ArrayList<Integer> getBuyerFavouritesID(String username){
        return favoriteListing.getBuyerFavouritesID(username);
    }

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing strings.
     */
    public ArrayList<String> getBuyerFavouritesString(String username){
        return favoriteListing.getBuyerFavouritesString(username);
    }

}
