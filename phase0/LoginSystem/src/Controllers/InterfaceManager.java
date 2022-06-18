package Controllers;

import Exceptions.UserBannedException;
import Exceptions.UserCannotBeBannedException;
import Exceptions.UserNotFoundException;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.RestrictUser;
import useCases.UpdateUserHistory;

import java.util.ArrayList;
import java.util.Collections;

public class InterfaceManager {

    private InputHandler inputHandler;
    private UserInterface ui;
    private AuthenticateUser auth;
    private CreateUser createUser;
    private UpdateUserHistory history;
    private RestrictUser restrict;

    public InterfaceManager(){

    }
    public InterfaceManager(InputHandler inputHandler, UserInterface ui, AuthenticateUser auth, CreateUser createUser,
                            UpdateUserHistory history, RestrictUser restrict) {
        this.inputHandler = inputHandler;
        this.ui = ui;
        this.auth = auth;
        this.createUser = createUser;
        this.history = history;
        this.restrict = restrict;
    }

    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> username_password = new ArrayList<>();

        ui.printUsernameInput();
        username_password.add(inputHandler.strInput());

        ui.printPasswordInput();
        username_password.add(inputHandler.strInput());

        return username_password;
    }

    public String menuSelector() {
        ui.printWelcomeMessage();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3);

        int menuSelect = inputHandler.intInput(allowedInputs);

        ArrayList<String> username_password = new ArrayList<>();
        switch (menuSelect) {
            case 1:
                // User selected to create a new user
                username_password = getUsernameAndPassword();
                // Assume the new user is not an admin b/c only admins can create new admin users
                createUser.createNonAdminUser(username_password.get(0), username_password.get(1));
                ui.printSignUpSuccess();
                break;

            case 2:
                // User selected to log in
                username_password = getUsernameAndPassword();
                break;

            case 3:
                // Secret admin user creator
                username_password = getUsernameAndPassword();
                createUser.createAdminUser(username_password.get(0), username_password.get(1));
                ui.printSignUpSuccess();
                break;
        }

        boolean isLoggedIn = false;
        try {
            isLoggedIn = auth.loginUser(username_password.get(0), username_password.get(1));
        } catch (UserNotFoundException e) {
            // TODO: print error
        } catch (UserBannedException e) {
            // TODO: print error
        }

        if (isLoggedIn) {
            ui.printLoginSuccess();

            history.writeUserHistory(username_password.get(0), false);

            return username_password.get(0);
        } else {
            ui.printLoginFail();
        }

        return null;
    }

    public void NonAdminScreen(String username) {
        ui.printNonAdminLogInMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2);

        int select = inputHandler.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                System.out.println(userHistory);
                break;

            case 2:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                break;
        }
    }

    public void AdminScreen(String username) {
        ui.printAdminLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5);

        int select = inputHandler.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                System.out.println(userHistory);
                break;

            case 2:
                // Create admin user
                ArrayList<String> username_password = getUsernameAndPassword();
                createUser.createAdminUser(username_password.get(0), username_password.get(1));
                break;

            case 3:
                // Ban or unban user
                ui.printUsernameInputForUserRestriction();
                String restrictUser = inputHandler.strInput();

                boolean isBanned = false;
                try {
                    isBanned = restrict.isUserBanned(restrictUser);
                } catch (UserCannotBeBannedException e) {
                    // TODO: print error message
                }

                if (!isBanned) {
                    // TODO: prince user is currently unbanned and ask for confirmation
                    restrict.unbanNonAdminUser(restrictUser);
                } else {
                    // TODO: prince user is currently banned and ask for confirmation
                    restrict.banNonAdminUser(restrictUser);
                }

                break;

            case 4:
                // Delete user
                ui.printUsernameInput();
                String deleteUser = inputHandler.strInput();
                boolean deleted = restrict.deleteNonAdminUser(deleteUser);

                if (deleted) {
                    // TODO: provide feedback to UI
                } else {
                    // TODO: provide feedback to UI
                }
                break;

            case 5:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                break;
        }
    }
}
