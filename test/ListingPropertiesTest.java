import entities.User;
import entities.containers.UserContainer;
import entities.Listing;
import gateways.ListingsCSVController;
import useCases.listingUseCases.CreateListing;
import useCases.listingUseCases.ListingProperties;
import entities.containers.ListingContainer;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ListingPropertiesTest {
    ListingContainer<Integer, Listing> lc = new ListingContainer<>();
    UserContainer<String, User> uc = new UserContainer<>();

    CreateListing createListing = new CreateListing(lc, uc, new ListingsCSVController(uc));

    Listing l1 = createListing.addListing(1,1,1,"ABC street","ABC city","APARTMENT",2,2,new BigDecimal(123456), "test1");
    Listing l2 = createListing.addListing(2,2,"ABC street","ABCD city","APARTMENT",2,2,1,new BigDecimal(12345), "test2");
    Listing l3 = createListing.addListing(3,1,1,"ABC street","ABC city","APARTMENT",2,2,new BigDecimal(12345678), "test3");
    ListingProperties listingProperties = new ListingProperties(lc);


    @Test()
    public void testSearchByPrice() {
        ArrayList<Listing> found1 = listingProperties.searchByPrice(new BigDecimal(123457), new BigDecimal(123455));

        assertFalse(found1.contains(l3));
    }

    @Test()
    public void testSearchByCity() {
        ArrayList<Listing> found2 = listingProperties.searchByCity("ABCD city");

        assertTrue(found2.contains(l2));
    }
    @Test()
    public void testSearchByCivicAddress() {
        ArrayList<Listing> found3 = listingProperties.searchByCivicAddress(1);

        assertTrue(listingProperties.getListingsStrings(found3).contains(l1.toString()));
        assertTrue(found3.contains(l3));
    }
    @Test()
    public void testSearchByType() {
        ArrayList<Listing> found4 = listingProperties.searchByListingType("APARTMENT");

        assertTrue(listingProperties.getListingsStrings(found4).contains(l1.toString()));
        assertTrue(found4.contains(l2));
    }
}

