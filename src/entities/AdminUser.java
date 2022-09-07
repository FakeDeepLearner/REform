package entities;

public non-sealed class AdminUser extends User {
    /**
     * Creates an instance of an AdminUser
     *
     * @param username of the User
     * @param password of the User
     */
    public AdminUser(String username, String password) {
        super(username, password);
        this.isAdmin = true;
    }

}