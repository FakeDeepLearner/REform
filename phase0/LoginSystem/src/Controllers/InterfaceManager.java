package Controllers;

import Entities.User;
import Entities.UserNameAndPasswordContainer;
import useCases.AuthenticateUser;
import useCases.CreateUser;
import useCases.UpdateUserHistory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;
    private InputHandler inputHandler;
    private UserInterface ui;
    private AuthenticateUser auth;
    private CreateUser createUser;
    private UpdateUserHistory history;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;

        inputHandler = new InputHandler();
        ui = new UserInterface(new InputHandler3());
        auth = new AuthenticateUser(interfaceUsers);
        createUser = new CreateUser(interfaceUsers);
        history = new UpdateUserHistory(interfaceUsers);
    }

    private ArrayList<String> getUsernameAndPassword() {
        ArrayList<String> username_password = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        ui.printUsernameInputForSignUp();
        username_password.add(sc.next());

        ui.printPasswordInputForSignUp();
        username_password.add(sc.next());

        return username_password;
    }

    public User menuSelector() {
        try {
            ui.printWelcomeMessage();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
        Collections.addAll(allowedInputs, 1, 2);

        int menuSelect = inputHandler.intInput(allowedInputs);

        if (menuSelect == 1) {
            // User selected to create a new user
            ArrayList<String> username_password = getUsernameAndPassword();

            // Assume the new user is not an admin b/c only admins can create new admin users
            createUser.createNonAdminUser(username_password.get(0), username_password.get(1));

            ui.printSignUpSuccess();

            auth.loginUser(username_password.get(0), username_password.get(1));
            User u = interfaceUsers.get(username_password.get(0));
            history.writeUserHistory(u, false);

            return u;

//            NonAdminScreen(interfaceUsers.get(username_password.get(0)));
        } else if (menuSelect == 2) {
            // User selected to create a new user
            ArrayList<String> username_password = getUsernameAndPassword();

            if (auth.loginUser(username_password.get(0), username_password.get(1))) {
                ui.printLoginSuccess();

                User u = interfaceUsers.get(username_password.get(0));
                history.writeUserHistory(u, false);

                return u;
            } else {
                ui.printLoginFail();
            }
        }

        return null;
    }

    public void NonAdminScreen(User u) {
        try {
            ui.printNonAdminLogInMenu();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
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
                break;
        }
    }

    public void AdminScreen(User u) {
        try {
            ui.printAdminLoginMenu();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
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
            case 3:
                // Logout user
                auth.logoutUser(u);
                break;
        }
    }


}
