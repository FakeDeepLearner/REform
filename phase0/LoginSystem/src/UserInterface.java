import java.util.Scanner;

public class UserInterface {
    private InterfaceManager manager;

    public UserInterface(InterfaceManager manager) {
        this.manager = manager;
    }

    public void printWelcomeMessage() {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account: ");
    }

    public void printLoginSuccess() {

    }

    public void printLoginFail() {

    }

    public void printUserSignUp() {
        System.out.println("Enter your username: ");
    }
}
