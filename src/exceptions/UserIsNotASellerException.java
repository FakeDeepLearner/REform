package exceptions;

public class UserIsNotASellerException extends IllegalArgumentException{
    public UserIsNotASellerException(){
        super("The user is not a seller, please enter another username");
    }
}
