import Entities.User;
import Entities.UserContainer;
import org.junit.jupiter.api.Test;
import useCases.CreateUser;

import static org.junit.Assert.assertTrue;

public class CreateUserTest {
    @Test
    public void testcreateNonAdminUser() {


        UserContainer<String, User> container = new UserContainer<>();

        CreateUser create = new CreateUser(container);
        create.createNonAdminUser("user1","u1");

        User user=container.get("user1");
        assertTrue(user.getPassword()=="u1");
    }
    @Test
    public void testcreateAdminUser() {


        UserContainer<String, User> container = new UserContainer<>();

        CreateUser create = new CreateUser(container);
        create.createAdminUser("user2","u2");

        User user=container.get("user2");
        assertTrue(user.getPassword()=="u2");
    }
}
