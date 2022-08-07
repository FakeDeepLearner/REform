import entities.User;
import entities.containers.UserContainer;
import entities.Listing;
import entities.containers.ListingContainer;

import entities.Seller;
import gateways.ListingsCSVController;
import useCases.listingUseCases.CreateListing;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CreateListingTest {
    @Test
    public void testAddListing() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        CreateListing createListing = new CreateListing(lc, uc, new ListingsCSVController(uc));

        createListing.addListing(1, "TestName", "TestCity", "TOWNHOUSE", 2, 3, 1, BigDecimal.valueOf(4.2), "test1");

        assertFalse(createListing.getListings().isEmpty());
    }

    @Test
    public void testAddListingToSeller() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Seller s = new Seller("s1", "p1");
        uc.put("s1", s);

        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));
        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        listingCreator.addListingToSeller("s1", listing);
        assertEquals(1, s.getListings().size());
    }

    @Test
    public void testGetListings() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        lc.put(0, listing);

        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));
        assertEquals(1, listingCreator.getListings().size());
    }

    @Test
    public void testAddListingToCreatedListings() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));

        listingCreator.addListingToCreatedListings("l1", listing);
        assertEquals(1, listingCreator.getCreatedListings().size());
    }

    @Test
    public void testGetSellerListingsStrings() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Seller s = new Seller("s1", "p1");
        uc.put("s1", s);

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        s.addListing(listing);

        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));

        assertEquals(1, listingCreator.getSellerListingsStrings("s1").size());
    }

    @Test
    public void testGetSellerListings() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Seller s = new Seller("s1", "p1");
        uc.put("s1", s);

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        s.addListing(listing);

        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));

        assertEquals(1, listingCreator.getSellerListings("s1").size());
    }

    @Test
    public void testDeleteListing() {
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        Seller s = new Seller("s1", "p1");

        Listing listing = new Listing(0, 1, 22, "streetName", "city", "APARTMENT", 5, 5, BigDecimal.valueOf(5.0), "test1");
        s.addListing(listing);

        lc.put(0, listing);
        uc.put("s1", s);

        CreateListing listingCreator = new CreateListing(lc, uc, new ListingsCSVController(uc));
        listingCreator.deleteListing("s1", listing);

        assertEquals(0, listingCreator.getListings().size());
        assertEquals(0, s.getListings().size());
    }
}
