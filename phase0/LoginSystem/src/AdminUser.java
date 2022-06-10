import java.util.ArrayList;
import java.util.Calendar;

public class AdminUser extends User {
    private String username;
    private String password;
    private ArrayList<Calendar> loginHistory;

    public AdminUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public ArrayList<Calendar> getLoginHistory() {
        return loginHistory;
    }

    boolean logIn(String password) {
        if (this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void banUser(Bannable u) {
        u.temporarilyBanUser();
    }

    public void unbanUser(Bannable u) {
        if (u.checkUserBanStatus()) {
            u.unbanUser();
        }
    }
}