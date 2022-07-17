package LoginSystem.controllers;

import LoginSystem.exceptions.UserBannedException;
import LoginSystem.exceptions.UserNotFoundException;
import LoginSystem.exceptions.UsernameAlreadyExistsException;
import LoginSystem.useCases.AuthenticateUser;
import LoginSystem.useCases.UpdateUserHistory;
import LoginSystem.useCases.UsernamePasswordFileEditor;
import RealEstate.useCases.UserFactory;

import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    private final InputHandler inputHandler;
    private final UserInterface ui;
    private final UserFactory userFactory;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final UsernamePasswordFileEditor file;

    public UserManager(InputHandler inputHandler, UserInterface ui, UserFactory userFactory, AuthenticateUser auth,
                       UpdateUserHistory history, UsernamePasswordFileEditor file) {
        this.inputHandler = inputHandler;
        this.ui = ui;
        this.userFactory = userFactory;
        this.auth = auth;
        this.history = history;
        this.file = file;
    }

//    public void loadUsersFromCSV() {
//        ArrayList<ArrayList<String>> users;
//        try {
//            users = file.getUsersFromCSV();
//        } catch (IOException e) {
//            ui.printArbitraryException(e);
//            return;
//        }
//
//        for (ArrayList<String> user : users) {
//            ArrayList<String> loginHistory = history.readUserHistory(user.get(0));
//
//            if (Boolean.parseBoolean(user.get(2))) {
//                userFactory.createAdminUser(user.get(0), user.get(1), loginHistory);
//            } else {
//                userFactory.createNonAdminUser(user.get(0), user.get(1), loginHistory);
//            }
//        }
//    }

    /**
     * Get a username and password from the console
     *
     * @return an array of a username and password
     */
    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> usernamePassword = new ArrayList<>();

        ui.printCreateUsernameInput();
        usernamePassword.add(inputHandler.strInput());

        ui.printCreatePasswordInput();
        usernamePassword.add(inputHandler.strInput());

        return usernamePassword;
    }

    /**
     * Implements the user creation pipeline
     *
     * @param type of user to be created
     * @return the username and password of the created user
     */
    public ArrayList<String> createNewUser(String type) {
        // User selected to create a new user
        ArrayList<String> usernamePassword = getUsernameAndPassword();
        // Assume the new user is not an admin b/c only admins can create new admin users
        try {
            userFactory.createUser(type, usernamePassword.get(0), usernamePassword.get(1));
        } catch (UsernameAlreadyExistsException e) {
            ui.printArbitraryException(e);
            return null;
        }

        try {
            file.addUserInfo(usernamePassword.get(0), usernamePassword.get(1));
        } catch (IOException e) {
            ui.printArbitraryException(e);
        }

        ui.printSignUpSuccess();

        return usernamePassword;
    }

    /**
     * Logs in a User
     *
     * @param usernamePassword is the array of the username and password of the User to be logged in
     * @return the username of the logged-in user
     */
    public String loginUser(ArrayList<String> usernamePassword) {
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
}
