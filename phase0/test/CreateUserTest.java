import Entities.User;
import Entities.UserContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import useCases.CreateUser;

public class CreateUserTest {
    @Test
    public void testCreateNonAdminUser() {


        UserContainer<String, User> container = new UserContainer<>();

        CreateUser create = new CreateUser(container);
        create.createNonAdminUser("user1", "u1");

        User user = container.get("user1");
        Assertions.assertEquals("u1", user.getPassword());
    }

    @Test
    public void testCreateAdminUser() {


        UserContainer<String, User> container = new UserContainer<>();

        CreateUser create = new CreateUser(container);
        create.createAdminUser("user2", "u2");

        User user = container.get("user2");
        Assertions.assertEquals("u2", user.getPassword());
    }
}
