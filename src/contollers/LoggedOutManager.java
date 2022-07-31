package contollers;

import exceptions.ExitProgramException;

import java.util.ArrayList;
import java.util.Arrays;

public class LoggedOutManager {

    private final InputHandler input;
    private final UserInterface ui;
    private final UserManager userManager;

    /**
     * @param input is the class responsible to get input from the user
     * @param ui is the class responsible for providing feedback to the user
     * @param userManager is the controller responsible for handling user-related tasks
     */
    public LoggedOutManager(InputHandler input, UserInterface ui, UserManager userManager) {
        this.input = input;
        this.ui = ui;
        this.userManager = userManager;
    }

    /**
     * Implements the menu for a user that is logged out
     *
     * @return the username and password associated with a logged-in user
     * @throws ExitProgramException when the user wants to quit the progran
     */
    public String menuSelector() throws ExitProgramException {
        ui.printWelcomeMessage();

        ArrayList<Integer> allowedInputs = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int menuSelect = input.intInput(allowedInputs);

        ArrayList<String> usernamePassword = new ArrayList<>();
        switch (menuSelect) {
            case 1:
                usernamePassword = createMenuSelector();
                break;
            case 2:
                // User selected to log in
                ui.printExistingUsernameInput();
                usernamePassword.add(input.strInput());
                ui.printExistingPasswordInput();
                usernamePassword.add(input.strInput());
                break;
            case 3:
                usernamePassword = userManager.createNewUser("ADMIN");
                break;
            case 4:
                throw new ExitProgramException();
        }

        if (usernamePassword == null) {
            return null;
        }

        return userManager.loginUser(usernamePassword);
    }

    /**
     * Implements the menu selector when creating a user
     */
    private ArrayList<String> createMenuSelector() {
        ui.printCreateUserMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int menuSelect = input.intInput(allowedInputs);

        ArrayList<String> usernamePassword = new ArrayList<>();
        switch (menuSelect) {
            case 1:
                usernamePassword = userManager.createNewUser("BUYER");
                break;
            case 2:
                usernamePassword = userManager.createNewUser("SELLER");
                break;
        }

        return usernamePassword;
    }
}
