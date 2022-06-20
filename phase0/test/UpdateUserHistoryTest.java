import org.junit.*;
import Entities.*;
import useCases.UpdateUserHistory;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class UpdateUserHistoryTest {

    @Test
    public void testReadWriteHistory() {
        NonAdminUser u = new NonAdminUser("u1", "p1");
        UserContainer<String, User> container = new UserContainer<>();
        container.put("u1", u);

        UpdateUserHistory updater = new UpdateUserHistory(container);

        updater.overwriteUserHistories();

        updater.writeUserHistory("u1", true);
        ArrayList<String> userHistory = updater.readUserHistory("u1");

        assertEquals(1, userHistory.size());
    }
}
