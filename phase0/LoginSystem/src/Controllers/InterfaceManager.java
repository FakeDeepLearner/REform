package Controllers;

import Entities.User;
import Entities.UserNameAndPasswordContainer;
import useCases.AuthenticateUser;
import useCases.CreateUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;
    private InputHandler inputHandler;
    private UserInterface ui;
    private AuthenticateUser auth;
    private CreateUser createUser;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers) {
        this.interfaceUsers = interfaceUsers;

        inputHandler = new InputHandler();
        ui = new UserInterface(new InputHandler3());
        auth = new AuthenticateUser(interfaceUsers);
        createUser = new CreateUser(interfaceUsers);
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

    public void menuSelector() {
        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
        Collections.addAll(allowedInputs, 1, 2);

        int menuSelect = inputHandler.intInput(allowedInputs);

        if (menuSelect == 1) {
            // User selected to create a new user
            ArrayList<String> username_password = getUsernameAndPassword();

            // Assume the new user is not an admin b/c only admins can create new admin users
            createUser.createNonAdminUser(username_password.get(0), username_password.get(1));

            ui.printSignUpSuccess();

            NonAdminScreen(interfaceUsers.get(username_password.get(0)));
        } else if (menuSelect == 2) {
            // User selected to create a new user
            ArrayList<String> username_password = getUsernameAndPassword();

            if (auth.loginUser(username_password.get(0), username_password.get(1))) {
                ui.printLoginSuccess();

                User u = interfaceUsers.get(username_password.get(0));
                if (!u.getAdmin()) {
                    NonAdminScreen(u);
                } else {
                    AdminScreen(u);
                }
            } else {
                ui.printLoginFail();
            }
        }
    }

    public void NonAdminScreen(User u) {
        try {
            ui.printNonAdminLogInMenu();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    public void AdminScreen(User u) {
        try {
            ui.printAdminLoginMenu();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }


}
