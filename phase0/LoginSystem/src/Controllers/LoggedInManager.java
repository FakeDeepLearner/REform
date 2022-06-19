package Controllers;

import Exceptions.UserCannotBeBannedException;
import useCases.AuthenticateUser;
import useCases.RestrictUser;
import useCases.UpdateUserHistory;

import java.util.ArrayList;
import java.util.Collections;

public class LoggedInManager {

    private final InputHandler input;
    private final UserInterface ui;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final RestrictUser restrict;
    private final UserManager userManager;

    public LoggedInManager(InputHandler input, UserInterface ui, AuthenticateUser auth, UpdateUserHistory history,
                           RestrictUser restrict, UserManager userManager) {
        this.input = input;
        this.ui = ui;
        this.auth = auth;
        this.history = history;
        this.restrict = restrict;
        this.userManager = userManager;
    }

    public boolean userScreen(String username) {
        if (!auth.checkUserAdmin(username)) {
            return nonAdminScreen(username);
        } else {
            return adminScreen(username);
        }
    }

    public boolean nonAdminScreen(String username) {
        ui.printNonAdminLogInMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1 -> {
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
            }
            case 2 -> {
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
            }
        }

        return true;
    }

    public boolean adminScreen(String username) {
        ui.printAdminLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1 -> {
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
            }
            case 2 ->
                // Create new admin user
                    userManager.createNewUser(true);
            case 3 -> {
                // Ban or unban user
                ui.printRestrictUsernameInput();
                String restrictUser = input.strInput();
                boolean isBanned = false;
                try {
                    isBanned = restrict.isUserBanned(restrictUser);
                } catch (UserCannotBeBannedException e) {
                    ui.printArbitraryException(e);
                }
                ui.printRestrictUserConfirmation(username, isBanned);
                ArrayList<String> allowedStrings = new ArrayList<>();
                Collections.addAll(allowedStrings, "Y", "N");
                String choice = input.strInput(allowedStrings);
                if (choice.equals("Y")) {
                    if (!isBanned) {
                        restrict.unbanNonAdminUser(restrictUser);
                    } else {
                        restrict.banNonAdminUser(restrictUser);
                    }
                }
            }
            case 4 -> {
                // Delete user
                ui.printCreateUsernameInput();
                String deleteUser = input.strInput();
                boolean deleted = restrict.deleteNonAdminUser(deleteUser);
                if (deleted) {
                    // TODO: provide feedback to UI
                } else {
                    // TODO: provide feedback to UI
                }
            }
            case 5 -> {
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
            }
        }

        return true;
    }
}
