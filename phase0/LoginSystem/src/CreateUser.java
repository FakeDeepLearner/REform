public class CreateUser {
    private UserNameAndPasswordContainer<String, User> interface_users;
    public CreateUser(UserNameAndPasswordContainer<String, User> interface_users) {
        this.interface_users=interface_users;
    }
    public void createNonAdminUser(String username, String password){
        User user = new NonAdminUser(username, password);
        interface_users.put(username,user);
    }
    public void createAdminUser(String username, String password){
        User user = new AdminUser(username, password);
        interface_users.put(username,user);
    }
}
