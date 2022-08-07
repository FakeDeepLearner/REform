package useCases.userUseCases;

import entities.Bannable;
import entities.NonAdminUser;
import entities.User;
import entities.containers.UserContainer;
import exceptions.UserBannedException;
import exceptions.UserNotFoundException;
import exceptions.SellerNotFoundException;

public class AuthenticateUser {
    private final UserContainer<String, User> interfaceUsers;

    public AuthenticateUser(UserContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;
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
            u = interfaceUsers.get(username);
        } catch (IllegalArgumentException e) {
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
            u = interfaceUsers.get(username);
        } catch (UserNotFoundException e) {
            return false;
        }

        u.setIsLoggedIn(false);
        return true;
    }

    /**
     * Returns whether the user logging in is an admin
     *
     * @param username the username of the user trying to log in
     * @return true is the user is an admin
     */
    public boolean checkUserAdmin(String username) {
        User u = interfaceUsers.get(username);
        return u.getAdmin();
    }

    /**
     * Returns whether the user logging in is a seller
     *
     * @param username the username of the user trying to log in
     * @return true is the user is a seller
     */
    public boolean checkUserSeller(String username) {
        try {
            interfaceUsers.getSeller(username);
            return true;
        } catch (SellerNotFoundException e) {
            return false;
        }
    }


    /**
     * Checks if the specified user exists and is not an admin
     *
     * @param username of the user being checked
     * @return true if the user exists and is not an admin
     */
    public boolean checkUserNonAdminExists(String username) {
        try {
            interfaceUsers.get(username);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

}
