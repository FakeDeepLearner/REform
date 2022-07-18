package LoginSystem.controllers;

import LoginSystem.exceptions.UserCannotBeBannedException;
import LoginSystem.exceptions.UserNotFoundException;
import LoginSystem.useCases.AuthenticateUser;
import LoginSystem.useCases.RestrictUser;
import LoginSystem.useCases.UpdateUserHistory;
import LoginSystem.useCases.UsernamePasswordFileEditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LoggedInManager {

    private final InputHandler input;
    private final UserInterface ui;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final RestrictUser restrict;
    private final UserManager userManager;
    private final UsernamePasswordFileEditor file;

    public LoggedInManager(InputHandler input, UserInterface ui, AuthenticateUser auth, UpdateUserHistory history, RestrictUser restrict, UserManager userManager, UsernamePasswordFileEditor file) {
        this.input = input;
        this.ui = ui;
        this.auth = auth;
        this.history = history;
        this.restrict = restrict;
        this.userManager = userManager;
        this.file = file;
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
            case 1: {
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            }

            case 2: {
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
            }
        }

        return true;
    }

    private void updateUserBanStatus() {
        ui.printRestrictUsernameInput();
        String restrictUser = input.strInput();

        boolean isBanned;
        try {
            isBanned = restrict.isUserBanned(restrictUser);
        } catch (UserCannotBeBannedException | UserNotFoundException e) {
            ui.printArbitraryException(e);
            return;
        }

        ui.printRestrictUserConfirmation(restrictUser, isBanned);

        ArrayList<String> allowedStrings = new ArrayList<>();
        Collections.addAll(allowedStrings, "Y", "N");

        String choice = input.strInput(allowedStrings);
        if (choice.equals("Y")) {
            if (!isBanned) {
                restrict.banNonAdminUser(restrictUser);
            } else {
                restrict.unbanNonAdminUser(restrictUser);
            }
        }
    }

    private void deleteUser() {
        ui.printDeleteUsernameInput();
        String deleteUser = input.strInput();
        boolean deleted = restrict.deleteNonAdminUser(deleteUser);
        if (deleted) {
            try {
                file.createUsernamePasswordFile();
            } catch (IOException e) {
                ui.printArbitraryException(e);
            }

            history.overwriteUserHistories();
            ui.printDeleteUserSuccess(deleteUser);
        } else {
            ui.printDeleteUserFail(deleteUser);
        }
    }

    public boolean adminScreen(String username) {
        ui.printAdminLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1: {
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            }

            case 2: {
                // Create new admin user
                userManager.createNewUser(String.valueOf(true));
                break;
            }

            case 3: {
                // Ban or unban user
                updateUserBanStatus();
                break;
            }

            case 4: {
                // Delete user
                deleteUser();
                break;
            }

            case 5: {
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
            }
        }

        return true;
    }
}
