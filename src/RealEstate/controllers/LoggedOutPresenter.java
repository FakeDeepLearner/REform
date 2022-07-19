package RealEstate.controllers;

public class LoggedOutPresenter {
    public void printWelcomeMessage(){
        System.out.println("Welcome, you are at the main menu");
        System.out.println("Press 1 to log in as a seller");
        System.out.println("Press 2 to log in as a buyer");
        System.out.println("Press 3 to create a seller account");
        System.out.println("Press 4 to create a seller account");
    }

    public void printUsernameEntryForSignUp(){
        System.out.println("Create your username:");
    }

    public void printPasswordEntryForSignUp(){
        System.out.println("Create your password:");
    }

    public void printLoginUsernameEntry(){
        System.out.println("Enter your username");
    }

    public void printPasswordPasswordEntry(){
        System.out.println("Enter your password");
    }


}
