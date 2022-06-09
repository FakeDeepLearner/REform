import java.util.HashMap;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String> interface_users;

    public InterfaceManager(UserNameAndPasswordContainer<String> interface_users){
        this.interface_users = interface_users;
    }

    public UserNameAndPasswordContainer<String> get_interface_users() {
        return interface_users;
    }
}
