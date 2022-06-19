package Controllers;

import java.util.ArrayList;

public class UserInterface {

    public void printWelcomeMessage() {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account.");
        System.out.println("(Secret) Press 3 to create an admin user.");
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

    public void printRestrictUserConfirmation(String username, boolean isBanned) {
        if (isBanned) {
            System.out.println(username + " is currently banned.");
            System.out.println("Would you like to unban them? (Y/N)");
        } else {
            System.out.println(username + " is currently not banned.");
            System.out.println("Would you like to ban them? (Y/N)");
        }
    }

    public void printCreateUsernameInput() {
        System.out.println("Create a username (It cannot include spaces): ");
    }

    public void printCreatePasswordInput() {
        System.out.println("Create a password (It cannot include spaces): ");
    }

    public void printSignUpSuccess() {
        System.out.println("User successfully created.");
    }

    public void printExistingUsernameInput() {
        System.out.println("Enter your username: ");
    }

    public void printExistingPasswordInput() {
        System.out.println("Enter your password: ");
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

    public void printRestrictUsernameInput() {
        System.out.println("Enter the username of the user whose ban status you wish to change: ");
    }

    public void printDeleteUsernameInput() {
        System.out.println("Enter the username of the user who you wish to delete: ");
    }

    public void printLoginHistory(ArrayList<String> userHistory) {
        System.out.println(userHistory);
    }

    public void printDeleteUserSuccess(String username) {
        System.out.println(username + "'s account was successfully deleted.");
    }

    public void printDeleteUserFail(String username) {
        System.out.println(username + "'s account could not be deleted.");
    }

    public void printInvalidInput() {
        System.out.println("Please enter a valid input.");
    }

    public void printArbitraryException (Exception e) {
        System.out.println(e.getMessage());
    }
}
