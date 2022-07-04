package LoginSystem.entities;

import java.util.ArrayList;

public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
        this.isAdmin = true;
    }

    public AdminUser(String username, String password, ArrayList<String> loginHistory) {
        super(username, password, loginHistory);
        this.isAdmin = true;
    }

    @Override
    public boolean isAdmin(){
        return true;
    }
}