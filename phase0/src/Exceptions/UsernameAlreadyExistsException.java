package Exceptions;

public class UsernameAlreadyExistsException extends IllegalArgumentException{
    public UsernameAlreadyExistsException(){
        super("That username is taken, please enter another one");
    }
}
