package Exceptions;

public class ReturnToMainMenuException extends Exception{
    public ReturnToMainMenuException(){
        super("Returning to main menu, please wait.");
    }
}
