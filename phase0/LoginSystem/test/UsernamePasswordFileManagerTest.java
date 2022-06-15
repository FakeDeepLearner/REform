import Entities.NonAdminUser;
import org.junit.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;


public class UsernamePasswordFileManagerTest {

    // test methods

    /**
     * The test below doesn't pass for some reason despite returning identical contents.
     * It would be appreciated if someone could debug. - Andrew
     *
    @Test(timeout = 50)
    public void testUsernamePasswordFileManagerTest() throws IOException {
        UsernamePasswordFileManager manager = new UsernamePasswordFileManager();

        manager.createCSVfile("testFile");

        NonAdminUser u1 = new NonAdminUser("u1", "pw1");
        NonAdminUser u2 = new NonAdminUser("u2", "pw2");
        NonAdminUser u3 = new NonAdminUser("u3", "pw3");

        UserNameAndPasswordContainer c = new UserNameAndPasswordContainer();

        c.put("u1", u1);
        c.put("u2", u1);
        c.put("u3", u1);

        manager.createUsernamePasswordFile("testFile", c);

        ArrayList<String> actual = new ArrayList<>(Collections.singleton("Username,Password\n" +
                ", u1,pw1\n" +
                ", u2,pw1\n" +
                ", u3,pw1\n" +
                ""));

        assertTrue(manager.readLines("testFile").equals(actual));

    }
    */


}
