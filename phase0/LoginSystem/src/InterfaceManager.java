public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;
    }

    public UserNameAndPasswordContainer<String, User> get_interface_users() {
        return interfaceUsers;
    }

    private void menuSelector(InputHandler inputHandler) {
        String choice = inputHandler.loginInput();
        int input;
        try {
            input = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            throw new UndefinedInputException();
        }

        if (input == 1) {
            String username = inputHandler.signUpInput();

            try {
                interfaceUsers.get(username);
            } catch (UserNotFoundException e) {
                // TODO: Create user
            }

        } else if (input == 2) {
            String username = inputHandler.signUpInput();
            // TODO: Create user
        }
    }

    public static void main(String[] args) {
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<String, User>();
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface(manager);
        InputHandler inputHandler = new InputHandler(manager);

        userInterface.printWelcomeMessage();
        manager.menuSelector(inputHandler);
    }
}
