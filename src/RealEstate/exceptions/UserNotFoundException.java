package RealEstate.exceptions;


public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(){
        super("User not found, please try again.");
    }
}
