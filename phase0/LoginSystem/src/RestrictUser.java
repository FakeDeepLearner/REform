public class RestrictUser {
    private UserNameAndPasswordContainer<String, User> interface_users;

    public RestrictUser(UserNameAndPasswordContainer<String, User> interface_users) {
        this.interface_users = interface_users;
    }
}
