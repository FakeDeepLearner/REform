import entities.User;
import entities.containers.UserContainer;
import gateways.BuyersCSVController;
import useCases.userUseCases.CreateBuyer;

import org.junit.Test;
import static org.junit.Assert.*;


public class CreateBuyerTest {
    @Test
    public void testCreatingABuyer() {
        UserContainer<String, User> uc = new UserContainer<>();

        CreateBuyer createBuyer = new CreateBuyer(uc, new BuyersCSVController(uc));
        createBuyer.createNewBuyer("TestUserName", "TestPW");

        assertFalse(createBuyer.getUserContainer().isEmpty());
        assertFalse(createBuyer.getCreatedBuyers().isEmpty());
    }
}
