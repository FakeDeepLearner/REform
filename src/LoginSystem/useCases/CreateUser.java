package LoginSystem.useCases;

import LoginSystem.entities.AdminUser;
import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import LoginSystem.exceptions.UserNotFoundException;
import LoginSystem.exceptions.UsernameAlreadyExistsException;
import RealEstate.entities.UserType;

import java.util.ArrayList;

public class CreateUser {
    private final UserContainer<String, User> interfaceUsers;

    public CreateUser(UserContainer<String, User> interface_users) {
        this.interfaceUsers = interface_users;
    }

//    public void createNonAdminUser(UserType type, String username, String password) {
//        if (uniqueUsernameExists(username)) {
//            User user = new NonAdminUser(type, username, password);
//            interfaceUsers.put(username, user);
//        } else {
//            throw new UsernameAlreadyExistsException();
//        }
//    }
//
//    public void createNonAdminUser(UserType type, String username, String password, ArrayList<String> loginHistory) {
//        if (uniqueUsernameExists(username)) {
//            User user = new NonAdminUser(type, username, password, loginHistory);
//            interfaceUsers.put(username, user);
//        } else {
//            throw new UsernameAlreadyExistsException();
//        }
//    }

    public void createAdminUser(String username, String password) {
        User user = new AdminUser(username, password);
        interfaceUsers.put(username, user);
    }

    public void createAdminUser(String username, String password, ArrayList<String> loginHistory) {
        User user = new AdminUser(username, password, loginHistory);
        interfaceUsers.put(username, user);
    }
}
