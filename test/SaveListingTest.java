import entities.Listing;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.UserContainer;
import gateways.ListingsCSVController;
import org.junit.Test;
import useCases.listingUseCases.CreateListing;
import useCases.userUseCases.UserFactory;

import java.io.IOException;
import java.math.BigDecimal;


public class SaveListingTest {
    UserContainer<String, User> uc = new UserContainer<>();
    ListingContainer<Integer, Listing> lc = new ListingContainer<>();

    UserFactory userFactory = new UserFactory(uc);
    CreateListing createListing = new CreateListing(lc, uc);
    ListingsCSVController csv = new ListingsCSVController(uc, createListing);
    Listing l1 = createListing.addListing(1,1,1,"ABC street","ABC city","APARTMENT",2,2,new BigDecimal(123456), "test1");
    Listing l2 = createListing.addListing(2,2,"ABC street","ABCD city","APARTMENT",2,2,1,new BigDecimal(12345), "test2");
    Listing l3 = createListing.addListing(3,1,1,"ABC street","ABC city","APARTMENT",2,2,new BigDecimal(12345678), "test3");

    @Test()
    public void testSaveListing() throws IOException {
        userFactory.createUser("SELLER", "s1", "p1");
        User s = uc.get("s1");

        createListing.addListingToSeller(s.getUsername(), l1);
        createListing.addListingToSeller(s.getUsername(), l2);
        createListing.addListingToSeller(s.getUsername(), l3);

        csv.write();
    }
}

