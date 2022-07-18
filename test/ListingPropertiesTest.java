
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
    ///Tests fail because of Null pointer Exception. Will Work after Get unique ID is fices
    ListingContainer lc = new ListingContainer();
    UserContainer uc = new UserContainer();

    CreateListing createListing = new CreateListing(lc,uc);



    Listing l1 = createListing.addListing(1,1,1,"ABC street","ABC city","APARTMENT",2,2,0,new BigDecimal(123456));

    Listing l2 = createListing.addListing(2,2,2,"ABC street","ABCD city","APARTMENT",2,2,0,new BigDecimal(12345));

    ListingProperties listingProperties = new ListingProperties(lc);


    @Test()
    public void testSearchbyPrice() {
        ArrayList<Listing> found1 = listingProperties.SearchByPrice(new BigDecimal(123457), new BigDecimal(123455));

        assertTrue(found1.contains(l1));
    }

    @Test()
    public void testSearchbyCity() {
        ArrayList<Listing> found2 = listingProperties.SearchByCity("ABCD city");

        assertTrue(found2.contains(l2));
    }
    @Test()
    public void testSearchbycivicAdress() {
        ArrayList<Listing> found3 = listingProperties.SearchByCivicAddress(1);

        assertTrue(found3.contains(l1));
    }
    @Test()
    public void testSearchbyType() {
        ArrayList<Listing> found4 = listingProperties.SearchByListingType("APARTMENT");

        assertTrue(found4.contains(l1));
        assertTrue(found4.contains(l2));
    }
}

