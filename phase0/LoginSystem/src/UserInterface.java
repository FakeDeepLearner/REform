import Exceptions.UndefinedInputException;

public class UserInterface {
    private InputHandler3 inputHandler3;
    public UserInterface(InputHandler3 inputHandler3) {
        this.inputHandler3 = inputHandler3;
    }

    public void printWelcomeMessage() throws InterruptedException {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account: ");
        inputHandler3.takeLoginOrSignUpInput();

    }
    public void HandleNumberFormatException(NumberFormatException exception) throws InterruptedException {
        System.out.println(exception.getMessage());
        Thread.sleep(3000);
        printWelcomeMessage();
    }

    public void HandleInitialUndefinedInput(UndefinedInputException exception) throws InterruptedException {
        System.out.println(exception.getMessage());
        Thread.sleep(3000);
        printWelcomeMessage();
    }

    public void printUsernameInput(){
        System.out.println("Please enter your username: ");
    }

    public void printPasswordInput(){
        System.out.println("Please enter your password: ");
    }

    public void printLoginSuccess() {

    }

    public void printLoginFail() {

    }

    public void printUserSignUp() {
        System.out.println("Enter your username: ");
    }
}
