package Controllers;

import Exceptions.LogOutException;
import Exceptions.ReturnToMainMenuException;
import Exceptions.UndefinedInputException;

public class UserInterface {

    public void printWelcomeMessage() {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account.");
    }

    public void HandleNumberFormatException() {
        System.out.println("Please enter a number.");
    }

    public void printUsernameInput() {
        System.out.println("Create a username: ");
    }

    public void printPasswordInput() {
        System.out.println("Create a password: ");
    }

    public void printSignUpSuccess() {
        System.out.println("User successfully created.");
    }

    public void printLoginSuccess() {
        System.out.println("Successfully logged in.");
    }

    public void printLoginFail() {
        System.out.println("Log in failed. Please try again.");
    }

    public void printLogOutSuccess() {
        System.out.println("Successfully logged out, returning to main menu.");
    }

    public void printNonAdminLogInMenu() {
        System.out.println("Press 1 to see your login history");
        System.out.println("Press 2 to log out.");
    }

    public void printAdminLoginMenu() {
        System.out.println("Press 1 to see your login history");
        System.out.println("Press 2 to create a user (this new user will be an admin)");
        System.out.println("Press 3 to ban or unban a user.");
        System.out.println("Press 4 to delete a user.");
        System.out.println("Press 5 to log out.");
    }

    public void printUserNameDisplayMenu() {
        System.out.println("Press 1 to restrict a user");
        System.out.println("Press 2 to return to the previous menu");
    }

    public void printUsernameInputForUserRestriction() {
        System.out.println("Enter the username of the user you wish to restrict: ");
    }

    public void returnToAdminLoginMenu() throws InterruptedException {
        System.out.println("Please wait, returning to the previous menu.");
        Thread.sleep(3000);
        printAdminLoginMenu();
    }
}
