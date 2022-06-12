import java.util.ArrayList;
import java.util.Collections;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;
    }

//    public UserNameAndPasswordContainer<String, User> get_interface_users() {
//        return interfaceUsers;
//    }


    public UserNameAndPasswordContainer<String, User> getInterfaceUsers() {
        return interfaceUsers;
    }

    private void menuSelector(InputHandler inputHandler) {
        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4);

        int menuSelect = inputHandler.intInput(allowedInputs);

        if (menuSelect == 1) {
            String username = inputHandler.strInput();

        } else if (menuSelect == 2) {
            String username = inputHandler.strInput();

            try {
                interfaceUsers.get(username);
            } catch (UserNotFoundException e) {
                // TODO: Create user
            }
        }
    }

    public static void main(String[] args) {
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<String, User>();
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface();
        InputHandler inputHandler = new InputHandler();

        userInterface.printWelcomeMessage();
        manager.menuSelector(inputHandler);
    }
}
