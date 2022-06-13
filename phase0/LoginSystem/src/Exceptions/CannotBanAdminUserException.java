package Exceptions;

public class CannotBanAdminUserException extends UnsupportedOperationException{
    public CannotBanAdminUserException(){
        super("Admin users cannot be banned");
    }
}
