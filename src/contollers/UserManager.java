package contollers;

import entities.User;
import entities.containers.UserContainer;
import exceptions.UserBannedException;
import exceptions.UserNotFoundException;
import exceptions.UsernameAlreadyExistsException;
import useCases.userUseCases.AuthenticateUser;
import useCases.userUseCases.UpdateUserHistory;
import useCases.userUseCases.UserFactory;

import java.util.ArrayList;

public class UserManager {
    private final InputHandler input;
    private final UserInterface ui;
    private final UserFactory userFactory;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;


    /**
     * Constructor for UserManager
     * @param userContainer stores the users of the program
     */
    public UserManager(UserContainer<String, User> userContainer) {
        auth = new AuthenticateUser(userContainer);
        history = new UpdateUserHistory(userContainer);

        ui = new UserInterface();
        input = new InputHandler(ui);
        userFactory = new UserFactory(userContainer);
    }

    /**
     * Constructor for UserManager
     * @param userFactory creates new users
     * @param auth is the user authenticator
     * @param history updates the history of a user
     * @param file stores the usernames and passwords of users
     */
    public UserManager(UserFactory userFactory, AuthenticateUser auth,
                       UpdateUserHistory history) {
        this.userFactory = userFactory;
        this.auth = auth;
        this.history = history;

        ui = new UserInterface();
        input = new InputHandler(ui);
    }

    /**
     * Get a username and password from the console
     *
     * @return an array of a username and password
     */
    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> usernamePassword = new ArrayList<>();

        ui.printCreateUsernameInput();
        usernamePassword.add(input.strInput());

        ui.printCreatePasswordInput();
        usernamePassword.add(input.strInput());

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
