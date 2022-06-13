public class CreateUser {
    public CreateUser(UserNameAndPasswordContainer<String, User> interface_users, String username, String password, Boolean isAdmin) {
        User user;
        if (isAdmin){
            user = new AdminUser(username, password);
        }else{
            user = new NonAdminUser(username, password);
        }
        interface_users.put(username,user);
    }
}
