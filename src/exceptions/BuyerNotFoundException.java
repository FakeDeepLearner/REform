package exceptions;

public class BuyerNotFoundException extends IllegalArgumentException{
    public BuyerNotFoundException() {
        super("No such buyer has been found.");
    }
}
