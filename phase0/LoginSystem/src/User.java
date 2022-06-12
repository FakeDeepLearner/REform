import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
    private String username;
    private String password;
    private ArrayList<Calendar> loginHistory;

    protected Boolean isAdmin = false;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        loginHistory = new ArrayList<>();
    }

    protected User(String username, String password, ArrayList<Calendar> loginHistory) {
        this.username = username;
        this.password = password;
        this.loginHistory = loginHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Calendar> getLoginHistory() {
        return loginHistory;
    }
}
