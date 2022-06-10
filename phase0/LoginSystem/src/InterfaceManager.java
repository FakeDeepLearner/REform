import java.util.HashMap;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interface_users;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interface_users){
        this.interface_users = interface_users;
    }

    public UserNameAndPasswordContainer<String, User> get_interface_users() {
        return interface_users;
    }
}
