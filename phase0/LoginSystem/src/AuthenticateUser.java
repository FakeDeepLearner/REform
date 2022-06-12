public class AuthenticateUser {
    private UserNameAndPasswordContainer<String, User> interface_users;

    public AuthenticateUser(UserNameAndPasswordContainer<String, User> interface_users) {
        this.interface_users = interface_users;
    }
}
