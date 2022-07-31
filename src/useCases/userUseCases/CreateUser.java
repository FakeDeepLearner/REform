package useCases.userUseCases;

import entities.AdminUser;
import entities.User;
import entities.containers.UserContainer;

public class CreateUser {
    private final UserContainer<String, User> interfaceUsers;

    public CreateUser(UserContainer<String, User> interface_users) {
        this.interfaceUsers = interface_users;
    }

    public void createAdminUser(String username, String password) {
        User user = new AdminUser(username, password);
        interfaceUsers.put(username, user);
    }
}
