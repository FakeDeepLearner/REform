package Controllers;

import Exceptions.UsernameAlreadyExistsException;
import databaseManagers.UsernamePasswordFileManager;
import useCases.CreateUser;

import java.io.IOException;
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

    public void loadUsersFromCSV() {
        UsernamePasswordFileManager file = new UsernamePasswordFileManager();

        ArrayList<ArrayList<String>> users;
        try {
            users = file.getUsersFromCSV();
        } catch (IOException e) {
            ui.printArbitraryException(e);
            return;
        }

        for (ArrayList<String> user : users) {
            if (Boolean.parseBoolean(user.get(2))) {
                createUser.createAdminUser(user.get(0), user.get(1));
            } else {
                createUser.createNonAdminUser(user.get(0), user.get(1));
            }
        }
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
