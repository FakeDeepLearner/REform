package Controllers;

import Entities.Bannable;
import Entities.NonAdminUser;
import Entities.User;
import Entities.UserNameAndPasswordContainer;
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
    public InterfaceManager(InputHandler inputHandler,
                            UserInterface ui,
                            AuthenticateUser auth,
                            CreateUser createUser,
                            UpdateUserHistory history,
                            RestrictUser restrict) {
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
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5);

        int select = inputHandler.intInput(allowedInputs);
        String username;
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
                // Ban or unban user
                ui.printUsernameInput();
                username = inputHandler.strInput();

                User v;
                try {
                    v = interfaceUsers.get(username);
                } catch (UserNotFoundException e) {
                    // TODO: print error message to UI
                    break;
                }

                if (v instanceof Bannable) {
                    if (!((Bannable) v).checkUserBanStatus()) {
                        // TODO: ask for user confirmation
                        restrict.banNonAdminUser(username);
                    } else {
                        // TODO: ask for user confirmation
                        restrict.unbanNonAdminUser(username);
                    }
                } else {
                    // TODO: print user cannot be banned to UI
                }
                break;

            case 4:
                // Delete user
                ui.printUsernameInput();
                username = inputHandler.strInput();
                boolean deleted = restrict.deleteNonAdminUser(username);

                if (deleted) {
                    // TODO: provide feedback to UI
                } else {
                    // TODO: provide feedback to UI
                }
                break;

            case 5:
                // Logout user
                auth.logoutUser(u);
                ui.printLogOutSuccess();
                break;
        }
    }
}
