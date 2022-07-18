import LoginSystem.entities.UserContainer;
import RealEstate.entities.ListingContainer;
import RealEstate.useCases.CreateBuyer;
import RealEstate.useCases.CreateListing;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CreateListingTest {


    @Test
    public void testCreatingABuyer(){

        ListingContainer lc = new ListingContainer();
        UserContainer uc = new UserContainer();

        CreateListing createListing = new CreateListing(lc, uc);

        createListing.addListing(1, "TestName", "TestCity",
                "TestType", 2, 3, BigDecimal.valueOf(4.2));

        // TODO: Fix Errors
        assertFalse(createListing.getCreatedListings().isEmpty());


    }



}
