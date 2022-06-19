package Exceptions;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(){
        super("User not found, enter your username again");
    }
}
