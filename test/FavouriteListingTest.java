import entities.Listing;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import gateways.FavouritesCSVController;
import org.junit.Test;
import useCases.listingUseCases.FavoriteListing;
import useCases.userUseCases.UserFactory;

import java.math.BigDecimal;

public class FavouriteListingTest {
    @Test
    public void TestFavouriteListing() {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();

        String username = "b1";
        userFactory.createUser("BUYER", username, "p1");
        int id = 1;
        Listing l1 = new Listing(id, 1, "St. George St.", "Toronto", "HOUSE", 1, 1, 1, new BigDecimal("10000.50"), "test");
        listings.put(id, l1);

        FavoriteListing favourite = new FavoriteListing(users, listings, new FavouritesCSVController(users));
        favourite.addListingToBuyerFavorites(username, id);

        assert !favourite.getBuyerFavouritesID(username).isEmpty();
        assert !favourite.getBuyerFavouritesString(username).isEmpty();

        favourite.removeListingFromBuyerFavorites(username, 0);

        assert favourite.getBuyerFavouritesID(username).isEmpty();
        assert favourite.getBuyerFavouritesString(username).isEmpty();
    }
}
