package useCases.listingUseCases;

import entities.Listing;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import entities.Buyer;
import useCases.DataInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;
    private final ListingContainer<Integer, Listing> listingContainer;

    private final HashMap<String, ArrayList<Integer>> generatedFavorites;
    public DataInterface i;

    /**
     * Create a FavoriteListing class
     *
     * @param userContainer UserContainer object to be used
     */
    public FavoriteListing(UserContainer<String, User> userContainer,
                           ListingContainer<Integer, Listing> listingContainer, DataInterface i) {
        this.userContainer = userContainer;
        this.generatedFavorites = new HashMap<>();
        this.listingContainer = listingContainer;
        this.i = i;
    }

    public void read() throws IOException {
        i.read();
    }

    public void write() throws IOException {
        i.write();
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

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing IDs.
     */
    public ArrayList<Integer> getBuyerFavouritesID(String username){
        Buyer buyer = userContainer.getBuyer(username);
        return buyer.getFavorites();
    }

    /**
     * @param username the username to get the favourite listings of.
     * @return the list of favourite listing strings.
     */
    public ArrayList<String> getBuyerFavouritesString(String username){
        Buyer buyer = userContainer.getBuyer(username);
        ArrayList<String> listings = new ArrayList<>();
        for(Integer id : buyer.getFavorites()){
            listings.add(listingContainer.get(id).toString());
        }
        return listings;
    }
}
