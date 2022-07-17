package LoginSystem.entities;

import RealEstate.entities.UserType;

import java.util.ArrayList;

public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(UserType.ADMIN, username, password);
        this.isAdmin = true;
    }

    public AdminUser(String username, String password, ArrayList<String> loginHistory) {
        super(UserType.ADMIN, username, password, loginHistory);
        this.isAdmin = true;
    }
}