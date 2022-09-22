import entities.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import entities.Listing;

import entities.Seller;
import org.junit.Test;
import useCases.listingUseCases.ViewListings;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ViewListingsTest {
    @Test
    public void testGetSellerListingsStrings() {
        UserContainer<String, User> uc = new UserContainer<>();
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();

        Seller s = new Seller("s1", "p1");
        uc.put("s1", s);

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        s.addListing(listing);

        ViewListings listingViewer = new ViewListings(uc, listings);

        assertEquals(1, listingViewer.getSellerListingsStrings("s1").size());
    }

    @Test
    public void testGetSellerListings() {
        UserContainer<String, User> uc = new UserContainer<>();
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();

        Seller s = new Seller("s1", "p1");
        uc.put("s1", s);

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        s.addListing(listing);

        ViewListings listingViewer = new ViewListings(uc, listings);

        assertEquals(1, listingViewer.getSellerListings("s1").size());
    }
}
