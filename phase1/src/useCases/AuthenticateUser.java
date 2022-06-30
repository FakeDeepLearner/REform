package useCases;

import entities.Bannable;
import entities.NonAdminUser;
import entities.User;
import entities.UserContainer;
import exceptions.UserNotFoundException;
import exceptions.UserBannedException;

public class AuthenticateUser {
    private final UserContainer<String, User> interface_users;

    public AuthenticateUser(UserContainer<String, User> interface_users) {
        this.interface_users = interface_users;
    }

    /**
     * Authenticates login information provided.
     *
     * @param username of the user to log in.
     * @param password of the user to log in.
     * @return true if the user is successfully logged in.
     */
    public boolean loginUser(String username, String password) throws UserBannedException {
        User u;
        try {
            u = interface_users.get(username);
        } catch (UserNotFoundException e) {
            return false;
        }

        if (u.getPassword().equals(password)) {
            if (u instanceof Bannable) {
                NonAdminUser user = (NonAdminUser) u;
                if (!user.checkUserBanStatus()) {
                    u.setIsLoggedIn(true);
                    return true;
                } else {
                    throw new UserBannedException();
                }
            } else {
                u.setIsLoggedIn(true);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Logs specified entities.User u out.
     *
     * @param username the username of the User instance to log out.
     * @return true when the user is successfully logged out.
     */
    public boolean logoutUser(String username) {
        User u;
        try {
            u = interface_users.get(username);
        } catch (UserNotFoundException e) {
            return false;
        }

        u.setIsLoggedIn(false);
        return true;
    }

    public boolean checkUserAdmin(String username) {
        User u = interface_users.get(username);
        return u.getAdmin();
    }
}
