package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import useCases.CreateUser;

import java.util.ArrayList;

public class UserManager {
    private final InputHandler inputHandler;
    private final UserInterface ui;
    private final CreateUser createUser;

    public UserManager(InputHandler inputHandler, UserInterface ui, CreateUser createUser) {
        this.inputHandler = inputHandler;
        this.ui = ui;
        this.createUser = createUser;
    }

    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> username_password = new ArrayList<>();

        ui.printCreateUsernameInput();
        username_password.add(inputHandler.strInput());

        ui.printCreatePasswordInput();
        username_password.add(inputHandler.strInput());

        return username_password;
    }

    public ArrayList<String> createNewUser(boolean isAdmin) {
        // User selected to create a new user
        ArrayList<String> username_password = getUsernameAndPassword();
        // Assume the new user is not an admin b/c only admins can create new admin users
        try {
            if (!isAdmin) {
                createUser.createNonAdminUser(username_password.get(0), username_password.get(1));
            } else {
                createUser.createAdminUser(username_password.get(0), username_password.get(1));
            }
        } catch (UsernameAlreadyExistsException e) {
            ui.printArbitraryException(e);
            return null;
        }
        ui.printSignUpSuccess();

        return username_password;
    }
}
