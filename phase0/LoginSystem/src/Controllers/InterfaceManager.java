package Controllers;

import Entities.User;
import Entities.UserNameAndPasswordContainer;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.UpdateUserHistory;

import java.util.ArrayList;
import java.util.Collections;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;
    private InputHandler inputHandler;
    private UserInterface ui;
    private AuthenticateUser auth;
    private CreateUser createUser;
    private UpdateUserHistory history;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;

        ui = new UserInterface();
        inputHandler = new InputHandler(ui);
        auth = new AuthenticateUser(interfaceUsers);
        createUser = new CreateUser(interfaceUsers);
        history = new UpdateUserHistory(interfaceUsers);
    }

    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> username_password = new ArrayList<>();

        ui.printUsernameInput();
        username_password.add(inputHandler.strInput());

        ui.printPasswordInput();
        username_password.add(inputHandler.strInput());

        return username_password;
    }

    public User menuSelector() {
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

        if (auth.loginUser(username_password.get(0), username_password.get(1))) {
            ui.printLoginSuccess();

            User u = interfaceUsers.get(username_password.get(0));
            history.writeUserHistory(u, false);

            return u;
        } else {
            ui.printLoginFail();
        }

        return null;
    }

    public void NonAdminScreen(User u) {
        ui.printNonAdminLogInMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2);

        int select = inputHandler.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View login history
                ArrayList<String> userHistory = u.getLoginHistory();
                System.out.println(userHistory);
                break;
            case 2:
                // Logout user
                auth.logoutUser(u);
                ui.printLogOutSuccess();
                break;
        }
    }

    public void AdminScreen(User u) {
        ui.printAdminLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3);

        int select = inputHandler.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View login history
                ArrayList<String> userHistory = u.getLoginHistory();
                System.out.println(userHistory);
                break;
            case 2:
                // Create admin user
                ArrayList<String> username_password = getUsernameAndPassword();
                createUser.createAdminUser(username_password.get(0), username_password.get(1));
                break;
            case 3:
                // Logout user
                auth.logoutUser(u);
                ui.printLogOutSuccess();
                break;
        }
    }
}
