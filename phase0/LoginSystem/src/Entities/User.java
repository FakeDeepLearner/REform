package Entities;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private String password;
    private ArrayList<String> loginHistory;

    protected Boolean isAdmin = false;

    private boolean isLoggedIn = false;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        loginHistory = new ArrayList<>();
    }

    protected User(String username, String password, ArrayList<String> loginHistory) {
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

    public ArrayList<String> getLoginHistory() {
        return loginHistory;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoginHistory(ArrayList<String> arr) {
        loginHistory = arr;
    }

    public void addToLoginHistory(String newLogin) {
        loginHistory.add(newLogin);
    }

    public void setIsLoggedIn(boolean bool) {
        isLoggedIn = bool;
    }

    public abstract boolean isAdmin();
}
