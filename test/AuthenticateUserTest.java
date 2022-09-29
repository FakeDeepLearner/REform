import entities.users.User;
import entities.containers.UserContainer;
import exceptions.UserBannedException;
import org.junit.Test;
import useCases.userUseCases.AuthenticateUser;
import useCases.userUseCases.UserFactory;

public class AuthenticateUserTest {
    @Test
    public void TestLogin() {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);

        userFactory.createUser("ADMIN", "a1", "p1");

        AuthenticateUser auth = new AuthenticateUser(users);

        try {
            auth.loginUser("a1", "p2");
            assert !users.get("a1").isLoggedIn();

            auth.loginUser("a1", "p1");
            assert users.get("a1").isLoggedIn();

        } catch(UserBannedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestLogout() {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);

        userFactory.createUser("ADMIN", "a1", "p1");
        users.get("a1").setIsLoggedIn(true);

        AuthenticateUser auth = new AuthenticateUser(users);
        auth.logoutUser("a1");
        assert !users.get("a1").isLoggedIn();
    }
}
