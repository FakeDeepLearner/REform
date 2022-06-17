/*
package Controllers;


import Exceptions.LogOutException;
import Exceptions.ReturnToMainMenuException;
import Exceptions.UndefinedInputException;

import java.util.Scanner;


public class InputHandler3 {
    private UserInterface userInterface;
    private InterfaceManager interfaceManager;

    // The reason we are instantiating the class like this is to get around circular dependencies
    public InputHandler3() {
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
        try {
            int input = Integer.parseInt(str_input);
            try {
                if (input == 1) {
                    getUsernameAndPasswordInputForSignUp();
                } else if (input == 2) {
                    getUsernameAndPasswordInputForNonAdminLogIn();
                } else if (input == 3) {
                    getUsernameAndPasswordInputForAdminLogIn();
                } else {
                    throw new UndefinedInputException();
                }
            } catch (UndefinedInputException exception) {
                userInterface.HandleInitialUndefinedInput(exception);
            }

        } catch (NumberFormatException exception) {
            userInterface.HandleNumberFormatException();
        }
    }

    public void getUsernameAndPasswordInputForSignUp() throws InterruptedException {
        userInterface.printUsernameInputForSignUp();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();

        userInterface.printPasswordInputForSignUp();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();

        userInterface.askForAdminStatus();
        Scanner adminStatusScanner = new Scanner(System.in);
        String adminStatus = adminStatusScanner.next();
        try {
            switch (adminStatus) {
                case "Y":
                    // Create admin user
                    break;
                case "N":
                    //Create regular user
                    break;
                case "M":
                    throw new ReturnToMainMenuException();
                default:
                    throw new UndefinedInputException();
            }
        } catch (UndefinedInputException exception) {
            userInterface.HandleUndefinedInputForAdminStatus(exception);

        } catch (ReturnToMainMenuException exception) {
            userInterface.HandleReturnToMainMenuException(exception);
        }
        //Call the controller's sign up method next to continue sign up action
        userInterface.printSignUpSuccess();
        userInterface.HandleReturnToMainMenuException(new ReturnToMainMenuException());

    }

    public void createAdminUserViaAnotherAdmin() throws InterruptedException {
        userInterface.printUsernameInputForSignUp();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();

        userInterface.printPasswordInputForSignUp();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();
        // Create an admin user with the controller.
        userInterface.printSignUpSuccess();
        userInterface.HandleReturnToMainMenuException(new ReturnToMainMenuException());
    }

    public void getUsernameAndPasswordInputForNonAdminLogIn() throws InterruptedException {
        userInterface.printUsernameInputForLogin();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        userInterface.printPasswordInputForLogin();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();

        //Call the controller's log in method next to continue log in action
        userInterface.printLoginSuccess();
        userInterface.printNonAdminLogInMenu();
    }

    public void getUsernameAndPasswordInputForAdminLogIn() throws InterruptedException {
        userInterface.printUsernameInputForLogin();
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        userInterface.printPasswordInputForLogin();
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();

        //Call the controller's log in method next to continue log in action
        userInterface.printLoginSuccess();
        userInterface.printAdminLoginMenu();
    }

    public void getUsernameInputForUserRestriction() {
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
    }

    public void getInputInNonAdminLoginMenu() throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String strInput = sc.next();
        try {
            int input = Integer.parseInt(strInput);
            try {
                if (input == 1) {
                    // Controller will print log in history
                }
                else if (input == 2){
                    throw new LogOutException();
                }
                else {
                    throw new UndefinedInputException();
                }
            }
            catch (UndefinedInputException exception) {
                userInterface.HandleUndefinedInputInNonAdminLogIn(exception);
            }
            catch (LogOutException exception){
                userInterface.HandleLogOutException(exception);
            }
        } catch (NumberFormatException exception) {
            userInterface.HandleNumberFormatExceptionForNonAdminLogin();
        }
    }

    public void getInputInUsernameDisplayMenu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String strInput = sc.next();
        try{
            int input = Integer.parseInt(strInput);
            if(input == 1){
                userInterface.printUsernameInputForUserRestriction();
                takeUsernameInputForUserRestriction();

            }else if (input == 2){
                userInterface.printAdminLoginMenu();
                getInputInAdminLoginMenu();
            }
            else{
                throw new UndefinedInputException();
            }
        }
        catch (NumberFormatException exception){
            userInterface.handleNumberFormatExceptionForUsernameDisplayMenu();
        }
        catch (UndefinedInputException exception){
            userInterface.handleUndefinedInputInUsernameDisplayMenu(exception);
        }

    }
    public void takeUsernameInputForUserRestriction(){
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        // Carry out the restriction action.
    }
    public void getInputInAdminLoginMenu() throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String strInput = sc.next();
        try {
            int input = Integer.parseInt(strInput);
            try {
                if (input == 1) {
                    // Controller will take care of the rest.
                } else if (input == 2) {
                    createAdminUserViaAnotherAdmin();
                } else if (input == 3){
                    userInterface.printUserNameDisplayMenu();
                    getInputInUsernameDisplayMenu();
                    userInterface.returnToAdminLoginMenu();
                }
                else if (input == 4){
                    throw new LogOutException();
                }
                else {
                    throw new UndefinedInputException();
                }
            }
            catch (UndefinedInputException exception) {
                userInterface.HandleUndefinedInputInAdminLogIn(exception);
            }
            catch (LogOutException exception){
                userInterface.HandleLogOutException(exception);
            }
        } catch (NumberFormatException exception) {
            userInterface.HandleNumberFormatExceptionForAdminLogin();
        }
    }


}
*/

