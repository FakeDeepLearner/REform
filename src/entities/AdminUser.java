package entities;

import java.util.ArrayList;

public class AdminUser extends User {
    /**
     * Creates an instance of an AdminUser
     *
     * @param username of the User
     * @param password of the User
     */
    public AdminUser(String username, String password) {
        super(username, password);
        this.isAdmin = true;
    }

    /**
     * Creates an instance of an AdminUser
     *
     * @param username of the User
     * @param password of the User
     * @param loginHistory of the User
     */
    public AdminUser(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
        this.isAdmin = true;
    }
}