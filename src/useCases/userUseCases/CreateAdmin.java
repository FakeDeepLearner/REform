package useCases.userUseCases;

import entities.AdminUser;
import entities.User;
import entities.containers.UserContainer;
import gateways.DataInterface;

import java.io.IOException;

public class CreateAdmin {
    private final UserContainer<String, User> interfaceUsers;
    public final DataInterface i;

    public CreateAdmin(UserContainer<String, User> interface_users, DataInterface i) {
        this.interfaceUsers = interface_users;
        this.i = i;
    }

    /**
     * Read data from csv files
     */
    public void read() throws IOException {
        UserFactory userFactory = new UserFactory(interfaceUsers);

        for (String[] data : i.read()) {
            userFactory.createUser("admin", data[0], data[1]);
        }
    }

    /**
     * Write data to csv files
     */
    public void write() throws IOException {
        i.write();
    }

    public void createAdminUser(String username, String password) {
        AdminUser user = new AdminUser(username, password);
        interfaceUsers.put(username, user);
    }

}
