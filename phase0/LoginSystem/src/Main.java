import Controllers.*;
import Entities.*;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.RestrictUser;
import useCases.UpdateUserHistory;

public class Main{
    public static void main(String[] args) {
        // Initiate the classes
        UserContainer<String, User> users = new UserContainer<>();
        // TODO: Load the database to <users>

        AuthenticateUser auth = new AuthenticateUser(users);
        CreateUser createUser = new CreateUser(users);
        UpdateUserHistory history = new UpdateUserHistory(users);
        RestrictUser restrict = new RestrictUser(users);

        UserInterface ui = new UserInterface();
        InputHandler inputHandler = new InputHandler(ui);

        UserManager userManager = new UserManager(inputHandler, ui, createUser);
        LoggedOutManager loggedOutManager = new LoggedOutManager(inputHandler, ui, auth, history, userManager);
        LoggedInManager loggedInManager = new LoggedInManager(inputHandler, ui, auth, history, restrict, userManager);

        while (true) {
            String username = loggedOutManager.menuSelector();

            if (username == null) {
                continue;
            }

            boolean isLoggedIn = true;
            while (isLoggedIn) {
                isLoggedIn = loggedInManager.userScreen(username);
            }
        }
    }
}
