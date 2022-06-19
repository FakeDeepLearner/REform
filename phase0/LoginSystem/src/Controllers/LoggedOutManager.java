package Controllers;

import Entities.User;
import Exceptions.UserBannedException;
import Exceptions.UserCannotBeBannedException;
import Exceptions.UserNotFoundException;
import Exceptions.UsernameAlreadyExistsException;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.RestrictUser;
import useCases.UpdateUserHistory;

import java.util.ArrayList;
import java.util.Collections;

public class LoggedOutManager {

    private final InputHandler input;
    private final UserInterface ui;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final UserManager userManager;

    public LoggedOutManager(InputHandler input, UserInterface ui, AuthenticateUser auth, UpdateUserHistory history, UserManager userManager) {
        this.input = input;
        this.ui = ui;
        this.auth = auth;
        this.history = history;
        this.userManager = userManager;
    }

    private String loginUser(ArrayList<String> usernamePassword) {
        boolean isLoggedIn;
        try {
            isLoggedIn = auth.loginUser(usernamePassword.get(0), usernamePassword.get(1));
        } catch (UserNotFoundException | UserBannedException e) {
            ui.printArbitraryException(e);
            return null;
        }

        if (isLoggedIn) {
            ui.printLoginSuccess();
            history.writeUserHistory(usernamePassword.get(0), true);

            return usernamePassword.get(0);
        } else {
            ui.printLoginFail();
        }

        return null;
    }

    public String menuSelector() {
        ui.printWelcomeMessage();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3);

        int menuSelect = input.intInput(allowedInputs);

        ArrayList<String> usernamePassword = new ArrayList<>();
        switch (menuSelect) {
            case 1: {
                usernamePassword = userManager.createNewUser(false);
                break;
            }

            case 2: {
                // User selected to log in
                ui.printExistingUsernameInput();
                usernamePassword.add(input.strInput());
                ui.printExistingPasswordInput();
                usernamePassword.add(input.strInput());
                break;
            }

            case 3: {
                usernamePassword = userManager.createNewUser(true);
                break;
            }
        }

        if (usernamePassword == null) {
            return null;
        }

        return loginUser(usernamePassword);
    }
}
