package LoginSystem.entities;

import RealEstate.entities.UserType;

import java.util.ArrayList;

public abstract class User {
    private final String username;
    private final String password;
    private final ArrayList<String> loginHistory;
    private final UserType type;

    protected Boolean isAdmin = false;

    private boolean isLoggedIn = false;

    /**
     * @param type of the User
     * @param username of the User
     * @param password of the User
     */
    protected User(UserType type, String username, String password) {
        this.type = type;
        this.username = username;
        this.password = password;
        loginHistory = new ArrayList<>();
    }

    /**
     * @param type of the User
     * @param username of the User
     * @param password of the User
     * @param loginHistory of the User
     */
    protected User(UserType type, String username, String password, ArrayList<String> loginHistory) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.loginHistory = loginHistory;
    }

    /**
     * @return the username of the User
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password of the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the login history of the User
     */
    public ArrayList<String> getLoginHistory() {
        return loginHistory;
    }

    /**
     * @return admin status of the User
     */
    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * @return whether the User is logged in
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * @param newLogin to be added to the login history of the User
     */
    public void addToLoginHistory(String newLogin) {
        loginHistory.add(newLogin);
    }

    /**
     * @param newLogins to be added to the login history of the User
     */
    public void addToLoginHistory(ArrayList<String> newLogins) {
        loginHistory.addAll(newLogins);
    }

    /**
     * @param bool to set login status to
     */
    public void setIsLoggedIn(boolean bool) {
        isLoggedIn = bool;
    }
}
