package LoginSystem.exceptions;

public class UserBannedException extends Exception {
    public UserBannedException() {
        super("This user is currently banned, please try again later.");
    }
}
