package useCases.listingUseCases;

import entities.users.User;
import entities.containers.UserContainer;
import entities.users.Buyer;
import gateways.DataInterface;

import java.io.IOException;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;
    public final DataInterface i;

    /**
     * Create a FavoriteListing class
     *
     * @param userContainer UserContainer object to be used
     */
    public FavoriteListing(UserContainer<String, User> userContainer, DataInterface i) {
        this.userContainer = userContainer;
        this.i = i;
    }

    /**
     * Read data from csv files
     */
    public void read() throws IOException {
        for (String[] data : i.read()) {
            addListingToBuyerFavorites(data[0], Integer.parseInt(data[1]));
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
     * Remove a Listing object from a Buyer's favorites.
     *
     * @param username  Username of the Buyer.
     * @param listingID ID of the Listing to be removed.
     */
    public void removeListingFromBuyerFavorites(String username, int listingID) {
        Buyer buyer = userContainer.getBuyer(username);
        buyer.removeFavouriteListing(listingID);
    }

}
