package useCases;

import Entities.AdminUser;
import Entities.NonAdminUser;
import Entities.User;
import Entities.UserContainer;
import Exceptions.UserNotFoundException;
import Exceptions.UsernameAlreadyExistsException;

import java.util.ArrayList;

public class CreateUser {
    private final UserContainer<String, User> interfaceUsers;

    public CreateUser(UserContainer<String, User> interface_users) {
        this.interfaceUsers = interface_users;
    }

    public void createNonAdminUser(String username, String password) {
        if (uniqueUsernameExists(username)) {
            User user = new NonAdminUser(username, password);
            interfaceUsers.put(username, user);
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }

    public void createNonAdminUser(String username, String password, ArrayList<String> loginHistory) {
        if (uniqueUsernameExists(username)) {
            User user = new NonAdminUser(username, password, loginHistory);
            interfaceUsers.put(username, user);
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }

    public void createAdminUser(String username, String password) {
        if (uniqueUsernameExists(username)) {
            User user = new AdminUser(username, password);
            interfaceUsers.put(username, user);
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }

    public void createAdminUser(String username, String password, ArrayList<String> loginHistory) {
        if (uniqueUsernameExists(username)) {
            User user = new AdminUser(username, password, loginHistory);
            interfaceUsers.put(username, user);
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }

    public boolean uniqueUsernameExists(String username) {
        try {
            interfaceUsers.get(username);
            return false;
        } catch (UserNotFoundException e) {
            return true;
        }
    }
}
