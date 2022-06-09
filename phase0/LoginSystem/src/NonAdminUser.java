import java.util.ArrayList;
import java.util.Calendar;

public class NonAdminUser extends User implements Bannable {
    private ArrayList<Calendar> loginTimes;
    private boolean isBanned;
    private String username;
    private String password;

    /**
     * Creates an instance of a non-admin user.
     * @param username this user's username.
     * @param password this user's password.
     */
    public NonAdminUser(String username, String password) {
        this.username = username;
        this.password = password;
        isBanned = false;

        loginTimes = new ArrayList<>();
        loginTimes.add(Calendar.getInstance());
    }

    /**
     * Logs this user in.
     * @return true if the user has been successfully logged in.
     */
    public boolean logIn() {
        loginTimes.add(Calendar.getInstance());
        return true;
    }

    /**
     * Return this user's login history.
     * @return an arraylist of Calendar instances.
     */
    public ArrayList<Calendar> getLoginHistory() {
        return loginTimes;
    }

    /**
     * Returns this user's username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns this user's password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns whether the user is currently banned.
     * @return true if the user is banned and false if not.
     */
    public boolean checkUserBanStatus() {
        return isBanned;
    }

    /**
     * Bans user temporarily.
     * @return true to signal the user has been banned.
     */
    public boolean temporarilyBanUser() {
        isBanned = true;
        return true;
    }

    /**
     * Unbans user.
     * @return true to signal the user has been unbanned.
     */
    public boolean unbanUser() {
        isBanned = false;
        return true;
    }
}