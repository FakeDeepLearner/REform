package LoginSystem.useCases;

import LoginSystem.entities.Bannable;
import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import LoginSystem.exceptions.UserBannedException;
import LoginSystem.exceptions.UserNotFoundException;
import RealEstate.exceptions.SellerNotFoundException;

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

    public boolean checkUserSeller(String username) {
        try{
            interface_users.getSeller(username);
            return true;
        }
        catch (SellerNotFoundException e){
            return false;
        }
    }

    public boolean checkUserNonAdminExists(String username) {
        try{
            interface_users.get(username);
            return true;
        }
        catch (UserNotFoundException e){
            return false;
        }
    }

}
