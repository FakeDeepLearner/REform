package useCases;

import Entities.Bannable;
import Entities.NonAdminUser;
import Entities.User;
import Entities.UserNameAndPasswordContainer;
import Exceptions.UserNotFoundException;
import Exceptions.UserBannedException;

public class AuthenticateUser {
    private final UserNameAndPasswordContainer<String, User> interface_users;

    public AuthenticateUser(UserNameAndPasswordContainer<String, User> interface_users) {
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
     * Logs specified Entities.User u out.
     *
     * @param u the Entities.User to log out.
     * @return true when the user is successfully logged out.
     */
    public boolean logoutUser(User u) {
        u.setIsLoggedIn(false);
        return true;
    }

    /**
     * Checks if there is a User with the given username.
     *
     * @param username to check.
     * @return true if a User has the specified username.
     */
    public boolean checkUsernameExists(String username) {
        try {
            User u = interface_users.get(username);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

}
