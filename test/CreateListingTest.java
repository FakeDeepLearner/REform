import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;

import RealEstate.useCases.CreateListing;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CreateListingTest {
    @Test
    public void testCreatingABuyer(){
        ListingContainer<Integer, Listing> lc = new ListingContainer<>();
        UserContainer<String, User> uc = new UserContainer<>();

        CreateListing createListing = new CreateListing(lc, uc);

        createListing.addListing(1, "TestName", "TestCity",
                "TOWNHOUSE", 2, 3, BigDecimal.valueOf(4.2));

        assertFalse(createListing.getCreatedListings().isEmpty());
    }
}
