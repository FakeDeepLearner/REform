import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
    abstract boolean logIn();
    abstract ArrayList<Calendar> getLoginHistory();

    abstract String getUsername();

    abstract String getPassword();

    abstract boolean checkUserBanStatus();

    abstract boolean temporarilyBanUser();

    abstract boolean unbanUser();


}
