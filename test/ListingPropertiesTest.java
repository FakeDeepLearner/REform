
import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Listing;
import RealEstate.useCases.CreateListing;
import RealEstate.useCases.ListingProperties;
import RealEstate.entities.ListingContainer;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ListingPropertiesTest {
    ListingContainer<Integer, Listing> lc = new ListingContainer<>();
    UserContainer<String, User> uc = new UserContainer<>();

    CreateListing createListing = new CreateListing(lc,uc);

    Listing l1 = createListing.addListing(1,1,1,"ABC street","ABC city","APARTMENT",2,2,0,new BigDecimal(123456));
    Listing l2 = createListing.addListing(2,2,2,"ABC street","ABCD city","APARTMENT",2,2,0,new BigDecimal(12345));
    Listing l3 = createListing.addListing(1,1,1,"ABC street","ABC city","APARTMENT",2,2,0,new BigDecimal(12345678));
    ListingProperties listingProperties = new ListingProperties(lc);


    @Test()
    public void testSearchbyPrice() {
        ArrayList<String> found1 = listingProperties.searchByPrice(new BigDecimal(123457), new BigDecimal(123455));

        assertFalse(found1.contains(l3.toString()));
    }

    @Test()
    public void testSearchbyCity() {
        ArrayList<String> found2 = listingProperties.searchByCity("ABCD city");

        assertTrue(found2.contains(l2.toString()));
    }
    @Test()
    public void testSearchbycivicAdress() {
        ArrayList<String> found3 = listingProperties.searchByCivicAddress(1);

        assertTrue(found3.contains(l1.toString()));
    }
    @Test()
    public void testSearchbyType() {
        ArrayList<String> found4 = listingProperties.searchByListingType("APARTMENT");

        assertTrue(found4.contains(l1.toString()));
        assertTrue(found4.contains(l2.toString()));
    }
}

