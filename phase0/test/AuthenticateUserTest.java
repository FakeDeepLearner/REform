import Exceptions.UserBannedException;
import org.junit.*;
import Entities.*;
import useCases.AuthenticateUser;

import static org.junit.Assert.*;

public class AuthenticateUserTest {

    @Test(timeout = 50)
    public void testLoginUser() throws UserBannedException {

        NonAdminUser user = new NonAdminUser("user1", "u1");

        UserContainer<String, User> container = new UserContainer<>();

        AuthenticateUser authUser = new AuthenticateUser(container);

        container.put("user1", user);

        assertTrue(authUser.loginUser("user1", "u1"));
        assertTrue(user.isLoggedIn());
    }

    @Test(timeout = 50)
    public void testLogoutUser() {

        NonAdminUser user = new NonAdminUser("user1", "u1");

        UserContainer<String, User> container = new UserContainer<>();

        AuthenticateUser authUser = new AuthenticateUser(container);

        container.put("user1", user);

        assertTrue(authUser.logoutUser("user1"));
        assertFalse(user.isLoggedIn());
    }
}
