import Exceptions.UserNotFoundException;

public class AuthenticateUser {
    private UserNameAndPasswordContainer<String, User> interface_users;

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
    public boolean loginUser(String username, String password) {
        try {
            User u = interface_users.get(username);
            if (u.getPassword().equals(password)) {
                u.setIsLoggedIn(true);
                return true;
            } else {
                return false;
            }
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    /**
     * Logs specified User u out.
     *
     * @param u the User to log out.
     * @return true when the user is successfully logged out.
     */
    public boolean logoutUser(User u) {
        u.setIsLoggedIn(false);
        return true;
    }

}
