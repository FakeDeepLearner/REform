package exceptions;

public class ModificationErrorException extends IllegalArgumentException{
    public ModificationErrorException(){
        super("Invalid parameter, please try again.");
    }
}
