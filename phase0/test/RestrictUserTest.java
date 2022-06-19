import Exceptions.UserBannedException;
import org.junit.*;
import Entities.*;
import useCases.AuthenticateUser;
import useCases.RestrictUser;

import static org.junit.Assert.*;

public class RestrictUserTest {

    @Test(timeout = 50)
    public void testBanNonAdminUser() {

        NonAdminUser user1 = new NonAdminUser("user1", "u1");
        AdminUser user2 = new AdminUser("user2", "u2");

        UserContainer<String, User> container = new UserContainer<>();

        RestrictUser restrict = new RestrictUser(container);

        container.put("user1", user1);
        container.put("user2", user2);

        assertTrue(restrict.banNonAdminUser("user1"));
        assertTrue(user1.checkUserBanStatus());

        assertFalse(restrict.banNonAdminUser("user2"));
    }

    @Test(timeout = 50)
    public void testUnbanNonAdminUser() {

        NonAdminUser user1 = new NonAdminUser("user1", "u1");
        user1.temporarilyBanUser();

        AdminUser user2 = new AdminUser("user2", "u2");

        UserContainer<String, User> container = new UserContainer<>();

        RestrictUser restrict = new RestrictUser(container);

        container.put("user1", user1);
        container.put("user2", user2);

        assertTrue(restrict.unbanNonAdminUser("user1"));
        assertFalse(user1.checkUserBanStatus());

        assertFalse(restrict.unbanNonAdminUser("user2"));
    }

    @Test(timeout = 50)
    public void testDeleteNonAdminUser() {

        NonAdminUser user1 = new NonAdminUser("user1", "u1");
        AdminUser user2 = new AdminUser("user2", "u2");

        UserContainer<String, User> container = new UserContainer<>();

        RestrictUser restrict = new RestrictUser(container);

        container.put("user1", user1);
        container.put("user2", user2);

        assertTrue(restrict.deleteNonAdminUser("user1"));
        assertFalse(container.containsKey("user1"));

        assertFalse(restrict.deleteNonAdminUser("user2"));
    }
}
