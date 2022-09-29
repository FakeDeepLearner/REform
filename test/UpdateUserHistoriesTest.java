import entities.users.User;
import entities.containers.UserContainer;
import gateways.HistoriesCSVController;
import org.junit.Test;
import useCases.userUseCases.UpdateUserHistory;
import useCases.userUseCases.UserFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UpdateUserHistoriesTest {
    @Test
    public void TestAddUserHistory() throws InterruptedException {
        UserContainer<String, User> users = new UserContainer<>();
        UserFactory userFactory = new UserFactory(users);

        userFactory.createUser("ADMIN", "a1", "p1");

        UpdateUserHistory history = new UpdateUserHistory(users, new HistoriesCSVController(users));

        history.addLoginHistory("a1");
        assert users.get("a1").getLoginHistory().size() == 1;

        TimeUnit.SECONDS.sleep(1);
        history.addLoginHistory("a1", (new Date()).toString());
        assert users.get("a1").getLoginHistory().size() == 2;
    }
}
