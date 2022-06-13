import Exceptions.UndefinedInputException;

import java.util.Scanner;

public class InputHandler3 {
    private UserInterface userInterface;
    private InterfaceManager interfaceManager;

    // The reason we are instantiating the class like this is to get around circular dependencies
    public InputHandler3(){
        this.userInterface = null;
        this.interfaceManager = null;
    }

    // First the class will be instantiated, then these setters will be called.
    public void setInterfaceManager(InterfaceManager interfaceManager) {
        this.interfaceManager = interfaceManager;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void takeLoginOrSignUpInput() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String str_input = sc.next();
        try{
            int input = Integer.parseInt(str_input);
            try {
                if (input == 1) {
                    getUsernameAndPasswordInputForSignUp();
                }
                if (input == 2) {
                    getUsernameAndPasswordInputForLogIn();
                }
                else{
                    throw new UndefinedInputException();
                }
            }
            catch (UndefinedInputException exception){
                userInterface.HandleInitialUndefinedInput(exception);
            }

        }
        catch (NumberFormatException exception){
            userInterface.HandleNumberFormatException(exception);
        }
    }

    public void getUsernameAndPasswordInputForSignUp(){
        userInterface.printUsernameInput();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        userInterface.printPasswordInput();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();

        //Call the controller's sign up method next to complete sign up action

    }

    public void getUsernameAndPasswordInputForLogIn(){
        userInterface.printUsernameInput();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        userInterface.printPasswordInput();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();

        //Call the controller's log in method next to complete log in action

    }


}
