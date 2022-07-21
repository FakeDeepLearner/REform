import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.useCases.CreateBuyer;

import RealEstate.useCases.CreateSeller;
import org.junit.Test;
import static org.junit.Assert.*;


public class CreateSellerTest {
    @Test
    public void testCreatingABuyer() {
        UserContainer<String, User> uc = new UserContainer<>();

        CreateSeller createSeller = new CreateSeller(uc);
        createSeller.createNewSeller("TestSeller", "TestPW");

        assertFalse(createSeller.getUserContainer().isEmpty());
        assertFalse(createSeller.getCreatedSellers().isEmpty());
    }
}
