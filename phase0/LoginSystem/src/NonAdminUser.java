import java.util.ArrayList;
import java.util.Calendar;

public class NonAdminUser extends User implements Bannable {
    private boolean isBanned = false;

    /**
     * Creates an instance of a non-admin user.
     * @param username this user's username.
     * @param password this user's password.
     */
    public NonAdminUser(String username, String password) {
        super(username, password);
    }

    public NonAdminUser(String username, String password, ArrayList<Calendar> loginHistory) {
        super(username, password, loginHistory);
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