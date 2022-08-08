import entities.User;
import entities.containers.UserContainer;
import org.junit.Test;
import useCases.userUseCases.RestrictUser;
import useCases.userUseCases.UserFactory;

public class RestrictUserTest {
    @Test
    public void TestBanUser() {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);

        userFactory.createUser("ADMIN", "a1", "p1");
        userFactory.createUser("BUYER", "b1", "p1");

        RestrictUser restrict = new RestrictUser(users);
        assert !restrict.banNonAdminUser("a1");

        assert restrict.banNonAdminUser("b1");
        assert users.getBuyer("b1").checkUserBanStatus();
    }

    @Test
    public void TestUnbanUser() {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);

        userFactory.createUser("ADMIN", "a1", "p1");
        userFactory.createUser("BUYER", "b1", "p1");

        RestrictUser restrict = new RestrictUser(users);
        restrict.banNonAdminUser("b1");
        assert restrict.unbanNonAdminUser("b1");
        assert !users.getBuyer("b1").checkUserBanStatus();
    }
}
