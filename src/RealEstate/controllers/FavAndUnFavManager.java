package RealEstate.controllers;
import RealEstate.useCases.FavoriteListing;

public class FavAndUnFavManager{
    private final FavoriteListing favoriteListing;

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

}
