import java.util.ArrayList;
import java.util.Calendar;

public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
        this.isAdmin = true;
    }

    public AdminUser(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
        this.isAdmin = true;
    }
}