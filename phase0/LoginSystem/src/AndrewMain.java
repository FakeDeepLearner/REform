import Entities.AdminUser;
import Entities.NonAdminUser;
import Entities.UserNameAndPasswordContainer;
import databaseManagers.UsernamePasswordFileManager;

import java.io.IOException;

public class AndrewMain {

    public static void main(String[] args) throws IOException {

        UsernamePasswordFileManager manager = new UsernamePasswordFileManager();

        NonAdminUser u1 = new NonAdminUser("u1", "pw1");
        NonAdminUser u2 = new NonAdminUser("u2", "pw2");
        NonAdminUser u3 = new NonAdminUser("u3", "pw3");

        UserNameAndPasswordContainer c = new UserNameAndPasswordContainer();

        c.put("u1", u1);
        c.put("u2", u1);
        c.put("u3", u1);

        manager.createUsernamePasswordFile(c);

        AdminUser u4 = new AdminUser("u3", "pw3");


        manager.addUserInfo(u4);

    }
}
