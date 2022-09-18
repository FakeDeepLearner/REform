package exceptions;

public class AdminNotFoundException extends IllegalArgumentException{
    public AdminNotFoundException(){
        super("Admin user not found, please input another username.");
    }
}
