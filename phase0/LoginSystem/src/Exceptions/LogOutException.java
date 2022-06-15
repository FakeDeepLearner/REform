package Exceptions;

public class LogOutException extends Exception {
    public LogOutException(){
        super("Logging out, please wait");
    }
}
