import Controllers.*;
import Entities.*;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.RestrictUser;
import useCases.UpdateUserHistory;

public class Main{
    public static void main(String[] args) {
        // Initiate the classes
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<>();
        // TODO: Load the database to <users>


        AuthenticateUser auth = new AuthenticateUser(users);
        CreateUser createUser = new CreateUser(users);
        UpdateUserHistory history = new UpdateUserHistory(users);
        RestrictUser restrict = new RestrictUser(users);

        UserInterface ui = new UserInterface();
        InputHandler inputHandler = new InputHandler(ui);

        InterfaceManager manager = new InterfaceManager(inputHandler, ui, auth, createUser, history, restrict);

        while (true) {
            String username = manager.menuSelector();

            if (username == null) {
                continue;
            }

            boolean isLoggedIn = true;
            while (isLoggedIn) {
                isLoggedIn = manager.userScreen(username);
            }
        }
    }
}
