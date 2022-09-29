package useCases.listingUseCases;

import entities.Listing;
import entities.users.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import entities.users.Buyer;
import gateways.DataInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;
    private final ListingContainer<Integer, Listing> listingContainer;

    private final HashMap<String, ArrayList<Integer>> generatedFavorites;
    public final DataInterface i;

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

    /**
     * Read data from csv files
     */
    public void read() throws IOException {
        for (String[] data : i.read()) {
            addListingToBuyerFavorites(data[0], Integer.parseInt(data[1]));
            addCreationToGeneratedFavorites(data[0], Integer.parseInt(data[1]));
        }
    }

    /**
     * Write data to csv files
     */
    public void write() throws IOException {
        i.write();
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
