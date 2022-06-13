package Controllers;

import Controllers.InputHandler3;
import Exceptions.ReturnToMainMenuException;
import Exceptions.UndefinedInputException;

public class UserInterface {
    private final InputHandler3 inputHandler3;
    public UserInterface(InputHandler3 inputHandler3) {
        this.inputHandler3 = inputHandler3;
    }

    public void printWelcomeMessage() throws InterruptedException {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account: ");
        inputHandler3.takeLoginOrSignUpInput();

    }
    public void HandleNumberFormatException() throws InterruptedException {
        System.out.println("Please enter a number, you will be returned to the main menu shortly.");
        Thread.sleep(3000);
        printWelcomeMessage();
    }

    public void HandleInitialUndefinedInput(UndefinedInputException exception) throws InterruptedException {
        System.out.println(exception.getMessage());
        Thread.sleep(3000);
        printWelcomeMessage();
    }

    public void HandleUndefinedInoutForAdminStatus(UndefinedInputException exception) throws InterruptedException{
        System.out.println(exception.getMessage());
        Thread.sleep(3000);
        inputHandler3.getUsernameAndPasswordInputForSignUp();

    }

    public void HandleReturnToMainMenuException(ReturnToMainMenuException exception) throws InterruptedException{
        System.out.println(exception.getMessage());
        Thread.sleep(3000);
        printWelcomeMessage();
    }

    public void printUsernameInputForSignUp(){
        System.out.println("Create your username: ");
    }

    public void printPasswordInputForSignUp(){
        System.out.println("Create your password: ");
    }

    public void printUsernameInputForLogin(){
        System.out.println("Enter your username: ");
    }

    public void printPasswordInputForLogin(){
        System.out.println("Enter your password: ");
    }

    public void askForAdminStatus(){
        System.out.println("Do you want this user to be an admin (Y/N). Press M to return to the main menu. " +
                "Returning to the main menu will not create the user.");
    }

    public void printLoginSuccess() {

    }

    public void printLoginFail() {

    }


}
