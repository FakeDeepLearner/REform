package LoginSystem.exceptions;

public class UserCannotBeBannedException extends UnsupportedOperationException{
    public UserCannotBeBannedException(){
        super("This user is not an admin, and cannot ban other users");
    }
}
