package useCases.userUseCases;

import entities.AdminUser;
import entities.User;
import entities.containers.UserContainer;

import java.util.HashMap;

public class CreateUser {
    private final UserContainer<String, User> interfaceUsers;

    private final HashMap<String, AdminUser> createdAdmins;

    public CreateUser(UserContainer<String, User> interface_users) {
        this.interfaceUsers = interface_users;
        createdAdmins = new UserContainer<>();
    }

    public void createAdminUser(String username, String password) {
        AdminUser user = new AdminUser(username, password);
        interfaceUsers.put(username, user);
        createdAdmins.put(username, user);
    }

    public HashMap<String, AdminUser> getCreatedAdmins() {
        return createdAdmins;
    }
}
