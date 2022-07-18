import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.useCases.CreateBuyer;
import org.junit.Test;
import static org.junit.Assert.*;


public class CreateBuyerTest {

    @Test
    public void testCreatingABuyer(){

        UserContainer<String, User> uc = new UserContainer<>();

        CreateBuyer createBuyer = new CreateBuyer(uc);
        createBuyer.createNewBuyer("TestUserName", "TestPW");

        // TODO: Fix Errors
        assertFalse(createBuyer.getUserContainer().isEmpty());
        assertFalse(createBuyer.getCreatedBuyers().isEmpty());


    }

}
